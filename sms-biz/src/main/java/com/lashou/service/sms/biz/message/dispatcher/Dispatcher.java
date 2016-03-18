package com.lashou.service.sms.biz.message.dispatcher;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.constants.ContrainerStatus;
import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.ConfigurationManager;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.config.impl.DefaultConfiguration;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.*;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import com.lashou.service.sms.biz.monitor.impl.SmsMsgMonitorData;

import java.util.List;
import java.util.Random;

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

    private volatile boolean isReload = false;


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
        this.container.setStatus(ContrainerStatus.RUNNING);
    }

    public void reload_Configuration(){
        if(this.container!=null && this.container.isRunning()){
            container.setStatus(ContrainerStatus.INITING);
            init_Configuration();
            this.isReload =true;
        }
    }


    private ConfigurationManager createConfigurationManager() {
        return  new ConfigurationManager();
    }



    public synchronized Result serviceAction(SmsRequestMsg msg){
        Invocation invocation = null;
        try {
            Container container = dispatcher.getContainer();
            container.setFilterChain(new FilterChain());
//            container.addFilter(new SmsMessageFilter());
//            container.addFilter(new SmsMobilesFilter());
            container.addFilter(new SmsChannelsTypeFilter());
            container.addFilter(new SmsOperatorRatioFilter());
            Invoker invoker = container.invoke();
            invocation = new SmsInvocation();
            invocation.setAttachment(msg);
            invocation.setContainer(container);
            invocation.setChannels(container.getChannels());
            Result result = invoker.invoke(invocation);
           return result;
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized Channels reSiftChannels(Channels channels){
        if(channels!=null){
            channels.setIsUsed(false);
        }
        List<Channels> cl = dispatcher.getContainer().getChannels();
        Channels gc[] = new Channels[cl.size()-1];
        for(int i = 0,k=0 ; i < cl.size(); i++){
            Channels c = cl.get(i);
            if(c.getId().equals(channels.getId()))
                c.setIsUsed(false);
            else{
                gc[k++] = c;
            }
        }
        return gc[new Random().nextInt(gc.length)];
    }

    public Container getContainer(){
        return this.container;
    }


    public void sendMonitorData(SmsMsgMonitorData monitorData) {
        dispatcher.container.getMonitor().start();
    }
}
