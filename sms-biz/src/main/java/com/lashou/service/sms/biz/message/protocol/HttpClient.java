package com.lashou.service.sms.biz.message.protocol;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.protocol.httpclient.HttpClientTask;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;

/**
 * Created by sher on 3/4/16.
 */
public interface HttpClient {


    HttpResult execute(HttpClientTask task) throws InvalidArgumentException;


}
