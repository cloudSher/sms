package com.lashou.service.sms.biz.monitor.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.monitor.AbstractMonitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cloudsher on 2016/3/18.
 */
public class SmsMsgMonitor extends AbstractMonitor {

    //执行成功个数
    private AtomicLong succNum = new AtomicLong(0);
    //执行失败个数
    private AtomicLong  failNum = new AtomicLong(0);

    private AtomicLong  laterNum = new AtomicLong(0);

    private Map<String,SmsMsgMonitorData> monitorDataMap = new ConcurrentHashMap<>();


    public void incSuccessNum(){
        succNum.incrementAndGet();
    }

    public void incFailNum(){
        failNum.incrementAndGet();
    }

    public void incLaterNum(){
        laterNum.incrementAndGet();
    }

    private SmsMsgResponseData data;

    public SmsMsgMonitorData collectSmsMsgMonitorData(){
        SmsMsgMonitorData monitorData = new SmsMsgMonitorData();
        monitorData.setSuccNum(succNum.getAndSet(0));
        monitorData.setFailNum(failNum.getAndSet(0));
        monitorData.setLaterNum(laterNum.getAndSet(0));
        return monitorData;
    }

    public SmsMsgResponseData collectResponseMonitorData(){
        return this.data;
    }

    public synchronized SmsMsgMonitorData incChannelsMonitorData(Channels channel,int code){
        SmsMsgMonitorData monitorData = null;
        if(channel!= null){
            monitorData = monitorDataMap.get(channel.getId());
            if(monitorData  == null)
                monitorData = new SmsMsgMonitorData();
            if(code == 1)
                monitorData.setSuccNum(monitorData.getSuccNum()+1);
            else if(code == 0)
                monitorData.setFailNum(monitorData.getFailNum()+1);
            monitorDataMap.put(channel.getId(),monitorData);
        }
        return monitorData;
    }

    public SmsMsgResponseData collectionMonitorData(SmsMsgResponseData data){
        return this.data = data;
    }

}
