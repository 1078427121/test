package com.example.exampledemo.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.jms.Queue;

@RestController
@RequestMapping("/mq")
public class MQClient {
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public String send(){
        String a = "";
        jmsTemplate.convertAndSend("queue","发送的数据");
        return a;
    }

}
