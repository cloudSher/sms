package com.lashou.service.sms.dubbo.customer.impl;


import com.lashou.service.sms.api.rest.PushReqMsgRestService;

/**
 * Created by cloudsher on 2016/4/21.
 */
public class PushMsgServiceImpl {


    private PushReqMsgRestService pushReqMsgService;

    public void setPushReqMsgService(PushReqMsgRestService pushReqMsgService) {
        this.pushReqMsgService = pushReqMsgService;
    }

    public void push(){
        System.out.println(pushReqMsgService.pushMsg("{'mobiles':'18518567340,18610418821','content':'dubbo_consumer_test','type':'1','scope':'2'}"));
    }
}
