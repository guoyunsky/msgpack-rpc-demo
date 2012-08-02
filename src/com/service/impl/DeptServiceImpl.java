package com.service.impl;

import com.entity.Dept;
import com.service.IDeptService;

public class DeptServiceImpl implements IDeptService {

    @Override
    public Dept getDeptById(long id) {
        return new Dept("name-" + id);
    }

}
