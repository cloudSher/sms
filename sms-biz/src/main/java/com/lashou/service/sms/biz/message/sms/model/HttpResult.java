package com.lashou.service.sms.biz.message.sms.model;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class HttpResult {

    private int code;
    private String resMsg;
    private String responseCode;

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

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
