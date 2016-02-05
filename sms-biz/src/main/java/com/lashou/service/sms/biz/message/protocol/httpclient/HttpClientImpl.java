package com.lashou.service.sms.biz.message.protocol.httpclient;


import com.lashou.service.sms.biz.message.protocol.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/2/5
 */
public class HttpClientImpl implements HttpClient{

    private static org.apache.http.client.HttpClient httpClient;
    private static PoolingClientConnectionManager cm;
    private static HttpParams params =new BasicHttpParams();
    private static SchemeRegistry schemeRegistry = new SchemeRegistry();


    static{
        schemeRegistry.register(
                new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(100);
        HttpConnectionParams.setConnectionTimeout(params, 2000);
        HttpConnectionParams.setSoTimeout(params, 4000);
        httpClient = new DefaultHttpClient(cm);

    }

    @Override
    public  org.apache.http.client.HttpClient getHttpClient() {
        return httpClient;
    }

}
