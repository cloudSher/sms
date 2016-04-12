package com.lashou.service.sms.biz.message.handler.mq.impl;

import com.lashou.service.sms.biz.message.handler.mq.RabbitSubscriberMessageHandler;
import com.lashou.service.sms.biz.message.queue.BasicQueue;
import com.lashou.service.sms.biz.message.sender.Sender;
import com.lashou.service.sms.biz.message.sender.impl.SmsSender;
import com.lashou.service.sms.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by cloudsher on 2016/4/6.
 */
public class MessageRabbitHandler implements RabbitSubscriberMessageHandler {

    private Logger logger = LoggerFactory.getLogger(MessageRabbitHandler.class);

    @Resource
    private SmsSender smsSender;

    @Resource
    private BasicQueue<Integer,Message> smsMessageQueue;

    private int maxMMQueue;

    public void setMaxMMQueue(int maxMMQueue) {
        this.maxMMQueue = maxMMQueue;
    }

    @Override
    public void handleMessage(Object message) {
        logger.debug("mq receive message start ...:",message);
        if(message instanceof Message){
            try {
                Message msg = (Message) message;
                while (smsMessageQueue.queueSize() > maxMMQueue){
                    Thread.sleep( 5 * 1000);
                }
                smsSender.broadCast(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("mq handleMessage error,{}",e);
            }
        }
        logger.debug("mq receive message end ...",message);
    }
}
