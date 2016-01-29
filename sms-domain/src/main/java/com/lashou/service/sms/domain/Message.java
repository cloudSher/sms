package com.lashou.service.sms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author Administrator
 * @date 2016-01-27
 *
 */
public class Message implements Serializable {
    /**  */
    private Integer id;

    /**  */
    private String content;

    /**  */
    private String from;

    /**  */
    private String to;

    /**  */
    private Date sendTime;

    /** 调用者Id */
    private Integer callerid;

    /**  */
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
        return callerid;
    }

    public void setCallerid(Integer callerid) {
        this.callerid = callerid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}