package com.test;

import com.entity.Dept;
import com.entity.User;
import com.service.IDeptService;
import com.service.IUserService;
import com.service.impl.DeptServiceImpl;
import com.service.impl.UserServiceImpl;

public class ServerApp {
    IUserService userService = new UserServiceImpl();
    IDeptService deptService = new DeptServiceImpl();
    
    public Dept getDeptById(long id){
        return this.deptService.getDeptById(id);
    }
    
    public User getUserById(Long userId){
        return this.userService.getUserById(userId);
    }
}
