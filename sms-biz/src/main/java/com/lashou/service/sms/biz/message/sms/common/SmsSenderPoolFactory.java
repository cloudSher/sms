package com.lashou.service.sms.biz.message.sms.common;

import com.lashou.service.sms.biz.message.sms.SenderPoolFactory;
import com.lashou.service.sms.biz.message.sms.SmsSenderPool;

import java.util.Map;

/**
 * Created by sher on 1/21/16.
 */
public class SmsSenderPoolFactory implements SenderPoolFactory {

    private Map<String,SmsSenderPool> poolMap;

    public Map<String, SmsSenderPool> getPoolMap() {
        return this.poolMap;
    }

    public void setPoolMap(Map<String, SmsSenderPool> poolMap) {
        this.poolMap = poolMap;
    }

    @Override
    public SmsSenderPool getPool(String key) {
        return poolMap.get(key);
    }
}
