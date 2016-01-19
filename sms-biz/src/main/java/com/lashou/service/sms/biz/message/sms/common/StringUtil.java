package com.lashou.service.sms.biz.message.sms.common;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String s){
        return s == null || s.trim().length()==0;
    }
}
