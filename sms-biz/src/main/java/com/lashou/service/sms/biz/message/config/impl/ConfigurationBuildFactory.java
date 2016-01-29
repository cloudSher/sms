package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.ConfigurationProvider;
import com.lashou.service.sms.biz.message.config.constants.ConfigType;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/28
 */
public class ConfigurationBuildFactory {

    private ConfigurationProvider provider;

    public Configuration buildPropertiesConfig(String fileName) throws InvalidArgumentException, IOException {
        if(fileName == null || fileName.trim().length() == 0){
            throw new InvalidArgumentException("资源不能为空");
        }
        provider = new PropertiesConfigurationProvider();
        provider.init();
        Properties pro = (Properties) provider.loadConfig(fileName);
        Configuration config = new DefaultConfiguration();
//        config.
        return null;
    }
}
