package com.lashou.service.sms.biz.monitor.impl;

import java.util.Date;

/**
 * Created by cloudsher on 2016/3/18.
 */
public class SmsMsgMonitorData {

    private String id;

    private String name;

    private String mobiles;

    private Date currTime;

    private String channelsName;

    private String channelsId;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public Date getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelsName() {
        return channelsName;
    }

    public void setChannelsName(String channelsName) {
        this.channelsName = channelsName;
    }

    public String getChannelsId() {
        return channelsId;
    }

    public void setChannelsId(String channelsId) {
        this.channelsId = channelsId;
    }
}
