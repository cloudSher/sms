package com.lashou.service.sms.biz.message.sms.common;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class HttpExecutor {

    private static final Logger logger = LoggerFactory.getLogger(HttpExecutor.class);

    public HttpResult toHttpGet(HttpClient client,String url,Character msg){
        if(StringUtil.isNullOrEmpty(url)){
            return null;
        }

        HttpGet httpGet;
        HttpResult response = null;
        try {
            logger.info("httpclient send message start");
            httpGet = new HttpGet(url);
            response = new HttpResult();


            HttpResponse httpResponse = client.execute(httpGet);
            if(httpResponse!=null){
                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    response.setCode(10000);
                    response.setResMsg(httpResponse.getEntity().getContent().toString());
                }
            }
        }catch (Exception e){
            logger.error("发送短信失败",e);
            response.setCode(100201);
            response.setResMsg("短信平台发送失败");
        }

        return response;

    }


    public void HttpPost(){

    }



}
