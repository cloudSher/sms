package com.lashou.service.sms.biz.message.config.impl;

import java.util.Map;

/**
 * Created by sher on 2/2/16.
 */
public class MessageContext {

    private static final String CONTAINER = "lashou.message.container";

    private Map<String,Object> context;


    public Map<String, Object> getContext() {
        return this.context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public void put(String key, Object obj){
        context.put(key,obj);
    }

    public Object get(String key){
        return context.get(key);
    }

    public Container getContainer(){
        return (Container) context.get(CONTAINER);
    }

    public void setContainer(Container container){
        context.put(CONTAINER,container);
    }


}
