package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public class Result<T> {

    /**
     * code  0：无效，1：
     */
    private int code;
    private T t;

    public T getT() {
        return this.t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Result(int code,T t){
        this.code = code;
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



}
