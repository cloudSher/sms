package com.lashou.service.sms.api.rest;

import com.lashou.service.mps.domain.Caller;
import com.lashou.service.mps.domain.Message;
import com.lashou.service.mps.domain.MpsContext;
import com.lashou.service.mps.domain.OpResult;

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
    OpResult push_msg(Caller caller, Message msg, MpsContext context);


    String test(Long id);

}
