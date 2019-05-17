package com.anuous.boot.controller;

import com.anuous.boot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/{name}")
    public String  sayHello(@PathVariable("name") String name){
        System.out.println("hello "+ name);
        redisUtil.sadd(name,name);
        return "welcome to china "+ name;
    }
}

