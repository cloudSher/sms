package com.lashou.service.sms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lashou.service.sms.domain.MessageResponse;
import com.lashou.service.sms.domain.MessageResponseExample;
import com.lashou.service.sms.mapper.MessageResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by cloudsher on 2016/3/18.
 */

@Controller
@RequestMapping("/message")
public class IndexController {


    @Autowired
    private MessageResponseMapper messageResponseMapper;

    @RequestMapping("/index")
    public String test(){
        return "index.html";
    }

    @RequestMapping("/sms")
    public String sms_index(){
        return "sms/index.html";
    }

    @RequestMapping("/mail")
    public String email_index(){
        return "mail/index.html";
    }

    @RequestMapping("/app")
    public String app_index(){
        return "app/index.html";
    }


    @RequestMapping("/data")
    @ResponseBody
    public String getData(@RequestParam("msgId") String msgId){
        MessageResponseExample example = new MessageResponseExample();
        example.createCriteria().andMessageidEqualTo(msgId);
        List<MessageResponse> messageResponses = messageResponseMapper.selectByExample(example);
        return JSONObject.toJSONString(messageResponses);
    }



}
