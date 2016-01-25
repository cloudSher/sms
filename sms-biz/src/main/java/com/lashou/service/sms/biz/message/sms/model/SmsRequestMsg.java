package com.lashou.service.sms.biz.message.sms.model;

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
}
