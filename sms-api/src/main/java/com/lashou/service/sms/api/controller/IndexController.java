//package com.lashou.service.sms.api.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.lashou.service.sms.domain.MessageResponse;
//import com.lashou.service.sms.domain.MessageResponseExample;
//import com.lashou.service.sms.mapper.MessageResponseMapper;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by cloudsher on 2016/3/18.
// */
//
//@Controller
//@RequestMapping("/sms")
//public class IndexController {
//
//
//    @Resource
//    private MessageResponseMapper messageResponseMapper;
//
//    @RequestMapping("/index")
//    public String test(){
//        return "test";
//    }
//
//
//
//    @RequestMapping("/data")
//    @ResponseBody
//    public String getData(@RequestParam("msgId") String msgId){
//        MessageResponseExample example = new MessageResponseExample();
//        example.createCriteria().andMessageidEqualTo(msgId);
//        List<MessageResponse> messageResponses = messageResponseMapper.selectByExample(example);
//        return JSONObject.toJSONString(messageResponses);
//    }
//}
