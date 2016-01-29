package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public class SmsInvocation implements Invocation {

    private Invoker invoker;

    private SmsRequestMsg msg;

    @Override
    public Invoker getInvoker() {
        return null;
    }


    public SmsRequestMsg getAttachment(){
        return msg;
    }
}
