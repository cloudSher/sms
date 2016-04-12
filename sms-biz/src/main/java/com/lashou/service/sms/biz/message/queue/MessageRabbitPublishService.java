package com.lashou.service.sms.biz.message.queue;

import com.lashou.service.sms.biz.message.queue.mq.RabbitPublisherService;
import com.lashou.service.sms.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by cloudsher on 2016/4/6.
 */
public class MessageRabbitPublishService {


    private Logger logger = LoggerFactory.getLogger(MessageRabbitPublishService.class);

    /** 消息 routeKey */
    public static final String MESSAGE_REFER_ROUTE_KEY = "messageReferRoute";

    @Resource
    private RabbitPublisherService messageRabbitPublisherService;


    public void sendMessage(Message message){
        logger.debug("mq-send:"+message);

        if(message != null){
            try{
                messageRabbitPublisherService.send(MESSAGE_REFER_ROUTE_KEY,message);
                logger.info("send data to mq======>msg is:{}", message.toString());
            }catch (Exception e){
                logger.error("failed to send data to mq======>msg is:{}", message.toString());
            }
        }

    }



}
