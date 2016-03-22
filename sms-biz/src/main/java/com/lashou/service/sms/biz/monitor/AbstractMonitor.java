package com.lashou.service.sms.biz.monitor;

import com.lashou.service.sms.biz.monitor.impl.SmsMsgMonitorData;
import com.lashou.service.sms.biz.monitor.impl.SmsMsgResponseData;

/**
 * Created by cloudsher on 2016/3/18.
 */
public  abstract class AbstractMonitor implements Monitor {


    public void start(){
        SmsMsgMonitorData monitorData = collectSmsMsgMonitorData();
        SmsMsgResponseData responseData = collectResponseMonitorData();
        if(monitorData!=null){
            //todo 处理失败之后的
            System.out.println(monitorData);
        }
        if(responseData != null){
            System.out.println(responseData);
        }
    }


    public void stop(){

    }

    public abstract SmsMsgMonitorData collectSmsMsgMonitorData();

    public abstract SmsMsgResponseData collectResponseMonitorData();

}
