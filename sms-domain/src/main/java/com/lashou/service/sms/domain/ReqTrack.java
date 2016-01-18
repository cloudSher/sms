package com.lashou.service.sms.domain;

import java.util.Date;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/18
 */
public class ReqTrack {

    /**发送通道类型,同deviceType*/
    private Character chlType;

    /**任务ID*/
    private String taskId;

    /**请求返回状态*/
    private String status;

    /**请求返回描述*/
    private String descs;

    /**拟将发送总数*/
    private int msgTotal;

    /**下发数*/
    private int msgDown;

    /**最后一次更新时间*/

    private Date lastModifyTstap;


    public Character getChlType() {
        return chlType;
    }

    public void setChlType(Character chlType) {
        this.chlType = chlType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public int getMsgTotal() {
        return msgTotal;
    }

    public void setMsgTotal(int msgTotal) {
        this.msgTotal = msgTotal;
    }

    public int getMsgDown() {
        return msgDown;
    }

    public void setMsgDown(int msgDown) {
        this.msgDown = msgDown;
    }


    public Date getLastModifyTstap() {
        return lastModifyTstap;
    }

    public void setLastModifyTstap(Date lastModifyTstap) {
        this.lastModifyTstap = lastModifyTstap;
    }
}

