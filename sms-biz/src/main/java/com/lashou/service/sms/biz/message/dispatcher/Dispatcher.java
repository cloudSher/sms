package com.lashou.service.sms.biz.message.dispatcher;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.constants.ContrainerStatus;
import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.ChannelsAccount;
import com.lashou.service.sms.biz.message.config.impl.ConfigurationManager;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.*;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * Created by sher on 2/1/16.
 */
public class Dispatcher {

    private ConfigurationManager configurationManager;
    private Container container;
    private volatile static Dispatcher dispatcher;
    public ConfigurationManager getConfigurationManager() {
        return this.configurationManager;
    }

    private volatile boolean isReload = false;

    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
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
        this.container.setStatus(ContrainerStatus.RUNNING);
    }

    public void reload_Configuration(){
        if(this.container!=null && this.container.isRunning()){
            container.setStatus(ContrainerStatus.INITING);
            container.getChannelsMap().clear();
            container.getMpsContext().clear();
            container.getChannels().clear();
            init_Configuration();
            this.isReload =true;
        }
    }


    private ConfigurationManager createConfigurationManager() {
        return  new ConfigurationManager(fileName);
    }



    public synchronized Result serviceAction(SmsRequestMsg msg){
        Invocation invocation = null;
        try {
            Container container = this.container;
            container.setFilterChain(new FilterChain());
//            container.addFilter(new SmsMobilesFilter());
//            container.addFilter(new SmsChannelsTypeFilter());
            container.addFilter(new SmsChannelsSignFilter());
            container.addFilter(new SmsOperatorRatioFilter());
            container.addFilter(new SmsMessageFilter());
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

    public synchronized Channels reSiftChannels(Channels channels,SmsRequestMsg msg){
        if(channels!=null){
            channels.setIsUsed(false);
        }
        List<Channels> cl = this.container.getChannels();
        Channels gc[] = new Channels[cl.size()-1];
        for(int i = 0,k=0 ; i < cl.size(); i++){
            Channels c = cl.get(i);
            if(c.getId().equals(channels.getId()))
                c.setIsUsed(false);
            else{
                gc[k++] = c;
            }
        }

        return chooseAccount(gc[new Random().nextInt(gc.length)],msg.getSendScope());
    }

    public Channels chooseAccount(Channels cl,int type){
        if(cl.getAccounts()!=null && cl.getAccounts().size() > 1){
            List<ChannelsAccount> accounts = cl.getAccounts();
            for(int i = 0 ; i< accounts.size(); i++){
                ChannelsAccount account = accounts.get(i);
                if(account.getType() == type){
                    cl.setAccount(account);
                }
            }
        }
        return cl;
    }

    public Container getContainer(){
        return this.container;
    }


    public void sendMonitorData() {
        container.getMonitor().start();
    }
}
