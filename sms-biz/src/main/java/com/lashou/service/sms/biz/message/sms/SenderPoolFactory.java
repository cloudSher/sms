package com.lashou.service.sms.biz.message.sms;

/**
 * Created by sher on 1/19/16.
 */
public interface SenderPoolFactory {

    SmsSenderPool getPool(String key);
}

