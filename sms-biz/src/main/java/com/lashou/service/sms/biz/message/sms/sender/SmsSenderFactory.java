package com.lashou.service.sms.biz.message.sms.sender;

import com.lashou.service.sms.biz.message.config.impl.Channels;

/**
 * Created by sher on 3/4/16.
 */
public class SmsSenderFactory {


    public static SmsSenderImpl createSmsSender(Channels channels){
        SmsSenderImpl sender = new SmsSenderImpl();
        sender.initHttpclient(channels);
        return sender;
    }
}
