package com.lashou.service.sms.biz.message.sms.sender;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.protocol.HttpClient;
import com.lashou.service.sms.biz.message.protocol.httpclient.HttpClientImpl;
import com.lashou.service.sms.biz.message.protocol.httpclient.HttpClientTask;
import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import com.lashou.service.sms.biz.message.sms.model.HttpResultCode;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;

/**
 * Created by sher on 3/4/16.
 */
public class SmsSenderImpl implements SmsSender {


    private HttpClient httpClient;

    private Channels channels;

    @Override
    public SmsResult sendMessage(SmsRequestMsg msg) {
        SmsResult smsResult = new SmsResult();
        try {
            if(channels!=null){
                String url = "";
                String character = "";
                HttpClientTask task = new HttpClientTask(url,character);
                HttpResult result = httpClient.execute(task);
                if(result!=null){
                    int code = result.getCode();
                    if(code == HttpResultCode.SUCCESS){
                        smsResult.setCode(1);
                        smsResult.setMsg("发送短消息成功");
                    }else{
                        smsResult.setCode(0);
                        smsResult.setMsg("发送短消息失败");
                    }
                }
            }

        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        return smsResult;
    }


    public void initHttpclient(Channels channels) {
        try {
            if (channels == null)
                throw new InvalidArgumentException("渠道商信息不能为空");
            this.channels = channels;
            int timeOut = channels.getHttpclientConnectionTimeout();
            int maxTotal = channels.getHttpclientMaxTotal();
            int sott = channels.getHttpclientSoTimeout();

            if(httpClient == null)
                httpClient = new HttpClientImpl(maxTotal>0?maxTotal:200,timeOut>0?timeOut:200,sott>0?sott:400);

        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

    }
}
