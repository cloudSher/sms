package com.lashou.service.sms.dubbo.customer.impl;

import java.io.Serializable;

/**
 * Created by cloudsher on 2016/8/1.
 */
public class Person implements Serializable{

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


}
