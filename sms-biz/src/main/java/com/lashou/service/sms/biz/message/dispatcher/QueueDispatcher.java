package com.lashou.service.sms.biz.message.dispatcher;

import com.lashou.service.sms.biz.message.queue.BasicQueue;
import com.lashou.service.sms.dic.MessageType;
import com.lashou.service.sms.domain.Message;
import com.lashou.service.sms.domain.OpResult;

import javax.annotation.Resource;

/**
 * Created by sher on 3/8/16.
 */
public class QueueDispatcher {


    @Resource
    private BasicQueue<Integer,Message> smsMessageQueue;

    @Resource
    private BasicQueue emailMessageQueue;

    @Resource
    private BasicQueue pushMessageQueue;

    public OpResult dispatchQueue(Message message){
        if(message!=null){
            MessageType type = MessageType.getKey(message.getHeader().getType());

            switch (type){
                case SMS:
                    smsMessageQueue.put(message);
                    break;
                case EMAIL:
                    emailMessageQueue.put(message);
                    break;
                case PUSH:
                    pushMessageQueue.put(message);
                    break;
            }

        }

        return OpResult.createSucceed("存放队列成功",null);
    }
}
