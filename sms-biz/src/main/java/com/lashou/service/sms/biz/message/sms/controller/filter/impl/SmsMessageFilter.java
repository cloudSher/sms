package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public class SmsMessageFilter implements Filter {


    @Override
    public Result invoke(Invoker invoker, Invocation invocation) {
        SmsInvocation smsInv = (SmsInvocation) invocation;
        SmsRequestMsg msg = smsInv.getAttachment();
        if(msg != null){
            String content = msg.getMessage();
            if(content != null && content.trim().length()!=0){

            }
        }
        System.out.println("message filter");
        return invoker.invoke(invocation);
    }
}
