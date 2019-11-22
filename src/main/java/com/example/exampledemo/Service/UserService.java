package com.example.exampledemo.Service;

import com.example.exampledemo.bean.user;

public interface UserService {
    user getUser(int uid);
    void insertUser(user u);
}
