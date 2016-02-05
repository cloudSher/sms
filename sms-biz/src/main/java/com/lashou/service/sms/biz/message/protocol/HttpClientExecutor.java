package com.lashou.service.sms.biz.message.protocol;

import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/2/5.
 */
public class HttpClientExecutor {

    private static Logger logger = Logger.getLogger(HttpClientExecutor.class);

    private HttpClient httpClient;

    public void execute(Object op){
        if(httpClient == null)
            throw new RuntimeException("httpclient is not null");
        final Object smsOp = op;
        HttpResult result =  callInHttp(new Callable() {

            @Override
            public HttpResult call() {
                return doHttpMethod(smsOp);
            }
        });

    }

    public HttpResult callInHttp(Callable callable){
        callable.call();
        return null;
    }

    public HttpResult doHttpMethod(Object operator){
        if(operator == null){
            logger.error("httpclientExecutor class operator is null");
            throw new RuntimeException("operator is null");
        }



        return null;
    }



    interface Callable{
        HttpResult call();
    }


}
