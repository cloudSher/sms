package com.lashou.service.sms.biz.message.sender.impl;


import com.lashou.service.sms.biz.message.sms.model.SmsMessage;
import com.lashou.service.sms.biz.message.sender.Sender;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.service.SmsService;
import com.lashou.service.sms.domain.OpResult;
import com.lashou.service.sms.domain.PushReq;
import com.lashou.service.sms.domain.PushReqBroad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class SmsSender implements Sender {

    private static Logger logger = LoggerFactory.getLogger(SmsSender.class);

    @Resource
    private SmsService smsService;

    @Override
    public OpResult sendMessage(SmsMessage msg) {
        return null;
    }


    @Override
    public void broadCast(PushReq req) {
        logger.info("群发短信：", req.getReqId());
        SmsRequestMsg msg = new SmsRequestMsg();
        msg.setChannel(req.getPushChannel());
        String mobiles = "";
        if(req instanceof PushReqBroad){
            PushReqBroad broad = (PushReqBroad) req;
            int len = 0;
            for(String mo : broad.getUserIds()){
                mobiles += mo;
                if(len++ < broad.getUserIds().size()-1){
                    mobiles += ",";
                }
            }
            msg.setMessage(broad.getPushMsg().getContent());
            msg.setMobiles(mobiles);
        }
        smsService.sendMessage(msg);

    }
}

