package com.lashou.service.sms.biz.message.config.impl;

/**
 * Created by sher on 3/13/16.
 */
public class ChannelsAccount {

    private String user;
    private String password;
    private int type;

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
