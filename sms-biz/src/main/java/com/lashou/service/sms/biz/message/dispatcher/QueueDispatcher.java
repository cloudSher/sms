package com.lashou.service.sms.biz.message.dispatcher;

import com.lashou.service.sms.biz.message.queue.BasicQueue;
import com.lashou.service.sms.biz.message.queue.MessageRabbitPublishService;
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

    @Resource
    private MessageRabbitPublishService msgPublisher;

    private int priority;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public OpResult dispatchQueue(Message message){
        if(message!=null){
            MessageType type = MessageType.getKey(message.getHeader().getType());

            switch (type){
                case SMS:
                    if(message.getHeader().getPriority() <= priority){
                        smsMessageQueue.put(message);
                    }else if(message.getHeader().getPriority() > priority){
                        msgPublisher.sendMessage(message);
                    }
                    break;
                case EMAIL:
                    emailMessageQueue.put(message);
                    break;
                case PUSH:
                    pushMessageQueue.put(message);
                    break;
            }

        }

        return OpResult.createSucceed("存放队列成功",message);
    }
}
