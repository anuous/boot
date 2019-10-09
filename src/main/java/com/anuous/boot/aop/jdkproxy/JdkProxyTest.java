package com.anuous.boot.aop.jdkproxy;

import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IJdkProxyLearnService proxyObejct =(IJdkProxyLearnService) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(),new Class[]{IJdkProxyLearnService.class},new JdkProxyLearnInvocationhandler(new JdkProxyLearnServiceImpl()));
        proxyObejct.sleep();
    }
}
