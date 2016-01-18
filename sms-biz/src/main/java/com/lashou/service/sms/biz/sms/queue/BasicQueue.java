package com.lashou.service.sms.biz.sms.queue;



import com.lashou.service.sms.biz.sms.exception.ReqQueueFullException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class BasicQueue<K,M> implements Queue<K,M>{

    private final Map<K,M> reqMap = new ConcurrentHashMap<>();
    private final BlockingQueue<M> queue = new LinkedBlockingDeque<>();

    private int maxSize;

    @Override
    public void put(K key, M msg) throws ReqQueueFullException {
        if(reqMap.size() > maxSize){
            throw new ReqQueueFullException("请求队列已满,数量为"+maxSize);
        }
        reqMap.put(key,msg);
    }

    @Override
    public M take(K key, boolean remove) {
        if (reqMap.containsKey(key)){
            if(remove){
              return  reqMap.remove(key);
            }else{
                return reqMap.get(key);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return reqMap.size();
    }

    @Override
    public Set<K> getAll() {
        return reqMap!=null?reqMap.keySet():null;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void clear() {
        reqMap.clear();
    }

    @Override
    public void put(M msg) {
        if(!queue.offer(msg)){
            try{
                queue.add(msg);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public M take(boolean b) {
        try{
           return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public M poll(int timeout) {
        try{
            return queue.poll(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
