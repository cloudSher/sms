package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.listeners.Listener;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.FilterChain;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.SmsInvocation;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/29
 */
public class Container {

    private static Logger logger = LoggerFactory.getLogger(Container.class);
    private final ConcurrentHashMap<String,Object> mpsContext = new ConcurrentHashMap<>();
    private static ThreadLocal instance = new ThreadLocal();
    private static Container container;
    private static Object myLock = new Object();
    private final Map<String,Channels> channelsMap = new ConcurrentHashMap<>();
    private final List<Injector> injectors = new ArrayList();
    private final Map<Channels,Map<String,Object>> paramterMap = new ConcurrentHashMap<>();

    private FilterChain chain;

    private boolean running;

    private Container(){}


    public static Container getInstance(){
        if(container == null){
            synchronized (myLock){
                if(container == null)
                    container = new Container();
            }
        }
        return container;
    }


    public Map getMpsContext(){
        return mpsContext;
    }


    public void destory(){

    }

    public void setFilterChain(FilterChain chain){
        this.chain = chain;
    }

    public Map<String,Channels> getChannelsMap(){
        return channelsMap;
    }


    public Container addInjectors(){
        if(channelsMap!=null){
            try {
                for(Map.Entry entry : channelsMap.entrySet()){
                    String cl = (String) entry.getKey();
                    Channels channels = (Channels) entry.getValue();
                    Map<String, Object> fieldMap = (Map<String, Object>) mpsContext.get(cl);
                    paramterMap.put(channels, fieldMap);
                    Class clazz = channels.getClass();
                    if(fieldMap!=null){
                        for(Map.Entry<String,Object> param : fieldMap.entrySet()){
                            String fieldKey = param.getKey();
                            String fieldValue = (String) param.getValue();

                            Field field = clazz.getDeclaredField(fieldKey);
                            addInjectorForFields(field,channels,fieldValue);
                        }
                    }

                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                logger.error("addInjector error ,",e);
            }
        }
        return this;
    }

    protected void addInjectorForFields(Field field,Object o, Object value){
        injectors.add(new FieldInjector(field,o,value));
    }

    public void inject(){
        if(injectors!=null && injectors.size()>0){
            for(Injector injector : injectors){
                injector.inject();
            }
        }
    }


    /**
     * add filter for container
     * @param filter
     */
    protected void addFilter(Filter filter){
        if(chain == null){
            chain = new FilterChain();
        }
        chain.addFilter(filter);
    }


    protected void addListeners(Listener listener){

    }

    public Invoker invoke() throws InvalidArgumentException {
        Invoker invoker = null;
        if(!running){
            running = true;
            if(channelsMap==null)
                throw new InvalidArgumentException("渠道商没有初始化成功");
            if(chain == null){
                chain = new FilterChain();
            }
            invoker = chain.buildInvokeChain();
        }
        return invoker;
    }

    interface  Injector{

        void inject();
    }

    static class FieldInjector implements Injector{
        final Field field;
        final Object obj;
        final Object value;

        FieldInjector(Field field,Object obj,Object value){
            this.field = field;
            this.obj = obj;
            this.value = value;
            if(!field.isAccessible()){
                field.setAccessible(true);
            }

        }

        public void inject(){
            try {
                field.set(obj,value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }



}
