package com.lashou.service.sms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author Administrator
 * @date 2016-01-12
 *
 */
public class Message implements Serializable {
    /**  */
    private Integer id;

    /** 消息体 */
    private String content;

    /** 消息来源 */
    private String from;

    /**  消息目的*/
    private String to;

    /**  发送的时间 */
    private Date sendTime;

    /** 调用者Id */
    private Integer callerId;

    /**  消息类型 */
    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getCallerid() {
        return callerId;
    }

    public void setCallerid(Integer callerid) {
        this.callerId = callerid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}