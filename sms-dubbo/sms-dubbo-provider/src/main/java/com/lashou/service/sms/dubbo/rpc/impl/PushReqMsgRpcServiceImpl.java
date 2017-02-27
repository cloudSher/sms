package com.lashou.service.sms.dubbo.rpc.impl;

import com.alibaba.dubbo.container.Main;
import com.alibaba.fastjson.JSONObject;
import com.lashou.service.sms.api.rpc.PushReqMsgService;
import com.lashou.service.sms.biz.PushService;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.dic.MessageType;
import com.lashou.service.sms.domain.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by cloudsher on 2016/4/21.
 */
public class PushReqMsgRpcServiceImpl implements PushReqMsgService {

    private static final Logger logger = Logger.getLogger(PushReqMsgRpcServiceImpl.class);

    @Autowired
        private PushService pushService;

        /***
         *
         * url：hostname:8231/services/mps/push
         * 请求参数：{"content":"11111","mobiles":"18518567340","type":"1","scope":"2"}   "scope": 1  真实发送， 2：测试
         * @param str
         * @return
         */
        @Override
        public OpResult pushMsg(String str) {
            logger.info("push api start....");
            if(StringUtil.isNullOrEmpty(str)){
                return OpResult.createFailMsg("参数不能为空", null);
            }

            System.out.println(str);

            JSONObject jo = JSONObject.parseObject(str);
//        jo = (JSONObject) jo.get("message");

        long currTime = System.currentTimeMillis();

        String msg = jo.getString("content");
        String mobiles = jo.getString("mobiles");
        String msgType = jo.getString("type");
        String msgScope = jo.getString("scope");
        String msgPriority = jo.getString("priority");
        String channel = jo.getString("channel_id");

        if(StringUtil.isNullOrEmpty(msg)){
            return OpResult.createFailMsg("内容参数不能为空",null);
        }
        if(StringUtil.isNullOrEmpty(msgType)){
            return OpResult.createFailMsg("消息类型参数不能为空",null);
        }else{
            if(msgType.equals(MessageType.SMS)){
                if(StringUtil.isNullOrEmpty(mobiles)){
                    return OpResult.createFailMsg("手机号参数不能为空",null);
                }
            }
        }
        if(StringUtil.isNullOrEmpty(msgScope)){
            return OpResult.createFailMsg("业务类型参数不能为空",null);
        }
        if(StringUtil.isNullOrEmpty(msgPriority)){
            msgPriority = "2";
        }

        Sender sender = new Sender();
        sender.setType(Integer.valueOf(msgType));
        sender.setScope(Integer.valueOf(msgScope));
        Map<String,Object> resource = new HashMap<>();
        resource.put("mobiles",mobiles);
        resource.put("channel_id",channel);
        sender.setParameters(resource);
        sender.setSendTime(currTime);

        Receiver receiver = new Receiver();
        Header header = new Header();
        header.setType(Integer.valueOf(msgType));
        header.setPriority(Integer.valueOf(msgPriority));

        Body body = new Body();
        body.setSender(sender);
        body.setReceiver(receiver);
        body.setContent(msg);
        Message message = new Message();
        message.setBody(body);
        message.setHeader(header);
        message.setMsgId(UUID.randomUUID().toString().replace("-",""));
        OpResult opResult = pushService.req(message);
        return opResult;
    }

    /**
     *  hession2 序列化结果，由于内部封装，无需实现tostring（）
     *
     *  ====obj {name=张三, age=20}
     ===class java.util.HashMap
     ==modify 26, ===name: serialVersionUID
     ==modify 24, ===name: DEFAULT_INITIAL_CAPACITY
     ==modify 24, ===name: MAXIMUM_CAPACITY
     ==modify 24, ===name: DEFAULT_LOAD_FACTOR
     ==modify 24, ===name: TREEIFY_THRESHOLD
     ==modify 24, ===name: UNTREEIFY_THRESHOLD
     ==modify 24, ===name: MIN_TREEIFY_CAPACITY
     ==modify 128, ===name: table
     ==modify 128, ===name: entrySet
     ==modify 128, ===name: size
     ==modify 128, ===name: modCount
     ==modify 0, ===name: threshold
     ==modify 16, ===name: loadFactor


     * @param obj
     * @return
     */
    @Override
    public String test(Object obj) {
        if(obj != null){
            System.out.println("====obj "+ obj);
            System.out.println("===class "+obj.getClass().getName());
            Field[] fields = obj.getClass().getDeclaredFields();
            for(int i = 0 ; i < fields.length; i++){
                Field field = fields[i];
                System.out.println("==modify "+field.getModifiers() +", ===name: "+field.getName());
            }
        }
        return "success";
    }


    public static void main(String args[]){
        Main main = new Main();
        main.main(null);
    }

}
