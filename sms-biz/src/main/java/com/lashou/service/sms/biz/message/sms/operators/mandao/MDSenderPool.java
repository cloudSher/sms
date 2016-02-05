package com.lashou.service.sms.biz.message.sms.operators.mandao;

import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.SmsSenderPool;
import com.lashou.service.sms.biz.message.sms.common.ResourceConfigUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by sher on 1/19/16.
 */
public class MDSenderPool implements SmsSenderPool{

    private static final HttpClient httpClient;
    private static int maxTotal;
    private static int maxClientPreUrl;

    static{
        maxTotal = ResourceConfigUtil.getValueOfInteger("md.pool.maxClients",200);
        maxClientPreUrl = ResourceConfigUtil.getValueOfInteger("md.pool.maxClientPreUrl",200);

        httpClient = new DefaultHttpClient();
    }


    public SmsSender get(){
        return new MDSender(httpClient);
    }

}
