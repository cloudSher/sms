package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

import java.util.List;

/**
 * Created by cloudsher on 2016/3/31.
 */
public class SmsChannelsSignFilter implements Filter {



    @Override
    public Result invoke(Invoker invoker, Invocation invocation) {
        List<SmsRequestMsg> msgList = invocation.getMsgList();
        if(msgList!=null && msgList.size()>0){
            for(SmsRequestMsg msg : msgList){
                Channels channels = msg.getChannels();
                if(channels!=null){
                    String sign = channels.getChannelsSign();
                    if(!StringUtil.isNullOrEmpty(sign)){
                        String content = msg.getMessage() +sign;
                        msg.setMessage(content);
                    }
                    String mobileSplice = channels.getMobileSplice();
                    if (!StringUtil.isNullOrEmpty(mobileSplice)){
                        String mobiles = msg.getMobiles();
                        msg.setMobiles(mobiles.replaceAll(",",mobileSplice));
                    }
                }
            }
        }
        return new Result(1,msgList);
    }
}
