package com.lashou.service.sms.biz.monitor;

import com.lashou.service.sms.biz.monitor.impl.SmsMsgMonitorData;

/**
 * Created by cloudsher on 2016/3/18.
 */
public  abstract class AbstractMonitor implements Monitor {


    public void start(){
        SmsMsgMonitorData monitorData = collectSmsMsgMonitorData();
        if(monitorData!=null){
            //todo 处理失败之后的
        }
    }


    public void stop(){

    }

    public abstract SmsMsgMonitorData collectSmsMsgMonitorData();

}
