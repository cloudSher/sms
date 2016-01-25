package com.lashou.service.sms.domain;

import java.util.Objects;

/**
 * <p> OpResult</p>
 * <p> 返回结果集 </p>
 * <p> Copyright:版权所有 （c）2014-2015 </p>
 * <p> Company:lashou </p>
 *
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/11
 */
public class OpResult<R> {

    /**返回code 200是成功，400 错误 **/
    private int code;
    /** 返回的消息内容 **/
    private String msg;
    /** 返回成功是否  1 ：成功  0： 失败 **/
    private int status;
    /** 返回结果集 **/
    private R result;

    public OpResult(int code ,String msg,int status,R result){
        this.code = code;
        this.msg = msg;
        this.status = status;
        this.result = result;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public OpResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public OpResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public OpResult setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "OpResult{" +
                "Code=" + code +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                ", result="+result +
                '}';
    }

    public static <T> OpResult<T> createSucceed(String msg,T result){
        return new OpResult<T>(200,msg,1,result);
    }

    public static <T> OpResult<T> createFailMsg(String msg,T result){
        return new OpResult<T>(400,msg,0,result);
    }
}
