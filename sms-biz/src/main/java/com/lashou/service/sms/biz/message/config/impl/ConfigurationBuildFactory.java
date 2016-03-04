package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.ConfigurationProvider;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.domain.OpResult;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

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
        Map map = prop2Map(pro);
        if(map!=null){
            config.setConfigurationMap(map);
        }
        return config;
    }


    public Map prop2Map(Properties properties){
        Map<Object,Object> map = null;
        if(properties!=null){
            map = new ConcurrentHashMap<>();
            Enumeration<?> enumeration = properties.propertyNames();
            if(enumeration!=null){
                while (enumeration.hasMoreElements()){
                    Object element = enumeration.nextElement();
                    Object o = properties.get(element);
                    if(o != null){
                        map.put(element,o);
                    }
                }
            }
        }
        return map;
    }


}
