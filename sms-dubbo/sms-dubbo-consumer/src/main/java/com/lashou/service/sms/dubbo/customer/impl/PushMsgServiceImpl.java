package com.lashou.service.sms.dubbo.customer.impl;


import com.alibaba.dubbo.container.Main;
import com.lashou.service.sms.api.rpc.PushReqMsgService;

import java.lang.reflect.Field;

/**
 * Created by cloudsher on 2016/4/21.
 */
public class PushMsgServiceImpl {


    private PushReqMsgService pushReqMsgService;

    public void setPushReqMsgService(PushReqMsgService pushReqMsgService) {
        this.pushReqMsgService = pushReqMsgService;
    }

    public void push(){
//        System.out.println(pushReqMsgService.pushMsg("{'mobiles':'18518567340,18610418821','content':'dubbo_consumer_test','type':'1','scope':'2'}"));
        System.out.println(pushReqMsgService.test(new Person("张三",20)));
    }


    public static void main(String args[]){
        Main main = new Main();
        main.main(null);
        fieldPrint(new Person("账单",20));
    }

    public static void fieldPrint(Person person){
        Class<? extends Person> clazz = person.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(int i = 0 ; i< fields.length; i++){
            System.out.println(fields[i].getModifiers()+","+fields[i].getName());
        }
    }
}
