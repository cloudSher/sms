package com.lashou.service.sms.biz.message.dispatcher;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.ConfigurationManager;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.config.impl.DefaultConfiguration;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.*;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

import java.util.List;

/**
 * Created by sher on 2/1/16.
 */
public class Dispatcher {


    private ConfigurationManager configurationManager;
    private Container container;
    private static Dispatcher dispatcher;
    public ConfigurationManager getConfigurationManager() {
        return this.configurationManager;
    }

    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    //
    public static Dispatcher getDispatcher(){
        if(dispatcher == null){
            synchronized (dispatcher){
                if(dispatcher == null){
                    dispatcher = new Dispatcher();
                }
            }
        }
        return dispatcher;
    }

    private Dispatcher(){

    }

    //init dispatcher
    public void init(){
        init_Configuration();
    }

    public void init_Configuration(){
        if(configurationManager == null){
            configurationManager = createConfigurationManager();
        }
        Configuration configuration = configurationManager.getConfiguration();
        Container contrainer = configuration.getContrainer();
        this.container = contrainer;
    }

    private ConfigurationManager createConfigurationManager() {
        return  new ConfigurationManager();
    }



    public void serviceAction(){

    }

    public synchronized List<Channels>  getChannels(SmsRequestMsg msg){
        List<Channels> channelsList = null;
        try {
            Container container = dispatcher.getContainer();
            container.setFilterChain(new FilterChain());
            container.addFilter(new SmsMessageFilter());
            container.addFilter(new SmsMobilesFilter());
            container.addFilter(new SmsChannelsTypeFilter());
            Invoker invoker = container.invoke();
            Invocation invocation = new SmsInvocation();
            invocation.setAttachment(msg);
            invocation.setContainer(container);
            invocation.setChannels(container.getChannels());
            Result result = invoker.invoke(invocation);
            if(result!=null){
                if(result.getCode() == 1){
                    channelsList = invocation.getChannels();
                }
            }
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        return channelsList;
    }


    public Container getContainer(){
        return this.container;
    }


}
