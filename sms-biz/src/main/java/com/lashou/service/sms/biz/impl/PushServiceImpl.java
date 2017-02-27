package com.lashou.service.sms.biz.impl;


import com.alibaba.fastjson.JSONObject;
import com.lashou.service.sms.biz.PushService;
import com.lashou.service.sms.biz.message.dispatcher.QueueDispatcher;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.domain.Message;
import com.lashou.service.sms.domain.MessageBody;
import com.lashou.service.sms.domain.OpResult;
import com.lashou.service.sms.mapper.MessageBodyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 *  发送消息service
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class PushServiceImpl implements PushService {

    private static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    @Resource
    private QueueDispatcher queueDispatcher;

    @Resource
    private MessageBodyMapper messageBodyMapper;

    @Override
    public OpResult req(Message pushReq) {
       if(pushReq == null){
        try {
            throw new InvalidArgumentException("请求消息为空");
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
            logger.error("请求的消息为空",e);
        }
    }
    insertMessageBody(pushReq);
        return queueDispatcher.dispatchQueue(pushReq);
    }


    //保存message
    public void insertMessageBody(Message msg){
        MessageBody body = new MessageBody();
        body.setMessageid(msg.getMsgId());
        body.setContent(msg.getBody().getContent());
        body.setHeader(JSONObject.toJSONString(msg.getHeader()));
        body.setReceiver(JSONObject.toJSONString(msg.getBody().getReceiver()));
        body.setScope(msg.getBody().getSender().getScope());
        body.setSender(JSONObject.toJSONString(msg.getBody().getSender()));
        body.setSendTime(new Date());
        body.setType(msg.getBody().getSender().getType());
        messageBodyMapper.insert(body);
    }
}
