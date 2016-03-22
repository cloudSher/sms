package com.lashou.service.sms.api.rest.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lashou.service.sms.api.rest.PushReqMsgRestService;
import com.lashou.service.sms.biz.PushService;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.dic.MessageType;
import com.lashou.service.sms.domain.*;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

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

@Path("mps")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class PushReqMsgRestServiceImpl implements PushReqMsgRestService {

    private static final Logger logger = Logger.getLogger(PushReqMsgRestServiceImpl.class);

    @Resource
    private PushService pushService;

    public void setPushService(PushService pushService) {
        this.pushService = pushService;
    }

    /**
     * 考虑：请求的消息体
     *
     * @return
     */
    @POST
    @Path("push")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public OpResult pushMsg(String str) {
        logger.info("push api start....");
        if(StringUtil.isNullOrEmpty(str)){
            return OpResult.createFailMsg("参数不能为空",null);
        }

        System.out.println(str);

        JSONObject jo = JSONObject.parseObject(str);
        jo = (JSONObject) jo.get("message");

        long currTime = System.currentTimeMillis();

        String msg = jo.getString("content");
        String mobiles = jo.getString("mobiles");
        String msgType = jo.getString("type");
        String msgScope = jo.getString("scope");

        Sender sender = new Sender();
        sender.setType(Integer.valueOf(msgScope));
        Map<String,Object> resource = new HashMap<>();
        resource.put("mobiles",mobiles);
        sender.setParameters(resource);
        sender.setSendTime(currTime);

        Receiver receiver = new Receiver();
        Header header = new Header();
        header.setType(Integer.valueOf(msgType));

        Body body = new Body();
        body.setSender(sender);
        body.setReceiver(receiver);
        body.setContent(msg);
        Message message = new Message();
        message.setBody(body);
        message.setHeader(header);
        message.setMsgId(UUID.randomUUID().toString());
        OpResult opResult = pushService.req(message);
        return opResult;
    }



    @GET
    @Path("{id: \\d+}")
    public String test(@PathParam("id") Long id){
        return "{1,1,1,1+"+id+"}";
    }




}
