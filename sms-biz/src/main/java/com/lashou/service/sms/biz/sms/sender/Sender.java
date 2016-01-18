package com.lashou.service.sms.biz.sms.sender;


import com.lashou.service.sms.biz.sms.model.SmsMessage;
import com.lashou.service.sms.domain.OpResult;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public interface Sender {

   OpResult sendMessage(SmsMessage msg);
}
