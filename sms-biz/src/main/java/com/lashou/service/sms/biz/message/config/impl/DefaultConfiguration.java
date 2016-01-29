package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.ConfigurationProvider;
import com.lashou.service.sms.biz.message.config.constants.ConfigType;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;

import java.util.Map;
import java.util.Properties;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public class DefaultConfiguration<T> implements Configuration<T>{

    private ConfigurationProvider provider;
    private Container container;


    @Override
    public Configuration getConfiguration() {
        return null;
    }


    public void prop2Map(Properties pro){
        if(pro != null){

        }

    }


}
