package com.lashou.service.sms.domain;

import java.util.Date;

/**
 * Created by sher on 1/20/16.
 */
public class PushReq {

    private String reqId;
    private Date reqTrack;
    private String pushChannel;
    private String pushModel;
    private String pushScope;
    private boolean direct;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Date getReqTrack() {
        return reqTrack;
    }

    public void setReqTrack(Date reqTrack) {
        this.reqTrack = reqTrack;
    }

    public String getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(String pushChannel) {
        this.pushChannel = pushChannel;
    }

    public String getPushModel() {
        return pushModel;
    }

    public void setPushModel(String pushModel) {
        this.pushModel = pushModel;
    }

    public String getPushScope() {
        return pushScope;
    }

    public void setPushScope(String pushScope) {
        this.pushScope = pushScope;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
}
