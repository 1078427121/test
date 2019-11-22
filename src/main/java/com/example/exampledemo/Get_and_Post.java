package com.example.exampledemo;

import com.example.exampledemo.bean.user;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v")
public class Get_and_Post {

    @GetMapping("/test")
    public String test(String a){
        return "hello world!"+a;
    }

    @PostMapping("/test2")
    @ResponseBody
    public String test2(@RequestBody Map<String,Object> params){
        String a = params.get("a").toString();
        String b = params.get("b").toString();
        return "a="+a+",b="+b;
    }

    @PostMapping("/test3")
    public String test3(user u,int uid){
        return u.getUid()+"#"+u.getSessionid()+"#"+uid;
    }
}
