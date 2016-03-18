package com.lashou.service.sms.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cloudsher on 2016/3/18.
 */

@Controller
@RequestMapping("/sms")
public class IndexController {


    @RequestMapping("/index")
    public String test(){
        return "test";
    }
}
