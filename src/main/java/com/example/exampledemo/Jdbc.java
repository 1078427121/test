package com.example.exampledemo;

import com.alibaba.fastjson.JSONObject;
import com.example.exampledemo.bean.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/jdbc")
public class Jdbc {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/g1")
    public Map<String,Object> g1(int pid){
        if(redisTemplate.opsForHash().hasKey(pid,"price")){
            System.out.println("1");
            return redisTemplate.opsForHash().entries(pid);
        }
        System.out.println("2");
        Map<String, Object> map;
        String sql="select * from product where pid="+pid;
        map = jdbcTemplate.queryForMap(sql);
        int ppid=(int) map.get("pid");
        int price=(int) map.get("price");
        String detail=(String) map.get("detail");
        int count=(int) map.get("count");
        redisTemplate.opsForHash().put(ppid,"price",price);
        redisTemplate.opsForHash().put(ppid,"detail",detail);
        redisTemplate.opsForHash().put(ppid,"count",count);
        return map;
    }

    @GetMapping("/list")
    public List<Map<String, Object>> itemsList() {
        String sql = "select * from product";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @GetMapping("/detail/{uid}")
    public JSONObject detail(int uid) {
        Map<String,Object> ret = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        String sql="select * from book where id="+uid;
        try {
            map = jdbcTemplate.queryForMap(sql);
            ret.put("code",1);
            ret.put("data",map);
        }catch (Exception e){
            ret.put("code",0);
        }
        return new JSONObject(ret);
    }

    @RequestMapping("/add")
    public String  addItems(@RequestBody user u) {
        String sql = "insert into user(uid,sessionid) values (?,?)";
        Object args[] = {u.getUid(),u.getSessionid()};
        try {
            int temp = jdbcTemplate.update(sql, args);
            if (temp > 0) {
                return "成功";
            }
            return "错误1";
        }catch (Exception e){
            return "错误2";
        }
    }

    @RequestMapping("/upd")
    public String  updItems(@RequestBody user u) {
        String sql = "update user set sessionid = ? where uid = ?";
        Object args[] = {u.getSessionid(),u.getUid()};
        int temp = jdbcTemplate.update(sql, args);
        if(temp > 0) {
            return "文章修改成功";
        }
        return "修改出现错误";
    }
}
