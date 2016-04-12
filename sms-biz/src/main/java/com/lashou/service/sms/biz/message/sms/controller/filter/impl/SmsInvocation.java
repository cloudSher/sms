package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public class SmsInvocation implements Invocation {

    private Invoker invoker;

    private SmsRequestMsg msg;

    private Container container;

    private List<Channels> channels;

    private List<SmsRequestMsg> msgList;

    @Override
    public Invoker getInvoker() {
        return null;
    }


    public SmsRequestMsg getAttachment(){

        return msg;
    }

    public void setAttachment(SmsRequestMsg msg){
        this.msg = msg;
    }


    public void setContainer(Container container){
        this.container = container;
    }

    public Container getContainer(){
        return this.container;
    }

    public void addChannels(Channels channels){
        this.channels.add(channels);

    }

    public List<Channels> getChannels(){
        return this.channels;
    }

    public void setChannels(List<Channels> list){
        this.channels = list;
    }

    public List<SmsRequestMsg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<SmsRequestMsg> msgList) {
        this.msgList = msgList;
    }
}
