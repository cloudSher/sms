package com.lashou.service.sms.domain;

import java.util.List;

/**
 * Created by sher on 1/20/16.
 */
public class PushReqBroad extends PushReq{

    private PushMsg pushMsg;

    private List<String> userIds;

    public PushMsg getPushMsg() {
        return this.pushMsg;
    }

    public void setPushMsg(PushMsg pushMsg) {
        this.pushMsg = pushMsg;
    }

    public List<String> getUserIds() {
        return this.userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
