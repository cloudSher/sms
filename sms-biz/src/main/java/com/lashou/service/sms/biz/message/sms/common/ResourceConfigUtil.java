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

    /***
     * 解析资源
     * @param source
     * @return
     */
    private static Properties parse(String source){
        if(StringUtil.isNullOrEmpty(source)){
            throw new RuntimeException("资源名称不能为空");
        }
        try{
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(source);
            if(resourceAsStream!=null){

            }
        }catch (Exception e){
            logger.error("资源加载出错，");
        }

        return null;
    }
}
