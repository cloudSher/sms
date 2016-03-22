package com.lashou.service.sms.biz.message.protocol.httpclient;

import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sher on 3/7/16.
 */
public class HttpClientTask {

    private static Logger logger = LoggerFactory.getLogger(HttpClientTask.class);

    private String url;

    private String character;

    public HttpClientTask(String url,String character){
        this.url = url;
        this.character = character;
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
            response = new HttpResult();
            httpResponse = client.execute(httpGet);
            if(httpResponse!=null){
                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    response.setCode(10000);
                    response.setResMsg(EntityUtils.toString(httpResponse.getEntity(), character == null ? "utf-8" : character));
                }
            }
        }catch (Exception e){
            logger.error("发送短信失败",e);
            response.setCode(100201);
            response.setResMsg("短信平台发送失败");
        }finally {
//            if(httpResponse!=null){
//                try {
//                    httpResponse.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    response.setCode(100);
//                    logger.error("短信平台请求关闭错误");
//                }
//            }
        }

        return response;

    }
}
