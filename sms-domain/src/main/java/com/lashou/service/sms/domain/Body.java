package com.lashou.service.sms.domain;

/**
 * Created by sher on 3/8/16.
 */
public class Body {

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
