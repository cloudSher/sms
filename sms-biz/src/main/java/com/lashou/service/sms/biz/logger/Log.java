package com.lashou.service.sms.biz.logger;

/**
 * @author cloudsher
 * @version 1.0
 * @date 2016/1/18
 */
public interface Log {

    /***
     * 添加日志类
     * @param logger
     */
    void put(Logger logger);

    /**
     * 发送日志类
     * @return
     */
    Logger send();
}
