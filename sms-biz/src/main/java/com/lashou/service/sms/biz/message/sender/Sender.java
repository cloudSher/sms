package com.lashou.service.sms.biz.message.sender;


import com.lashou.service.sms.biz.message.sms.model.SmsMessage;
import com.lashou.service.sms.domain.Message;
import com.lashou.service.sms.domain.OpResult;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public interface Sender {

   OpResult sendMessage(SmsMessage msg);

   void broadCast(Message message);
}
