package com.lashou.service.sms.biz.message.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

/**
 * Created by sher on 3/4/16.
 */
public abstract class ObservableSubject implements Listener {


    private List<Listener> listeners = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    protected final Lock readLock = readWriteLock.readLock();
    protected final Lock writeLock = readWriteLock.writeLock();

    public Listener registerListener(Listener listener){
        writeLock.lock();
        try {
            //add Listener
            listeners.add(listener);

        }finally {
            writeLock.unlock();
        }
        return listener;
    }



    public Listener unRegisterListener(Listener listener){
        writeLock.lock();
        try {
            listeners.remove(listener);

        }finally {
            writeLock.unlock();
        }
        return listener;
    }


    public void notifyListeners(Consumer<? super Listener> action){
        this.readLock.lock();
        try{
            this.listeners.forEach(action);
        }finally {
            this.readLock.unlock();
        }
    }

}
