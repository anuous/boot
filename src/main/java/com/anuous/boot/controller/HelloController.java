package com.anuous.boot.controller;

import com.anuous.boot.aop.ParamTest;
import com.anuous.boot.aop.impl.AopLearnService;
import com.anuous.boot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/hello")
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    //@Autowired
    private final RedisUtil redisUtil;

    //@Autowired
    private final AopLearnService aopLearnService;

    @Autowired
    public HelloController(RedisUtil redisUtil,AopLearnService aopLearnService){
        this.redisUtil=redisUtil;
        this.aopLearnService=aopLearnService;
    }

    //@Autowired
    private JedisCluster jedisCluster;

    @RequestMapping(value={"/{name}","/"})
    public String  sayHello(@PathVariable(value="name",required = false) String name)  {
        System.out.println("hello "+ name);
        if(StringUtils.isEmpty(name)){

        }else{
            redisUtil.sadd(name,name);
        }
        try {
            aopLearnService.test(new ParamTest());
        }catch (Exception e){
            log.error("测试定义的异常");
            name = "";
        }
        return "welcome to china "+ name;
    }


    @RequestMapping(method= RequestMethod.POST,value = "/{save}")
    public String saveUser(User user){
        System.out.println("保存的数据为："+user.toString());
        Long res= jedisCluster.sadd(user.getId(),user.toString());
        return String.valueOf(res);
    }
}

class User{

    private String id;
    private String name;

    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

