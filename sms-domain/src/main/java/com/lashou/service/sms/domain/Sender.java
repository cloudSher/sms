package com.lashou.service.sms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by sher on 3/8/16.
 */
public class Sender implements Serializable {

    private static final long serialVersionUID = -2589044650206996448L;
    private String id;
    private String name;
    private int type;
    private int scope;
    private Map<String ,Object> parameters;
    private long  sendTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }
}
