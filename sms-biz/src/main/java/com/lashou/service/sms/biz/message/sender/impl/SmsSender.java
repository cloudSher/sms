package com.lashou.service.sms.biz.message.sender.impl;


import com.lashou.service.sms.biz.message.sms.model.SmsMessage;
import com.lashou.service.sms.biz.message.sender.Sender;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsSendScopeType;
import com.lashou.service.sms.biz.message.sms.service.SmsService;
import com.lashou.service.sms.domain.Message;
import com.lashou.service.sms.domain.OpResult;
import com.lashou.service.sms.domain.PushReq;
import com.lashou.service.sms.domain.PushReqBroad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

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
    public void broadCast(Message message) {
        logger.info("发短信：", message.getMsgId());
        SmsRequestMsg msg = new SmsRequestMsg();
        String mobiles = "";
        if(message != null){
            Map<String, Object> parameters = message.getBody().getSender().getParameters();
            if(parameters!=null){
                mobiles = (String) parameters.get("mobiles");
            }
            msg.setMessage(message.getBody().getContent());
            msg.setMobiles(mobiles);
            msg.setSendScope(message.getHeader().getType());
        }
        smsService.sendMessage(msg);

    }
}

