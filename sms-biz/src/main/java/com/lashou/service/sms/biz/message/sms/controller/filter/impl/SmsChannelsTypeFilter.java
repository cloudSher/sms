package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.Container;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.model.ChannelPayMentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sher on 3/8/16.
 */
public class SmsChannelsTypeFilter implements Filter{


    @Override
    public Result invoke(Invoker invoker, Invocation invocation) {
        List<Channels> channelsList = null;
        if(invocation!=null){
            List<Channels>  channels = invocation.getChannels();
            if(channels == null){
                return null;
            }
            channelsList = new ArrayList<>(channels.size());
            for(int i = 0 ; i < channels.size(); i++){
                //优先走包量的渠道商
                Channels c = channels.get(i);
                if(c.getType() == ChannelPayMentType.AMOUNT){
                    channelsList.add(c);
                    channels.remove(i);
                }

            }

            if(channelsList.size() == 1){
                channelsList.addAll(channels);
            }

            if(channelsList.size() > 0){
                invocation.setChannels(channelsList);
            }

        }
        return new Result<List>(1,channelsList);
    }
}
