package com.lashou.service.sms.api.rpc;

import com.lashou.service.sms.domain.OpResult;

/**
 * Created by cloudsher on 2016/4/21.
 */
public interface PushReqMsgService {


    /***
     *  发送短消息接口
     * @return
     */
    OpResult pushMsg(String msg);


    /**
     * 测试接口
     * @return
     */
    String test(Object obj);

}
