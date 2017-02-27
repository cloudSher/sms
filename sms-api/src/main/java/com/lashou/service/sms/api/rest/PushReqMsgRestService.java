package com.lashou.service.sms.api.rest;


import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.lashou.service.sms.domain.OpResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/12
 */
@Path("mps")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface PushReqMsgRestService {

    /***
     *  发送短消息接口
     * @return
     */
    @POST
    @Path("push")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    OpResult pushMsg(String msg);


    /**
     * 测试接口
     * @return
     */
    @GET
    @Path("test")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    String test();

}
