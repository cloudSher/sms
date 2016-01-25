package com.lashou.service.sms.api.rest;


import com.lashou.service.sms.domain.Caller;
import com.lashou.service.sms.domain.Message;
import com.lashou.service.sms.domain.MpsContext;
import com.lashou.service.sms.domain.OpResult;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/12
 */
public interface PushReqMsgRestService {

    /***
     *  发送短消息接口
     * @return
     */
    OpResult push_msg();


    String test(Long id);

}