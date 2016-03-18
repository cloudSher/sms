package com.lashou.service.sms.biz.message.worker.impl;

/**
 * Created by cloudsher on 2016/3/17.
 */
public class ConfigListenerInstance {


    private volatile boolean signal = true;

    public synchronized void setSignal(boolean sign){
        this.signal = sign;
    }

    public boolean isSignal(){
        return signal;
    }

}
