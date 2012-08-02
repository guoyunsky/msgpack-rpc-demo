package com.entity;

import org.msgpack.annotation.Message;

@Message
public class Address {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address(String name) {
        super();
        this.name = name;
    }

    public Address() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
}