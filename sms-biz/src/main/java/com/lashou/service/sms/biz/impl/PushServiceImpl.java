package com.lashou.service.sms.biz.impl;


import com.lashou.service.sms.biz.PushService;
import com.lashou.service.sms.biz.sms.queue.BasicQueue;
import com.lashou.service.sms.domain.PushMsg;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/15
 */
public class PushServiceImpl implements PushService {

    private BasicQueue reqQueue;

    @Override
    public void req(PushMsg pushMsg) {
       if(pushMsg != null){

       }
    }
}
