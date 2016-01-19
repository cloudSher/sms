package com.lashou.service.sms.biz.message.sms.exception;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class ReqQueueFullException extends Exception{

    public ReqQueueFullException(){
        super("请求队列已满");
    }
    public ReqQueueFullException(String msg){
        super(msg);
    }
}
