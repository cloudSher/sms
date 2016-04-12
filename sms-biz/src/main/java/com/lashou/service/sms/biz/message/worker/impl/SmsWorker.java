package com.lashou.service.sms.biz.message.worker.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.lashou.service.sms.biz.message.config.constants.ContrainerStatus;
import com.lashou.service.sms.biz.message.dispatcher.Dispatcher;
import com.lashou.service.sms.biz.message.queue.BasicQueue;
import com.lashou.service.sms.biz.message.sender.impl.SmsSender;
import com.lashou.service.sms.biz.message.worker.Worker;
import com.lashou.service.sms.domain.Message;

import javax.annotation.Resource;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class SmsWorker implements Worker {

    private Logger logger = LoggerFactory.getLogger(SmsWorker.class);

    @Resource
    private BasicQueue<Integer,Message> smsMessageQueue;

    @Resource
    private SmsSender smsSender;

    @Resource
    private ConfigListenerInstance configInstance;

    private volatile boolean reload = false;


    @Override
    public void run() {
        logger.info("smsWorker 正在执行中....");
        long startTime = System.currentTimeMillis();
        while(true){
            try{
                while(!configInstance.isSignal()){
                    reload = false;
                    Thread.sleep(1 * 1000);
                }
                Message message = smsMessageQueue.take(true);

                if(message != null){
                    smsSender.broadCast(message);
                }
            }catch (Exception e){
                logger.error("smsWorker 执行失败"+Thread.currentThread().getName(),e);
            }
            logger.info("发送短信完成，花费时间："+(System.currentTimeMillis()-startTime)+"毫秒");
        }

    }

}
