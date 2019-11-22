package com.example.exampledemo.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MQServer {
    @JmsListener(destination = "queue")
    public void receive(String message){
        System.out.println("监听queue====");
        System.out.println(message);
    }
}
