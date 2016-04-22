package com.lashou.service.sms.domain;

import java.io.Serializable;

/**
 * Created by sher on 3/8/16.
 */
public class Body implements Serializable{

    private static final long serialVersionUID = 1284088652274404421L;

    private Sender sender;
    private Receiver receiver;
    private String content;

    public Sender getSender() {
        return this.sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return this.receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
