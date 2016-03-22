package com.lashou.service.sms.biz.message.sms.model;

import com.lashou.service.sms.biz.message.config.impl.Channels;

/**
 * Created by sher on 1/20/16.
 */
public class SmsRequestMsg {

    /** 手机号 **/
    private String mobiles;
    /** 消息 **/
    private String message;
    /** 通道 **/
    private String channel;
    /** 发送类型 **/
    private int sendScope;

    private Channels channels;

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Channels getChannels() {
        return channels;
    }

    public void setChannels(Channels channels) {
        this.channels = channels;
    }

    public int getSendScope() {
        return sendScope;
    }

    public void setSendScope(int sendScope) {
        this.sendScope = sendScope;
    }
}
