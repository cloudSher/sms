package com.lashou.service.sms.biz.logger;

import java.util.Date;

/**
 *
 *  日志类
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/18
 */
public class Logger<T> {

    private String loggerId;

    /** 日志类型 **/
    private int type;

    /** 日志级别 **/
    private int level;
    /**  日志内容 **/
    private T content;

    private Date currTime;

    /** 当前线程id **/
    private String curThreadId;

    public String getLoggerId() {
        return loggerId;
    }

    public void setLoggerId(String loggerId) {
        this.loggerId = loggerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Date getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public String getCurThreadId() {
        return curThreadId;
    }

    public void setCurThreadId(String curThreadId) {
        this.curThreadId = curThreadId;
    }
}
