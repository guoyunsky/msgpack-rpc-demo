package com.entity;

import org.msgpack.annotation.Message;

@Message
public class Dept {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept(String name) {
        super();
        this.name = name;
    }

    public Dept() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
}