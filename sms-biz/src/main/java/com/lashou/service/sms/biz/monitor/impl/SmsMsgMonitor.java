package com.lashou.service.sms.biz.monitor.impl;

import com.lashou.service.sms.biz.monitor.AbstractMonitor;

/**
 * Created by cloudsher on 2016/3/18.
 */
public class SmsMsgMonitor extends AbstractMonitor {


    private SmsMsgMonitorData data;

    public SmsMsgMonitorData collectSmsMsgMonitorData(){
        SmsMsgMonitorData monitorData = data;
        return monitorData;
    }

    public SmsMsgMonitorData collectionMonitorData(SmsMsgMonitorData data){
        return this.data = data;
    }

}
