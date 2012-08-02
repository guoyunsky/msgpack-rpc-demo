package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.entity.Address;
import com.entity.Dept;
import com.entity.User;
import com.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
    public User getUserById(Long userId) {
	    Address a1 = new Address("address-" + userId);
	    List<Address> as = new ArrayList<Address>();
	    as.add(a1);
	    
	    Dept dept = new Dept("dept-" + userId);
		return new User(userId, "name-" + userId, as, dept);
	}
}