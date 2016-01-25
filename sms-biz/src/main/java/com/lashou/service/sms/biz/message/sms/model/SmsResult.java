package com.lashou.service.sms.biz.message.sms.model;

/**
 * Created by sher on 1/21/16.
 */
public class SmsResult<T> {

    private int code;
    private String msg;
    private T result;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <R> SmsResult succeed(String msg,R obj){
        SmsResult result = new SmsResult();
        result.setCode(10000);
        result.setMsg(msg);
        result.setResult(obj);
        return result;
    }

    public static SmsResult failed(int code,String msg){
        SmsResult smsResult = new SmsResult();
        smsResult.setCode(code);
        smsResult.setMsg(msg);
        return smsResult;
    }
}
