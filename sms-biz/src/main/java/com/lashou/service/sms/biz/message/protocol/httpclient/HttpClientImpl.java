package com.lashou.service.sms.biz.message.protocol.httpclient;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.HttpResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sher on 3/4/16.
 */
public class HttpClientImpl implements com.lashou.service.sms.biz.message.protocol.HttpClient{


    private static HttpClient httpClient;
    private static PoolingClientConnectionManager cm;
    private static HttpParams params = new BasicHttpParams();
    private static SchemeRegistry schemeRegistry = new SchemeRegistry();
    private static Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);

    public HttpClientImpl(int maxTotal,int ctt,int sott){
        schemeRegistry.register(
                new Scheme("http",80, PlainSocketFactory.getSocketFactory()));
        cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(maxTotal);
        HttpConnectionParams.setConnectionTimeout(params, ctt);
        HttpConnectionParams.setSoTimeout(params, sott);
        httpClient = new DefaultHttpClient(cm);
    }


    @Override
    public HttpResult execute(HttpClientTask task) throws InvalidArgumentException {
        if(task == null)
            throw new InvalidArgumentException("httpclient task不能为空");
        return task.doHttpGet(httpClient);
    }



}
