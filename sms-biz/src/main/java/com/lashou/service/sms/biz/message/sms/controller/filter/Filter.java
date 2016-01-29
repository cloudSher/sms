package com.lashou.service.sms.biz.message.sms.controller.filter;

import com.lashou.service.sms.biz.message.sms.controller.filter.impl.Result;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/26
 */
public interface Filter {

    Result invoke(Invoker invoker,Invocation invocation);
}
