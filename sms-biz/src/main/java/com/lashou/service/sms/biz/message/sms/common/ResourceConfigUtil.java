package com.lashou.service.sms.biz.message.sms.common;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/18
 */
public class ResourceConfigUtil {

    private static Logger logger = LoggerFactory.getLogger(ResourceConfigUtil.class);
    private final static String SOURCE = "properties/config/inner.config";

    /***
     * 解析资源
     * @param source
     * @return
     */
    private static Properties parse(String source){
        if(StringUtil.isNullOrEmpty(source)){
            throw new RuntimeException("资源名称不能为空");
        }
        Properties properties = null;
        try{
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(source);
            if(resourceAsStream!=null){
                properties = new Properties();
                properties.load(resourceAsStream);
            }
        }catch (Exception e){
            logger.error("资源加载出错，");
        }

        return properties;
    }

    /**
     * 获取config中参数值
     * @param key
     * @param def
     * @return
     */
    public static String getValueOfString(String key,String def){
        Properties properties = parse(SOURCE);
        if(properties!=null){
            Object orDefault = properties.getProperty(key, def);
            return orDefault.toString();
        }
        return null;
    }


    public static Integer getValueOfInteger(String key,int def){
        Properties properties = parse(SOURCE);
        if(properties!= null){
            Object orDefault = properties.getProperty(key, def+"");
            return Integer.valueOf(orDefault.toString());
        }
        return null;
    }
}
