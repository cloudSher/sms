package com.lashou.service.sms.api.rest.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lashou.service.sms.api.rest.PushReqMsgRestService;
import com.lashou.service.sms.biz.PushService;
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
    @GET
    @Path("push")
    @Consumes({MediaType.APPLICATION_JSON})
    public OpResult pushMsg() {
        HttpServletRequest request = RpcContext.getContext().getRequest(HttpServletRequest.class);
        logger.info("push api start....");

        String json =  request.getParameter("message");
        if(json == null){
            return OpResult.createFailMsg("参数不能为空",null);
        }

        JSONObject jo = JSONObject.parseObject(json);

        long currTime = System.currentTimeMillis();

        String msg = jo.getString("content");
        String mobiles = jo.getString("mobiles");

        Sender sender = new Sender();
        sender.setType(MessageType.SMS.getKey());
        Map<String,Object> resource = new HashMap<>();
        resource.put("mobiles",mobiles);

        Receiver receiver = new Receiver();
        Header header = new Header();
        header.setType(MessageType.SMS.getKey());

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


    /**
     * broad message
     * @param jo
     * @return
     */
    private PushReq processBroad(JSONObject jo) {
        PushReqBroad req = new PushReqBroad();
        JSONArray phones = jo.getJSONArray("phoneList");
        if(phones == null || phones.size() == 0){
            try {
                throw new InvalidArgumentException("api发送短信时，手机号不能为空");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
                logger.error("api发送短信时，手机号不能为空");
            }
        }

        JSONArray msgJa= jo.getJSONArray("msgList");
        if(msgJa == null){
            try {
                throw new InvalidArgumentException("消息体不能为空");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
                logger.error("api发送消息时,消息体不能为空",e);
            }
        }else if(msgJa.size() != 1){
            try {
                throw new InvalidArgumentException("群发短信时，只能有一个短信内容");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
                logger.error("api发送短信时，短信内容只有一个",e);
            }

        }

        PushMsg pushMsg = new PushMsg();
        req.setPushMsg(pushMsg);
        pushMsg.setContent(msgJa.getString(0));
        pushMsg.setExpireSeconds(jo.getIntValue("expireSends"));

        List<String> mobiles = new ArrayList<>(phones.size());
        req.setUserIds(mobiles);
        for(int i = 0 ; i < phones.size(); i++){
            mobiles.add(phones.getString(i));
        }

        return req;
    }

    /**
     * batch push message , the number of mobiles and msgList must be equal
     * @param jo
     * @return
     */
    private PushReq processBatch(JSONObject jo) {
        PushReqBatch req = new PushReqBatch();
        JSONArray msgList = jo.getJSONArray("msgList");
        JSONArray phoneList = jo.getJSONArray("phoneList");
        if(msgList == null || msgList.size() == 0){
            try {
                throw new InvalidArgumentException("api发送短信时，消息不能为空");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
                logger.error("api发送短信时，消息不能为空",e);
            }
        }
        if(phoneList == null || phoneList.size() == 0){
            try {
                throw new InvalidArgumentException("api发送短信时，手机号不能为空");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
                logger.error("api发送短信时，手机号不能为空");
            }
        }else if(phoneList.size() != msgList.size()){
            try {
                throw new InvalidArgumentException("api发送短信时，手机号必须和消息数量相等");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
                logger.error("api发送短信时，手机号和消息数量必须相等");
            }

        }

        List<PushReqSingle> singles = new ArrayList<>(phoneList.size());
        req.setBatch(singles);
        PushReqSingle single;
        PushMsg msg;
        for (int i = 0 ; i < msgList.size(); i++){
            single = new PushReqSingle();
            msg = new PushMsg();
            JSONObject jMsg = (JSONObject) msgList.get(i);
            singles.add(single);
            single.setPushMsg(msg);
            msg.setContent(jMsg.getString("content"));
            msg.setExpireSeconds(jMsg.getIntValue("expireSends"));
            msg.setMsgId(jMsg.getString("msgId"));
            msg.setPriority(jMsg.getIntValue("priority"));
            msg.setTitle(jMsg.getString("title"));
            msg.setTip(jMsg.getString("tip"));

            single.setUserId(phoneList.getString(i));
        }

        return req;
    }


    @GET
    @Path("{id: \\d+}")
    public String test(@PathParam("id") Long id){
        return "{1,1,1,1+"+id+"}";
    }




}
