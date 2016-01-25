package com.lashou.service.sms.dic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sher on 1/20/16.
 */
public enum  PushChannel {

    PROMOTE(1,"1","推送");

    private int key;
    private String value;
    private String desc;

    PushChannel(int key,String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static Map<Integer,PushChannel> keyMap = new HashMap<>();
    private static Map<String,PushChannel> valueMap = new HashMap<>();

    static{
        for(PushChannel channel: PushChannel.values()){
            keyMap.put(channel.key,channel);
            valueMap.put(channel.value,channel);
        }
    }

    public static PushChannel get(int key){
        return keyMap.get(key);
    }

    public static PushChannel getValue(String value){
        return valueMap.get(value);
    }
}
