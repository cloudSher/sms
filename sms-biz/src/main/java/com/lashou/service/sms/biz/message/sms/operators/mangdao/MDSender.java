package com.lashou.service.sms.biz.message.sms.operators.mangdao;


import com.lashou.service.sms.biz.message.sms.SmsSender;
import com.lashou.service.sms.biz.message.sms.common.EncrptUtil;
import com.lashou.service.sms.biz.message.sms.common.HttpExecutor;
import com.lashou.service.sms.biz.message.sms.common.ResourceConfigUtil;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.sms.model.SmsResult;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 漫道运营商发送器
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/18
 */
public class MDSender implements SmsSender {

    private HttpClient httpClient;
    private final static String user;
    private final static String pwd;
    private static Map<String,Object> configMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(MDSender.class);
    private final static String desUrl;
    private final static int timeout;

    static{
        user = ResourceConfigUtil.getValueOfString("md.user","");
        pwd = ResourceConfigUtil.getValueOfString("md.password","");
        desUrl = ResourceConfigUtil.getValueOfString("md.url","");
        timeout = ResourceConfigUtil.getValueOfInteger("md.timeout",1000);
        StringUtil.parseConfig(user,configMap);
        StringUtil.parseConfig(pwd,configMap);
        StringUtil.parseConfig(desUrl,configMap);
    }

    public MDSender(HttpClient client){
        this.httpClient = client;
    }


    /**
     * 发送消息
     * @return
     */
    public SmsResult sendMessage(SmsRequestMsg req){
        if(req == null){
            return SmsResult.failed(10901, "短消息不能为空");
        }

        if(configMap.size() == 0){
            return SmsResult.failed(10801, "配置文件没有加载成功");
        }

        StringBuilder url = new StringBuilder();
        url.append(configMap.get("url"));
        configMap.remove("url");
        url.append("?");

        int len = 0;
        String user = "";
        for(Map.Entry<String,Object> entry : configMap.entrySet()){
            if(len == 1){
                url.append(entry.getKey());
                url.append("=");
                String pwd = entry.getValue().toString();
                url.append(EncrptUtil.md5(user + pwd).toUpperCase());
            }else{
                if(len == 0){
                    user = entry.getValue().toString();
                }
                url.append(entry.getKey());
                url.append("=");
                url.append(entry.getValue());
            }
                url.append("&");
                len++;
        }

        String msg = req.getMessage();
        url.append("content=").append(StringUtil.encoding(msg,"gb2312"));

        HttpResult httpResult = HttpExecutor.doHttpGet(httpClient, url.toString(), "", timeout);
        if(httpResult.getCode() == 10000){
            logger.info("短消息发送成功");
            return SmsResult.succeed("短信发送成功",null);
        }else{
            logger.error("短消息发送失败");
            return SmsResult.failed(10901,"短消息发送失败");
        }
    }



}
