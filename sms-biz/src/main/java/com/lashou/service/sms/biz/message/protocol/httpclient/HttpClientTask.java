package com.lashou.service.sms.biz.message.protocol.httpclient;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import com.lashou.service.sms.biz.message.sms.model.HttpResultCode;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sher on 3/7/16.
 */
public class HttpClientTask {

    private static Logger logger = LoggerFactory.getLogger(HttpClientTask.class);

    private String url;

    private Channels channels;

    public HttpClientTask(String url,Channels channels){
        this.url = url;
        this.channels = channels;
    }

    public  HttpResult doHttpGet(HttpClient client){
        if(StringUtil.isNullOrEmpty(url)){
            return null;
        }

        HttpGet httpGet;
        HttpResult response = null;
        HttpResponse httpResponse = null;
        try {
            logger.info("httpclient send message start .....");
            httpGet = new HttpGet(url);
            client.getParams().setParameter(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");
            response = new HttpResult();
            httpResponse = client.execute(httpGet);
            if(httpResponse!=null){
                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    String msg = EntityUtils.toString(httpResponse.getEntity(),  "utf-8");
                    System.out.println(msg);
                    response.setCode(HttpResultCode.SUCCESS);
                    response.setResMsg(msg);
                    response.setResponseCode(msg);
                }
            }
        }catch (Exception e){
            logger.error("发送短信失败",e);
            response.setCode(HttpResultCode.FAIL_SEND);
            response.setResMsg("短信平台发送失败");
        }finally {

        }

        return response;

    }


    public HttpResult doHttpPost(HttpClient client){
        if(StringUtil.isNullOrEmpty(url)){
            return null;
        }

        HttpPost httpPost;
        HttpResult response = null;
        try{
            logger.info("httpclient method post send message start .....");
            httpPost = new HttpPost(url);
            httpPost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
            client.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
//            client.getParams().setParameter(HTTP.CONTENT_ENCODING,);

        }catch (Exception e){
            logger.error("发送短信失败",e);
            response.setCode(HttpResultCode.FAIL_SEND);
            response.setResMsg("短信平台发送失败");
        }finally {

        }

        return response;
    }
}
