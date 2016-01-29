package com.lashou.service.sms.biz.message.config.constants;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public enum  ConfigType {
    PROPERTIES("properties"),
    XML("xml");

    private String type;

    ConfigType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
