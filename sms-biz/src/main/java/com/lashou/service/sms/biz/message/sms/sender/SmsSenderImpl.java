package com.lashou.service.sms.biz.message.sms.sender;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.protocol.HttpClient;
import com.lashou.service.sms.biz.message.protocol.httpclient.HttpClientImpl;
import com.lashou.service.sms.biz.message.protocol.httpclient.HttpClientTask;
import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.common.EncrptUtil;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import com.lashou.service.sms.biz.message.sms.model.HttpResultCode;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by sher on 3/4/16.
 */
public class SmsSenderImpl implements SmsSender {

    private static Logger logger = LoggerFactory.getLogger(SmsSenderImpl.class);

    private HttpClient httpClient;

    private Channels channels;

    @Override
    public SmsResult sendMessage(SmsRequestMsg msg) {
        SmsResult smsResult = new SmsResult();
        logger.info("smsSender sendMessage start...");
        try {
            if(channels!=null){
                String url = getUrl(channels,msg);
                System.out.println(url);
                String character = channels.getEncode();
                HttpClientTask task = new HttpClientTask(url,channels);
                HttpResult result = httpClient.execute(task);
                logger.info("发送短信执行完成，");
                if(result!=null){
                    int code = result.getCode();
                    if(code == HttpResultCode.SUCCESS){
                        smsResult.setCode(1);
                        smsResult.setMsg("发送短消息成功");
                        smsResult.setResult(result.getResponseCode());
                        logger.info("发送短信成功");
                    }else{
                        smsResult.setCode(0);
                        smsResult.setMsg("发送短消息失败");
                        logger.info("发送短信失败，");
                    }
                }
            }

        } catch (InvalidArgumentException e) {
            e.printStackTrace();
            logger.error("smsSenderImpl sendMessage error,",e);
        }

        return smsResult;
    }

    public String getUrl(Channels channels,SmsRequestMsg msg){
        StringBuilder str = new StringBuilder();
        String message = msg.getMessage();
        try {
            message =  URLEncoder.encode(message,channels.getEncode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        str.append(channels.getUrl())
                .append("?").append(channels.getUserId()).append("=").append(channels.getAccount().getUser())
                .append("&").append(channels.getPasswordId()).append("=").append(channels.getAccount().getPassword())
                .append("&").append(channels.getMsgId()).append("=").append(message)
                .append("&").append(channels.getMobileId()).append("=").append(msg.getMobiles());
        if(channels.getChannelCodeId()!=null){
            str.append("&").append(channels.getChannelCodeId()).append("=").append(channels.getChannelCode());
        }
        return str.toString();
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
            logger.error("初始化httpclient出错,",e);
        }

    }
}
