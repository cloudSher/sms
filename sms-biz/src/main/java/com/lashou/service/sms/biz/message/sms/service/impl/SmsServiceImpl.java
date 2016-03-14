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
import com.lashou.service.sms.domain.OpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sher on 1/19/16.
 */
public class SmsServiceImpl implements SmsService{

    private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);


    @Resource
    private SenderPoolFactory poolFactory;

    @Resource
    private Dispatcher dispatcher;

//    @Override
//    public SmsResult sendMessage(SmsRequestMsg msg) {
//
//        msg.setChannel("md");
//        SmsSenderPool pool = poolFactory.getPool(msg.getChannel());
//        if(pool == null){
//            logger.error("获取短信通道商pool出错");
//            return SmsResult.failed(10901,"获取短信通道出错");
//
//        }
//        SmsSender smsSender = pool.get();
//        if(smsSender == null){
//            logger.error("获取短信通道sender出错");
//            return SmsResult.failed(10801, "获取短信通道sender出错");
//        }
//
//        SmsResult smsResult = smsSender.sendMessage(msg);
//        //发送失败，尝试在重新发送，
//        if(smsResult.getCode() != 10000){
//            logger.error(msg.getChannel()+"发送失败，");
//            for(int i = 0 ; i < 3; i++){
//                SmsResult smsRt = smsSender.sendMessage(msg);
//                if(smsRt.getCode() == 10000){
//                    smsResult = smsRt;
//                    break;
//                }
//                try{
//                    Thread.sleep(5 * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    logger.error(msg.getChannel()+"发送失败，尝试次数："+i);
//                }
//            }
//        }
//        return smsResult;
//    }


    public SmsResult sendMessage(SmsRequestMsg msg){

        List<Channels> channelsList = dispatcher.getChannels(msg);
        if(channelsList == null){
            throw new RuntimeException("渠道商信息为空");
        }

        Channels channels = channelsList.get(0);
        SmsResult smsResult = sendMsg(channels, msg);

        if(smsResult.getCode() == 0){
            if(channelsList.size() > 1)
                 channels = channelsList.get(1);
            smsResult = sendMsg(channels, msg);
            if(smsResult.getCode() == 0){
                //todo 渠道商你也太牛了，这都请求不到
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
