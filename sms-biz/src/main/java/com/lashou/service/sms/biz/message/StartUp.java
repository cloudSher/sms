package com.lashou.service.sms.biz.message;

import com.lashou.service.sms.biz.message.worker.Worker;
import com.lashou.service.sms.biz.message.worker.impl.ConfigListenerWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sher on 1/21/16.
 */
public class StartUp {

    private static Logger logger = LoggerFactory.getLogger(StartUp.class);

    private int smsWorkerThreand;

    private ExecutorService smsWorkerPool;

    private ExecutorService configListenerPool;

    @Resource
    private Worker smsWorker;
//
//    @Resource
//    private ConfigListenerWorker configListenerWorker;

    public void startup(){
        logger.info("work  startup.....");
        smsWorkerPool = Executors.newFixedThreadPool(smsWorkerThreand);
        for(int i = 0 ; i < smsWorkerThreand; i++){
            smsWorkerPool.execute(smsWorker);
        }

//        logger.info("config file listener startup ....");
//        configListenerPool =Executors.newFixedThreadPool(1);
//        configListenerPool.execute(configListenerWorker);

        logger.info("work 启动完成");
    }

    public void stop(){
        logger.info("work thread stop...");
        smsWorkerPool.shutdown();
        configListenerPool.shutdown();
    }



    public void setSmsWorkerThreand(int smsWorkerThreand) {
        this.smsWorkerThreand = smsWorkerThreand;
    }
}
