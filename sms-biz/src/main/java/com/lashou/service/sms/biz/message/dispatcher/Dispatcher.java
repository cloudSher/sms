package com.lashou.service.sms.biz.message.dispatcher;

import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.config.impl.ConfigurationManager;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.config.impl.DefaultConfiguration;

/**
 * Created by sher on 2/1/16.
 */
public class Dispatcher {


    private ConfigurationManager configurationManager;
    private static ThreadLocal<Dispatcher> instance = new ThreadLocal<>();
    private Container container;

    public ConfigurationManager getConfigurationManager() {
        return this.configurationManager;
    }

    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    //
    public static Dispatcher getDispatcher(){
        return instance.get();
    }

    public static void setDispatcher(Dispatcher dispatcher){
        instance.set(dispatcher);
    }

    public Dispatcher(){

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

    public Container getContainer(){
        return this.container;
    }


}
