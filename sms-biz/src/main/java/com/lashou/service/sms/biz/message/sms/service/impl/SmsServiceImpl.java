package com.lashou.service.sms.biz.message.sms.service.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.dispatcher.Dispatcher;
import com.lashou.service.sms.biz.message.sms.SenderPoolFactory;
import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.SmsSenderPool;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.*;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;
import com.lashou.service.sms.biz.message.sms.sender.SmsSenderFactory;
import com.lashou.service.sms.biz.message.sms.service.SmsService;
import com.lashou.service.sms.biz.monitor.impl.SmsMsgMonitorData;
import com.lashou.service.sms.domain.OpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by sher on 1/19/16.
 */
public class SmsServiceImpl implements SmsService{

    private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);


    @Resource
    private Dispatcher dispatcher;


    public SmsResult sendMessage(SmsRequestMsg msg){
        Result result = dispatcher.serviceAction(msg);
        List<SmsRequestMsg> list = null;
        if(result!=null && result.getCode() ==1){
            list = (List<SmsRequestMsg>) result.getT();
        }

        SmsResult smsResult = null;
        if(list!=null && list.size() >0){
            for(int i = 0 ; i < list.size(); i++){
                SmsRequestMsg requestMsg = list.get(i);
                 smsResult = sendMsg(requestMsg.getChannels(),requestMsg);
                if(smsResult.getCode() == 0){
                    //todo 未发送成功之后
                    //添加监控数据
                    addMonitor(requestMsg);
                    Channels channels = dispatcher.reSiftChannels(requestMsg.getChannels());
                    smsResult = sendMsg(channels, msg);
                    if(smsResult.getCode() == 0){
                        //todo 渠道商你也太牛了，这都请求不到，中彩了
                        addMonitor(requestMsg);
                    }
                }
            }
        }

        return smsResult;
    }

    public SmsResult sendMsg(Channels channels,SmsRequestMsg msg){
        SmsSender smsSender = SmsSenderFactory.createSmsSender(channels);
        SmsResult smsResult = smsSender.sendMessage(msg);
        if(smsResult!=null){
            if(smsResult.getCode() == 0){
                SmsResult result = reSendMsg(smsSender, msg);
                return result;
            }
        }
        return smsResult;
    }


    public void addMonitor(SmsRequestMsg requestMsg){
        SmsMsgMonitorData monitorData = new SmsMsgMonitorData();
        monitorData.setMobiles(requestMsg.getMobiles());
        monitorData.setCurrTime(new Date());
        monitorData.setChannelsName(requestMsg.getChannels().getChannelName());
        monitorData.setChannelsId(requestMsg.getChannels().getId());
        monitorData.setDescription("未发送成功短信");
        dispatcher.sendMonitorData(monitorData);
    }

    public SmsResult reSendMsg(SmsSender smsSender,SmsRequestMsg msg){
        SmsResult smsResult = null;
        for(int i = 0 ; i < 3; i++){
            smsResult = smsSender.sendMessage(msg);
            if(smsResult.getCode() == 1){
                break;
            }
            try{
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(msg.getChannel() +" 发送消息失败",e);
            }
        }

        return smsResult;
    }


}
