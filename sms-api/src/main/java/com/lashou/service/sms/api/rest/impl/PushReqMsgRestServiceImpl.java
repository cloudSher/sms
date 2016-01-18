package com.lashou.service.sms.api.rest.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.lashou.service.mps.PushService;
import com.lashou.service.mps.api.facade.PushReqMsgRestService;
import com.lashou.service.mps.domain.Caller;
import com.lashou.service.mps.domain.Message;
import com.lashou.service.mps.domain.MpsContext;
import com.lashou.service.mps.domain.OpResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    private PushService pushService;

    public void setPushService(PushService pushService) {
        this.pushService = pushService;
    }

    @GET
    public OpResult push_msg(Caller caller, Message msg, MpsContext context) {
        if (RpcContext.getContext().getRequest(HttpServletRequest.class) != null) {
            System.out.println("Client IP address from RpcContext: " + RpcContext.getContext().getRequest(HttpServletRequest.class).getRemoteAddr());
        }
        if (RpcContext.getContext().getResponse(HttpServletResponse.class) != null) {
            System.out.println("Response object from RpcContext: " + RpcContext.getContext().getResponse(HttpServletResponse.class));
        }
        return new OpResult(200,"success",1,null);
    }


    @GET
    @Path("{id: \\d+}")
    public String test(@PathParam("id") Long id){
        return "{1,1,1,1+"+id+"}";
    }




}
