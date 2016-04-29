package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

import java.util.Arrays;
import java.util.List;

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
        List<Channels> channels = smsInv.getChannels();
        if(msg != null){
            String channel = msg.getChannel();
            if(StringUtil.isNullOrEmpty(channel)){
                return invoker.invoke(invocation);
            }
            if(channels!=null && channels.size() >0){
                for(Channels c : channels){
                    if(c.getId().equals(channel)){
                        msg.setChannels(c);
                        smsInv.setMsgList(Arrays.asList(msg));
                    }
                }
            }
        }
        return new Result(1,smsInv.getMsgList());
    }
}
