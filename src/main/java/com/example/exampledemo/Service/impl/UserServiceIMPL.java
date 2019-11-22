package com.example.exampledemo.Service.impl;

import com.example.exampledemo.Service.UserService;
import com.example.exampledemo.bean.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exampledemo.Dao.UserMapper;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public user getUser(int uid){
        return userMapper.getUser(uid);
    }
    @Override
    public void insertUser(user u){
        userMapper.insertUser(u);
    }
}
