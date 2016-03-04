package com.lashou.service.sms.biz.message.config.impl;

/**
 * 渠道商
 * Created by sher on 1/29/16.
 */
public class Channels {

    private String channelId;

    private String channelName;

    private String userId;

    private String pwd;

    private String companyId;

    private int weight;

    private int maxContentSize;

    private int maxMobilesSize;

    private boolean isUsed;

    private int priority;   //1:最高

    private String validDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxContentSize() {
        return this.maxContentSize;
    }

    public void setMaxContentSize(int maxContentSize) {
        this.maxContentSize = maxContentSize;
    }

    public int getMaxMobilesSize() {
        return this.maxMobilesSize;
    }

    public void setMaxMobilesSize(int maxMobilesSize) {
        this.maxMobilesSize = maxMobilesSize;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getValidDate() {
        return this.validDate;
    }

    public void setValidDate(String validDate) {

        this.validDate = validDate;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
