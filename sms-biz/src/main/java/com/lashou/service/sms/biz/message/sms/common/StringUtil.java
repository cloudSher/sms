package com.lashou.service.sms.biz.message.sms.common;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class StringUtil {

    /**
     * 是否为空
     * @param s
     * @return
     */
    public static boolean isNullOrEmpty(String s){
        return s == null || s.trim().length()==0;
    }


    /**
     * 数据格式： key:value;key:value;
     * @param str
     * @param map
     * @return
     */
    public static Map<String,Object> parseConfig(String str,Map<String,Object> map){
        if(map == null){
            map = new HashMap<>();
        }
        if(isNullOrEmpty(str) && map == null){
            return null;
        }else if(isNullOrEmpty(str)){
            return map;
        }

        if(str.contains(";")){
            String [] arr = str.split(";");
            for(String s : arr){
                if(s != null){
                    map.put(s.split(":")[0],s.split(":")[1]);
                }
            }
            return map;
        }else if(str.contains(",")){
            //TODO 数据格式定义
        }else if(str.contains(":")){
            map.put(str.split(":")[0],str.split(":")[1]);
            return map;
        }
        return null;
    }

    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    public static String encoding(String s,String character){
        try {
            return new String(s.getBytes(),character==null ? "UTF-8":character);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
