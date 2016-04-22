package com.lashou.service.sms.domain;

import java.io.Serializable;

/**
 * Created by sher on 3/8/16.
 */
public class Message implements Serializable{

    private static final long serialVersionUID = -3688763141031713282L;

    private String msgId;
    private Header header;
    private Body body;

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Header getHeader() {
        return this.header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
