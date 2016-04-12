package com.lashou.service.sms.biz.monitor;

import com.lashou.service.sms.biz.monitor.impl.SmsMsgMonitorData;
import com.lashou.service.sms.biz.monitor.impl.SmsMsgResponseData;
import com.lashou.service.sms.domain.MessageResponse;
import com.lashou.service.sms.mapper.MessageBodyMapper;
import com.lashou.service.sms.mapper.MessageResponseMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by cloudsher on 2016/3/18.
 */
public  abstract class AbstractMonitor implements Monitor {


    public void start(){
        SmsMsgMonitorData monitorData = collectSmsMsgMonitorData();
        SmsMsgResponseData responseData = collectResponseMonitorData();
        if(monitorData!=null){
            //todo 处理失败之后的
            System.out.println(monitorData);
        }
    }


    public void stop(){

    }

    public abstract SmsMsgMonitorData collectSmsMsgMonitorData();

    public abstract SmsMsgResponseData collectResponseMonitorData();

}
