package com.lashou.service.sms.dubbo.rest.impl;

import com.alibaba.dubbo.container.Main;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import com.lashou.service.sms.api.rest.PushReqMsgRestService;
import com.lashou.service.sms.api.rpc.PushReqMsgService;
import com.lashou.service.sms.biz.PushService;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.dic.MessageType;
import com.lashou.service.sms.domain.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p> PushReqMsgRestServiceImpl</p>
 * <p> 说明 </p>
 * <p> Copyright:版权所有 （c）2014-2015 </p>
 * <p> Company:lashou </p>
 *
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/12
 */


public class PushReqMsgRestServiceImpl implements PushReqMsgRestService {

    private static final Logger logger = Logger.getLogger(PushReqMsgRestServiceImpl.class);

    @Autowired
    private PushService pushService;

    private PushReqMsgService pushReqMsgService;

    public void setPushReqMsgService(PushReqMsgService pushReqMsgService) {
        this.pushReqMsgService = pushReqMsgService;
    }

    /**
     * 考虑：请求的消息体
     *
     * @return
     */
    public OpResult pushMsg(String str) {
        return pushReqMsgService.pushMsg(str);
    }




    public String test(){
        HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
        System.out.println(request.getHeaderNames());
        String mobiles = request.getParameter("mobiles");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        String scope = request.getParameter("scope");
        String callback = request.getParameter("jsoncallback");

        if(StringUtil.isNullOrEmpty(content)){
            return  String.format("%1$s(%2$s)", callback, JSONObject.toJSON(JSONObject.toJSONString(OpResult.createFailMsg("内容参数不能为空",null))));
        }
        if(StringUtil.isNullOrEmpty(type)){
            return String.format("%1$s(%2$s)", callback, JSONObject.toJSON(JSONObject.toJSONString(OpResult.createFailMsg("消息类型参数不能为空", null))));
        }else{
            if(type.equals(MessageType.SMS)){
                if(StringUtil.isNullOrEmpty(mobiles)){
                    return String.format("%1$s(%2$s)", callback, JSONObject.toJSON(JSONObject.toJSONString(OpResult.createFailMsg("手机号参数不能为空", null))));
                }
            }
        }
        if(StringUtil.isNullOrEmpty(scope)){
            return String.format("%1$s(%2$s)", callback, JSONObject.toJSON(JSONObject.toJSONString(OpResult.createFailMsg("业务类型参数不能为空", null))));
        }

        Sender sender = new Sender();
        sender.setType(Integer.valueOf(type));
        sender.setScope(Integer.valueOf(scope));
        Map<String,Object> resource = new HashMap<>();
        resource.put("mobiles",mobiles);
        sender.setParameters(resource);
        sender.setSendTime(System.currentTimeMillis());

        Receiver receiver = new Receiver();
        Header header = new Header();
        header.setType(Integer.valueOf(1));

        Body body = new Body();
        body.setSender(sender);
        body.setReceiver(receiver);
        body.setContent(StringUtil.encoding(content));
        Message message = new Message();
        message.setBody(body);
        message.setHeader(header);
        message.setMsgId(UUID.randomUUID().toString().replace("-",""));
        OpResult opResult = pushService.req(message);
        String result = String.format("%1$s(%2$s)",callback, JSONObject.toJSON(opResult));
        return result;
    }


    public static void main(String args[]){
        Main main = new Main();
        main.main(null);
    }




}
