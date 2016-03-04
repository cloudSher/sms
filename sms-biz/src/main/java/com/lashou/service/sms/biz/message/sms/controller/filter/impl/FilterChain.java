package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/27
 */
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    private int index;


    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Invoker buildInvokeChain(){
        Invoker last = null;
        if(filters!=null && filters.size()>0){
            for(final Filter filter : filters){
                final Invoker next = last;
                last = new Invoker() {
                    @Override
                    public Result invoke(Invocation invocation) {
                        return filter.invoke(next,invocation);
                    }
                };
            }
        }
        return last;
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }
}
