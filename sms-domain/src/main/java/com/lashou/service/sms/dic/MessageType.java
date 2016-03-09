package com.lashou.service.sms.dic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sher on 3/8/16.
 */
public enum  MessageType {

    SMS(1,"1","短信"),

    EMAIL(2,"2","邮件"),

    PUSH(3,"3","推送");


    private int key;
    private String value;
    private String content;

    MessageType(int key,String value,String content) {
        this.key = key;
        this.value = value;
        this.content = content;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private static Map<Integer,MessageType> keyMap = new HashMap<>();
    private static Map<String,MessageType> valueMap = new HashMap<>();

    static{

        for(MessageType type : MessageType.values()){
            keyMap.put(type.getKey(),type);
            valueMap.put(type.getValue(),type);
        }
    }

    public static MessageType getKey(int key){
        return keyMap.get(key);
    }

    public static MessageType getValue(String value){
        return valueMap.get(value);
    }
}
