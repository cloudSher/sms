package com.lashou.service.sms.biz.message.sms.model;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/18
 */
public class SmsMessage {

    private String mobile;

    private String message;

    private  String sp;

    private String appendId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getAppendId() {
        return appendId;
    }

    public void setAppendId(String appendId) {
        this.appendId = appendId;
    }
}
