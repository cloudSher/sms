package com.lashou.service.sms.biz.message.config;

import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;

import java.io.IOException;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public interface ConfigurationProvider<T> {

    void init();

    void destory();

    T loadConfig(String fileName) throws InvalidArgumentException, IOException;

}
