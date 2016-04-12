package com.lashou.service.sms.biz.message.sms.controller.filter;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.Result;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

import java.util.List;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public interface Invocation {

    Invoker getInvoker();


    void setAttachment(SmsRequestMsg msg);


    SmsRequestMsg getAttachment();


    void setContainer(Container container);


    Container getContainer();


    void addChannels(Channels channels);

    List<Channels>  getChannels();


    void setChannels(List<Channels> list);

    List<SmsRequestMsg> getMsgList();

    void setMsgList(List<SmsRequestMsg> msgList);


}
