package com.lashou.service.sms.biz.message.worker.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.lashou.service.sms.biz.message.queue.BasicQueue;
import com.lashou.service.sms.biz.message.sender.impl.SmsSender;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.message.worker.Worker;
import com.lashou.service.sms.dic.PushScope;
import com.lashou.service.sms.domain.PushReq;


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
    private BasicQueue<Integer,PushReq> smsMessageQueue;

    @Resource
    private SmsSender smsSender;


    @Override
    public void run() {
        logger.info("smsWorker 正在执行中....");
        long startTime = System.currentTimeMillis();
        while(true){
            try{
                PushReq req = smsMessageQueue.take(true);
                String scope = req.getPushScope();
                PushScope ps = PushScope.getValue(scope);
                switch (ps){
                    case USER:
                        smsSender.broadCast(req);
                        break;
                }
            }catch (Exception e){
                logger.error("smsWorker 执行失败"+Thread.currentThread().getName(),e);
            }
            logger.info("发送短信完成，花费时间："+(System.currentTimeMillis()-startTime)+"毫秒");
        }

    }

}
