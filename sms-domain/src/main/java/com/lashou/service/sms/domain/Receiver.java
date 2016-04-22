package com.lashou.service.sms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by sher on 3/8/16.
 */
public class Receiver implements Serializable{

    private static final long serialVersionUID = 7001637412192612802L;
    private String id;
    private int type;
    private Map<String ,Object> parameters;
    private Date receiveTime;

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

    public Date getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
