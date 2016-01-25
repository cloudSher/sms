package com.lashou.service.sms.dic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sher on 1/20/16.
 */
public enum PushScope {

    USER(1,"1","用户");

    private final int key;
    private final String value;
    private final String desc;

    PushScope(int key ,String value,String desc) {
        this.desc= desc;
        this.key = key;
        this.value = value;
    }


    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    public String getDesc() {
        return desc;
    }

    private static Map<Integer,PushScope> keyMap = new HashMap<>();
    private static Map<String,PushScope> valueMap = new HashMap<>();

    static{
        for(PushScope scope : PushScope.values()){
            keyMap.put(scope.key,scope);
            valueMap.put(scope.value,scope);
        }
    }

    public static PushScope get(String key){
        return keyMap.get(key);
    }

    public static PushScope getValue(String value){
        return valueMap.get(value);
    }
}
