package com.lashou.service.sms.biz;


import com.lashou.service.sms.domain.Message;
import com.lashou.service.sms.domain.OpResult;
import com.lashou.service.sms.domain.PushMsg;
import com.lashou.service.sms.domain.PushReq;

/**
 * 发送消息service
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/12
 */
public interface PushService {


  OpResult req(Message message);
}

