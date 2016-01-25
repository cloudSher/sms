package com.lashou.service.sms.dic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sher on 1/20/16.
 */
public enum PushModel {

    BATCH(1,"1","批处理模式"),
    BROADCAST(2,"2","广播模式"),
    ;

    final int key;
    final String value;
    final String desc;

    PushModel(int key,String value,String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
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

    private static Map<Integer, PushModel> keyMap = new HashMap<Integer, PushModel>();
    private static Map<String, PushModel> valueMap = new HashMap<String, PushModel>();

    static {
        for (PushModel key : PushModel.values()) {
            keyMap.put(key.key, key);
            valueMap.put(key.value, key);
        }
    }

    public static PushModel get(Integer key) {
        return keyMap.get(key);
    }

    public static PushModel getByValue(Character value) {
        return valueMap.get(value);
    }
}
