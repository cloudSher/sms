package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public class FilterMain {


    public static void main(String args[]){
        FilterChain chain = new FilterChain();
        List<Filter> filters = new ArrayList<>();
        filters.add(new SmsMessageFilter());
        filters.add(new SmsMobilesFilter());
        chain.setFilters(filters);
        SmsInvocation inv = new SmsInvocation();
        chain.buildInvokeChain().invoke(inv);
    }
}
