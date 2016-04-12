package com.lashou.service.sms.biz.message.sms.service.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.dispatcher.Dispatcher;
import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.*;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;
import com.lashou.service.sms.biz.message.sms.sender.SmsSenderFactory;
import com.lashou.service.sms.biz.message.sms.service.SmsService;
import com.lashou.service.sms.biz.monitor.impl.SmsMsgResponseData;
import com.lashou.service.sms.biz.monitor.impl.SmsMsgMonitor;
import com.lashou.service.sms.domain.MessageResponse;
import com.lashou.service.sms.mapper.MessageResponseMapper;
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

    @Resource
    private MessageResponseMapper messageResponseMapper;


    public SmsResult sendMessage(SmsRequestMsg msg){
        Result result = dispatcher.serviceAction(msg);
        List<SmsRequestMsg> list = null;
        if(result!=null && result.getCode() ==1){
            list = (List<SmsRequestMsg>) result.getT();
        }

        SmsResult smsResult = null;
        long currentTime = System.currentTimeMillis();
        if(list!=null && list.size() >0){
            for(int i = 0 ; i < list.size(); i++){
                SmsRequestMsg requestMsg = list.get(i);
                if(requestMsg.getSendScope() == 1){
                    smsResult = sendMsg(requestMsg.getChannels(),requestMsg);
                    if(smsResult.getCode() == 0){
                        //todo 未发送成功之后
                        //添加失败监控数据
                        addMonitor(requestMsg,0,System.currentTimeMillis()-currentTime,smsResult.getResult());
                        Channels channels = dispatcher.reSiftChannels(requestMsg.getChannels(),requestMsg);
                        smsResult = sendMsg(channels, msg);
                        if(smsResult.getCode() == 0){
                            //渠道商你也太牛了，这都请求不到，中彩了
                            addMonitor(requestMsg,0,System.currentTimeMillis()-currentTime,smsResult.getResult());
                        }
                    }else if(smsResult.getCode() == 1){
                        addMonitor(requestMsg, 1,System.currentTimeMillis()-currentTime,smsResult.getResult());
                    }
                }
                else if(requestMsg.getSendScope() == 2){
                    System.out.println("=======msg个数======"+list.size());
                    addMonitor(requestMsg,1,System.currentTimeMillis()-currentTime,null);
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


    public void addMonitor(SmsRequestMsg requestMsg,int code,long responseTime,Object result){
        SmsMsgMonitor monitor =  (SmsMsgMonitor) dispatcher.getContainer().getMonitor();
        if(code == 1){
            monitor.incSuccessNum();
        }else if(code == 0){
            monitor.incFailNum();
        }
        monitor.incChannelsMonitorData(requestMsg.getChannels(),code);
        SmsMsgResponseData monitorData = new SmsMsgResponseData();
        monitorData.setMobiles(requestMsg.getMobiles());
        monitorData.setCurrTime(new Date());
        monitorData.setChannelsName(requestMsg.getChannels().getChannelName());
        monitorData.setChannelsId(requestMsg.getChannels().getId());
        monitorData.setDescription(code == 0?"未发送成功短信":"发送成功");
        monitor.collectionMonitorData(monitorData);
        monitorData.setResponseTime(new Long(responseTime).intValue());
        monitorData.setResponseCode(result!=null?(String)result:null);
        monitorData.setId(requestMsg.getMessageId());
        monitorData.setName(requestMsg.getMessage());
        monitorData.setIsSuccess(new Byte("1"));
        monitorData.setMobileOperator(requestMsg.getMobileOperator());
        monitorData.setOperatorType(requestMsg.getOperatorType());
        System.out.println("=========================="+monitorData);
//        dispatcher.sendMonitorData();
        insertResponseData(monitorData);
    }


    public void insertResponseData(SmsMsgResponseData responseData){
        MessageResponse response = new MessageResponse();
        response.setMessageid(responseData.getId());
        response.setChannelsid(responseData.getChannelsId());
        response.setChannelsname(responseData.getChannelsName());
        response.setChannelsResponseCode(responseData.getResponseCode());
        response.setChannelsResponseTime(responseData.getResponseTime());
        response.setCurrenttime(new Date());
        response.setOperatorType(responseData.getOperatorType());
        response.setMobilesOperator(responseData.getMobileOperator());
        response.setDescription(responseData.getDescription());
        response.setMessagename(responseData.getName());
        response.setMobiles(responseData.getMobiles());
        response.setIssuccess(responseData.getIsSuccess());
        response.setMessagename(responseData.getName());
        messageResponseMapper.insert(response);
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
