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
public class Caller implements Serializable {
    /** 调用者Id */
    private Integer callerid;

    /** 调用者名称 */
    private String callername;

    /** 调用者所属类型 */
    private String type;

    /** 业务场景描述 */
    private String description;

    /** 调用接口时间 */
    private Date calltime;

    private static final long serialVersionUID = 1L;

    public Integer getCallerid() {
        return callerid;
    }

    public void setCallerid(Integer callerid) {
        this.callerid = callerid;
    }

    public String getCallername() {
        return callername;
    }

    public void setCallername(String callername) {
        this.callername = callername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCalltime() {
        return calltime;
    }

    public void setCalltime(Date calltime) {
        this.calltime = calltime;
    }
}