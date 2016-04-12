package com.lashou.service.sms.biz.message.sms.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sher on 1/20/16.
 */
public class EncrptUtil {

    public static  String md5(String str){
        if(str ==null && str.length()==0){
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] array = md5.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String args[]){
        System.out.println(md5("DXX-BBX-010-1894006950b-d").toUpperCase());
    }
}
