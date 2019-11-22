package com.example.exampledemo.controller;

import com.example.exampledemo.Service.UserService;
import com.example.exampledemo.bean.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/w")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public user getUser(int uid){
            return userService.getUser(uid);
    }

    @PostMapping("/insert")
    public String insert(@RequestBody user u){
        try {
            userService.insertUser(u);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }
}
