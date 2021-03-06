package com.lashou.service.sms.biz.message.sms;

import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;
import com.lashou.service.sms.domain.OpResult;

/**
 * Created by sher on 1/19/16.
 */
public interface SmsSender {

    SmsResult sendMessage(SmsRequestMsg msg);
}
