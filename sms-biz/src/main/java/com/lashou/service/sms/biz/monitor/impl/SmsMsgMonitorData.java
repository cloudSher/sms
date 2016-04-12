package com.lashou.service.sms.biz.monitor.impl;

/**
 * Created by cloudsher on 2016/3/22.
 */
public class SmsMsgMonitorData {

    private long succNum;
    private long failNum;
    private long laterNum;

    public long getSuccNum() {
        return succNum;
    }

    public void setSuccNum(long succNum) {
        this.succNum = succNum;
    }

    public long getFailNum() {
        return failNum;
    }

    public void setFailNum(long failNum) {
        this.failNum = failNum;
    }

    public long getLaterNum() {
        return laterNum;
    }

    public void setLaterNum(long laterNum) {
        this.laterNum = laterNum;
    }


    @Override
    public String toString() {
        return "SmsMsgMonitorData{" +
                "succNum=" + succNum +
                ", failNum=" + failNum +
                ", laterNum=" + laterNum +
                '}';
    }
}
