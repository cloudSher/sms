package com.lashou.service.sms.biz.monitor.impl;

import java.util.Date;

/**
 * Created by cloudsher on 2016/3/18.
 */
public class SmsMsgResponseData {

    private String id;

    private String name;

    private String mobiles;

    private Date currTime;

    private String channelsName;

    private String channelsId;

    private String description;

    private String responseCode;

    private String mobileOperator;

    private int operatorType;

    private int responseTime;

    private Byte isSuccess;

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

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public Byte getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Byte isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(String mobileOperator) {
        this.mobileOperator = mobileOperator;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    @Override
    public String toString() {
        return "SmsMsgResponseData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobiles='" + mobiles + '\'' +
                ", currTime=" + currTime +
                ", channelsName='" + channelsName + '\'' +
                ", channelsId='" + channelsId + '\'' +
                ", description='" + description + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", mobileOperator='" + mobileOperator + '\'' +
                ", operatorType=" + operatorType +
                ", responseTime=" + responseTime +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
