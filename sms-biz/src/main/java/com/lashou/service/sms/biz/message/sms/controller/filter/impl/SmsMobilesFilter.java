package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public class SmsMobilesFilter implements Filter {


    @Override
    public Result invoke(Invoker invoker, Invocation invocation) {
        System.out.println("mobile is filter");
        return null;
    }
}
