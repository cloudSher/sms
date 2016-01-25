package com.lashou.service.sms.biz.impl;


import com.lashou.service.sms.biz.PushService;
import com.lashou.service.sms.biz.message.queue.BasicQueue;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.domain.PushMsg;
import com.lashou.service.sms.domain.PushReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  发送消息service
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class PushServiceImpl implements PushService {

    private static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    private BasicQueue<Integer,PushReq> reqQueue;

    @Override
    public void req(PushReq pushReq) {
       if(pushReq == null){
           try {
               throw new InvalidArgumentException("请求消息为空");
           } catch (InvalidArgumentException e) {
               e.printStackTrace();
               logger.error("请求的消息为空",e);
           }
       }
        reqQueue.put(pushReq);
    }
}
