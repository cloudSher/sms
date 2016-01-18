package com.lashou.service.sms.biz.sms.queue;



import com.lashou.service.sms.biz.sms.exception.ReqQueueFullException;

import java.util.Set;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public interface Queue<K,M> {

    public void put(K key, M msg) throws ReqQueueFullException;

    public M take(K key, boolean remove);

    public int size();

    public Set<K> getAll();

    public void setMaxSize(int maxSize);

    public void clear();

    public void put(M msg);

    public M take(boolean b);

    public M poll(int timeout);
}
