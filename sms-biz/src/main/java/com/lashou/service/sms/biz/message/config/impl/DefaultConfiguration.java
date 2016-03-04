package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.ConfigurationProvider;
import com.lashou.service.sms.biz.message.config.constants.ChannelConfig;
import com.lashou.service.sms.biz.message.config.constants.ConfigType;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public class DefaultConfiguration<T> implements Configuration<T>{

    private static Logger logger = LoggerFactory.getLogger(DefaultConfiguration.class);
    private ConfigurationProvider provider;
    private ConcurrentHashMap<?,?> configContext;
    private Container container;

    @Override
    public Configuration getConfiguration() {
        return this;
    }


    @Override
    public void setConfigurationMap(Map<?, ?> context) {
        if(context!=null){
            this.configContext = (ConcurrentHashMap<?, ?>) context;
        }
    }

    @Override
    public Map<?, ?> getConfigContext() {
        return this.configContext;
    }

    @Override
    public Container getContrainer() {
        return container;
    }

    @Override
    public Container loadContainer() {
        if(configContext == null || configContext.size() == 0){
            logger.error("configuration is not load");
            throw new IllegalArgumentException("configuration is not load");
        }
        Container container = Container.getInstance();
        this.container = container;
        Map mpsContext =  container.getMpsContext();
        String channel_list = (String) configContext.get(ChannelConfig.CHANNEL_LIST);
        String channel_price = (String) configContext.get(ChannelConfig.CHANNEL_PRICE);
        mpsContext.put(ChannelConfig.CHANNEL_LIST,channel_list);
        mpsContext.put(ChannelConfig.CHANNEL_PRICE, channel_price);
        String[] cl = channel_list.split(",");

        Map<String,Object> fieldMap;
        Map<String, Channels> channelsMap = container.getChannelsMap();
        for(int i = 0 ; i < cl.length; i++){
            if(cl[i] != null){
                fieldMap = new HashMap<>();
                fieldMap.put("id",cl[i]);
                mpsContext.put(cl[i],fieldMap);
                Channels channels = new Channels();
                channelsMap.put(cl[i],channels);
            }
        }

        Iterator<? extends Map.Entry<?, ?>> iterator = configContext.entrySet().iterator();
        String oldcl = "";
        while(iterator.hasNext()){
            Map.Entry<?, ?> next = iterator.next();
            String key = (String) next.getKey();
            Object value = next.getValue();
            if(StringUtil.isNullOrEmpty(key)){
                if(key.contains(".")){
                    String pre_key = key.substring(0,key.indexOf("."));
                    String suf_key = key.substring(key.indexOf(".")+1,key.length());
                    fieldMap = (Map) mpsContext.get(pre_key);
                    if(fieldMap == null || fieldMap.size() == 0){
                        continue;
                    }
                    fieldMap.put(suf_key,value);
                }

            }
        }

        container.addInjectors().inject();
        return container;


    }


}
