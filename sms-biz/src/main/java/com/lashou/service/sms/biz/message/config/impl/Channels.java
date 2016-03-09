package com.lashou.service.sms.biz.message.config.impl;

/**
 * 渠道商
 * Created by sher on 1/29/16.
 */
public class Channels {

    private String id;

    private String channelId;

    private String channelName;

    private String userId;

    private String user;

    private String passwordId;

    private String password;

    private String mobileId;

    private String msgId;

    private String encode;

    private int type;

    private String url;

    private String companyId;

    private int weight;

    private int maxContentSize;

    private int maxMobilesSize;

    private boolean isUsed;

    private int priority;   //1:最高

    private String validDate;

    private int httpclientMaxTotal;

    private int httpclientConnectionTimeout;

    private int httpclientSoTimeout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHttpclientConnectionTimeout() {
        return this.httpclientConnectionTimeout;
    }

    public void setHttpclientConnectionTimeout(int httpclientConnectionTimeout) {
        this.httpclientConnectionTimeout = httpclientConnectionTimeout;
    }

    public int getHttpclientSoTimeout() {
        return this.httpclientSoTimeout;
    }

    public void setHttpclientSoTimeout(int httpclientSoTimeout) {
        this.httpclientSoTimeout = httpclientSoTimeout;
    }

    public int getHttpclientMaxTotal() {
        return this.httpclientMaxTotal;
    }

    public void setHttpclientMaxTotal(int httpclientMaxTotal) {
        this.httpclientMaxTotal = httpclientMaxTotal;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswordId() {
        return this.passwordId;
    }

    public void setPasswordId(String passwordId) {
        this.passwordId = passwordId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileId() {
        return this.mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getEncode() {
        return this.encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
