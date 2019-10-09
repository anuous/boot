package com.anuous.boot.aop.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {
    public static void main(String[] args) {
        Enhancer enhancer  = new Enhancer();
        enhancer.setSuperclass(CglibSubject.class);
        enhancer.setCallback(new DemoCglibMethodInterceptor());
        CglibSubject proxy = (CglibSubject) enhancer.create();
        proxy.learn();

    }
}
