package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.config.ConfigurationProvider;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public class PropertiesConfigurationProvider implements ConfigurationProvider {

    private static Logger logger = LoggerFactory.getLogger(PropertiesConfigurationProvider.class);
    private Properties properties;
    private ClassLoader classLoader;

    @Override
    public void init() {
        this.properties = new Properties();
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Override
    public void destory() {
    }

    public  Properties loadConfig(String fileName) throws InvalidArgumentException, IOException {
        if(fileName == null || fileName.length() == 0){
            throw new InvalidArgumentException("load config fileName is not null");
        }
        if (properties == null){
            properties = new Properties();
        }
        if(classLoader == null){
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        InputStream stream = classLoader.getResourceAsStream(fileName);
        if(stream == null){
            logger.error("加载资源不存在");
            throw new InvalidArgumentException("加载的资源不存在");
        }
        properties.load(stream);
        logger.info("properties 资源已经加载成功");
        return properties;
    }
}
