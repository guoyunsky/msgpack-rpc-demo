package com.entity;


import java.util.List;

import org.msgpack.annotation.Message;


@Message
public class User {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 7375286040851556274L;
    private Long userId;
    private String username;
    private List<Address> address;
    private Dept dept;
    
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<Address> getAddress() {
        return this.address;
    }
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    public Dept getDept() {
        return this.dept;
    }
    public void setDept(Dept dept) {
        this.dept = dept;
    }
    
    public User(Long userId, String username, List<Address> address, Dept dept) {
        super();
        this.userId = userId;
        this.username = username;
        this.address = address;
        this.dept = dept;
    }
    
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
}