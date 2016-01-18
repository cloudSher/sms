package com.lashou.service.sms.biz.sms.model;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class HttpResult {

    private int code;
    private String resMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
