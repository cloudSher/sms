package com.lashou.service.sms.biz.sms.worker.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.lashou.service.sms.biz.sms.queue.BasicQueue;
import com.lashou.service.sms.biz.sms.sender.impl.SmsSender;
import com.lashou.service.sms.biz.sms.worker.Worker;


import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class SmsWorker implements Worker {

    private Logger logger = LoggerFactory.getLogger(SmsWorker.class);

    @Resource
    private BasicQueue<Integer,Objects> messageQueue;
    @Resource
    private SmsSender smsSender;


    @Override
    public void run() {
        logger.info("smsWorker 正在执行中....");
        long startTime = System.currentTimeMillis();
        while(true){
            try{
                messageQueue.take(true);
            }catch (Exception e){
                logger.error("worker 执行失败"+Thread.currentThread().getName(),e);
            }

        }


    }

}
