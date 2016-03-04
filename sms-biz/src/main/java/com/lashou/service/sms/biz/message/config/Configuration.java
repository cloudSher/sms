package com.lashou.service.sms.biz.message.config;

import com.lashou.service.sms.biz.message.config.constants.ConfigType;
import com.lashou.service.sms.biz.message.config.impl.Container;

import java.util.Map;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public interface Configuration<T> {


    Configuration getConfiguration();

    void setConfigurationMap(Map<?,?> context);

    Map<?,?> getConfigContext();

    Container getContrainer();

    Container loadContainer();

}
