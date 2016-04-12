package com.lashou.service.sms.biz.message.worker.impl;

import com.lashou.service.sms.biz.message.dispatcher.Dispatcher;
import com.lashou.service.sms.biz.message.queue.Queue;
import com.lashou.service.sms.biz.message.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by cloudsher on 2016/3/16.
 */
public class ConfigListenerWorker implements Worker{

    private String path;

    @Resource
    private Dispatcher dispatcher;

    @Resource
    private ConfigListenerInstance configInstance;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static Logger logger = LoggerFactory.getLogger(ConfigListenerWorker.class);

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get(path).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);

            while(true){
                WatchKey key = watchService.take();
                for(WatchEvent<?> event : key.pollEvents()){
                    if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
                        logger.info(Thread.currentThread().getName()+"："+event.context()+":配置文件已经修改");
                        configInstance.setSignal(false);
                        Thread.sleep(500);
                        if(!configInstance.isSignal()){
                            dispatcher.reload_Configuration();
                            configInstance.setSignal(true);
                        }
                    }

                }
                if(!key.reset()){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
