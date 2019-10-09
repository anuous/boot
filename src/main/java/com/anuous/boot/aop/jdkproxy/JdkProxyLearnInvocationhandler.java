package com.anuous.boot.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyLearnInvocationhandler implements InvocationHandler {
    private IJdkProxyLearnService target;

    public JdkProxyLearnInvocationhandler(IJdkProxyLearnService jdkProxyLearnService) {
        this.target = jdkProxyLearnService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        try {
            System.out.println("invoke ---before !");
            res = method.invoke(target, args);
            return res;
        } catch (Throwable e) {
            System.out.println("afterException !");
            throw e;
        } finally {
            System.out.println("after return !");
        }

    }
}
