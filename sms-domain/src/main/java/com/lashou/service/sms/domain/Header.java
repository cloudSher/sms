package com.lashou.service.sms.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sher on 3/8/16.
 */
public class Header implements Serializable{

    private static final long serialVersionUID = 4895767103457574327L;
    private String appId;
    private int type;
    private Map<String,Object> parameters;
    private int priority;
    private int expireSeconds;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getExpireSeconds() {
        return this.expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
