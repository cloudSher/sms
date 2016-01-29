package com.lashou.service.sms.biz.message.sms.operators.honglian;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.sender.Sender;
import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.common.HttpExecutor;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import com.lashou.service.sms.biz.message.sms.model.SmsMessage;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;
import com.lashou.service.sms.domain.OpResult;
import com.lashou.service.sms.domain.PushReq;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鸿联企信通 渠道商 发送器
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public class HLSender implements SmsSender {

    private static Logger logger = LoggerFactory.getLogger(HLSender.class);

    private HttpClient httpClient;

    private Configuration configuration;

    private HLSender(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public SmsResult sendMessage(SmsRequestMsg msg) {
        String url = "";
        int timeout= 0;
        HttpResult httpResult = HttpExecutor.doHttpGet(httpClient, url, "", timeout);
        if(httpResult.getCode() == 10000){
            logger.info("短消息发送成功");
            return SmsResult.succeed("短信发送成功",null);
        }else{
            logger.error("短消息发送失败");
            return SmsResult.failed(10901,"短消息发送失败");
        }
    }

    public void setConfiguration(Configuration configuration){
        this.configuration = configuration;
    }
}
