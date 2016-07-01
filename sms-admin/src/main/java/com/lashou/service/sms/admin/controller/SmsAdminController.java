package com.lashou.service.sms.admin.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cloudsher on 2016/5/6.
 */
@RequestMapping("/sms/admin")
public class SmsAdminController {


    @RequestMapping("config")
    public String config(){
        return null;
    }


    @RequestMapping("modify/{qa}/{ch}")
    public String modify(@PathVariable String qa,@PathVariable String ch){
        return "index.html";
    }

}
