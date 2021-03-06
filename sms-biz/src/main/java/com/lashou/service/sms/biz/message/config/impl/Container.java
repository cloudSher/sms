package com.lashou.service.sms.biz.message.config.impl;

import com.lashou.service.sms.biz.message.listeners.Listener;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.FilterChain;
import com.lashou.service.sms.biz.message.sms.controller.filter.impl.SmsInvocation;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.monitor.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/29
 */
public class Container {

    private static Logger logger = LoggerFactory.getLogger(Container.class);
    private final ConcurrentHashMap<String,Object> mpsContext = new ConcurrentHashMap<>();
    private static Container container;
    private static Object myLock = new Object();
    private final Map<String,Channels> channelsMap = new ConcurrentHashMap<>();
    private final List<Injector> injectors = new ArrayList();
    private final Map<Channels,Map<String,Object>> paramterMap = new ConcurrentHashMap<>();

    private FilterChain chain;

    private volatile boolean running = true;

    private volatile int status;

    private Monitor monitor;

    private Container(){}

    public synchronized void setRunning(boolean rn){
        this.running = rn;
    }

    public boolean isRunning(){
        return running;
    }

    public synchronized void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

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
                        List list = new ArrayList();
                        Map<String,Object> cMap = new HashMap<>();
                        for(Map.Entry<String,Object> param : fieldMap.entrySet()){
                            String fieldKey = param.getKey();
                            Object fieldValue = param.getValue();

                            if(fieldKey!=null){
                                if(fieldKey.contains(".")){
                                    initChildClass(fieldKey, fieldValue, clazz, list, cMap);
                                }else{
                                    Field field = clazz.getDeclaredField(fieldKey);
                                    addInjectorForFields(field,channels,fieldValue);
                                }
                            }
                        }

                        if(cMap!=null && list != null){
                            Set<String> keySet = cMap.keySet();
                            String key = keySet.iterator().next();
                            if(keySet.size() == 1 && key.matches("^[a-zA-Z]+$")){
                                Field field = clazz.getDeclaredField(key);
                                addInjectorForFields(field,channels,list.get(0));
                            }else{
                                key = key.substring(0,key.length()-1);
                                Field field = clazz.getDeclaredField(key);
                                addInjectorForFields(field,channels,list);
                            }
                        }
                    }

                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                logger.error("addInjector error ,",e);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return this;
    }



    public void initChildClass(String fieldKey, Object value,Class clazz,List list ,Map<String,Object> cMap) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        if(cMap == null)
             cMap = new HashMap<>();
        if(list == null)
             list = new ArrayList();
        if(fieldKey.contains(".")) {
            String cgkey = fieldKey.split("\\.")[0];
            String cval = fieldKey.split("\\.")[1];


            String tail = cgkey.substring(cgkey.length()-1);
            String ckey;
            if(tail.matches("\\d+")){
                ckey = cgkey.substring(0,cgkey.length()-1);
                Field field = clazz.getDeclaredField(ckey);
                ParameterizedType type = (ParameterizedType) field.getGenericType();

                Class<?> cc = (Class<?>) type.getActualTypeArguments()[0];
                Object o = cMap.get(cgkey);
                if (o == null) {
                    o = cc.newInstance();
                    cMap.put(cgkey, o);
                    list.add(o);
                }
                Field cField = cc.getDeclaredField(cval);
                new FieldInjector(cField,o,value).inject();
            }else{
                ckey = cgkey;

                Field declaredField = clazz.getDeclaredField(ckey);
                Type type = declaredField.getGenericType();
                Class<?> cc = (Class<?>)type;
                Object o = cMap.get(ckey);
                if(o == null){
                    o = cc.newInstance();
                    cMap.put(ckey,o);
                    list.add(o);
                }
                Field cField = cc.getDeclaredField(cval);
                new FieldInjector(cField,o,value).inject();
            }

        }

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
    public void addFilter(Filter filter){
        if(chain == null){
            chain = new FilterChain();
        }
        chain.addFilter(filter);
    }


    public void addListeners(Listener listener){

    }

    public List<Channels> getChannels(){
        List<Channels> list = new ArrayList<>(channelsMap.size());
        if(channelsMap!=null){
            for(Map.Entry<String,Channels> entry : channelsMap.entrySet()){
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public Invoker invoke() throws InvalidArgumentException {
        Invoker invoker = null;
        running = true;
        if(channelsMap==null)
            throw new InvalidArgumentException("渠道商没有初始化成功");
        if(chain == null){
            chain = new FilterChain();
        }
        invoker = chain.buildInvokeChain();
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
                if(field.getType().equals(Integer.class) || field.getType().equals(int.class)){
                    int intValue = 0;
                    intValue = Integer.valueOf(value.toString());
                    field.set(obj,intValue);
                }else if(field.getType().equals(Map.class)) {
                    Map<String, String> map = new HashMap<>();
                    String val = value.toString();
                    initMap(val, map);
                    field.set(obj, map);
                }else{
                    field.set(obj,value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public void initMap(String value,Map<String,String> map){
            if(value!=null && value.length()>0){
                String accounts[] = value.split(";");
                for(int i = 0 ; i <accounts.length; i++){
                    String users = accounts[i];
                    if(users!=null && users.length()>0){
                        String user[] = users.split(":");
                        map.put(user[0],user[1]);
                    }
                }
            }


        }

    }



}
