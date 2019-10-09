package com.anuous.boot.aop.jdkproxy;

public class JdkProxyLearnServiceImpl implements IJdkProxyLearnService {
    @Override
    public void learn() {
        System.out.println("jdkproxy 学习 ing !");
    }

    @Override
    public void sleep() {
        System.out.println("jdkproxy 学习累了，我要睡觉了 sleeping !");
    }
}
