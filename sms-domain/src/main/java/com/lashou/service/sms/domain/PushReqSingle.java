package com.lashou.service.sms.domain;

/**
 * Created by sher on 1/20/16.
 */
public class PushReqSingle {

    private PushMsg pushMsg;
    private String userId;

    public PushMsg getPushMsg() {
        return this.pushMsg;
    }

    public void setPushMsg(PushMsg pushMsg) {
        this.pushMsg = pushMsg;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
