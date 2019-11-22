package com.example.exampledemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.exampledemo.bean.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/set")
    public boolean set(String a,Long b){
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(a,b);
            redisTemplate.expire(a, 1000, TimeUnit.SECONDS);//设置失效时间
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/get")
    public Set get() {
        redisTemplate.opsForSet().add("p","a");
        redisTemplate.opsForSet().add("p","b");
        System.out.println(redisTemplate.opsForSet().size("p"));
        return redisTemplate.opsForSet().members("p");
    }


}
