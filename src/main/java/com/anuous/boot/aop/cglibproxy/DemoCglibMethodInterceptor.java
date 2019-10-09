package com.anuous.boot.aop.cglibproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoCglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object res = null;
        try {
            System.out.println("cglib --before!");
            res = methodProxy.invokeSuper(o,objects);
            System.out.println("cglib -- return!");
        } catch (Exception e) {
            System.out.println("cglib -- afterException!");
            throw e;
        } finally {
            System.out.println("cglib --after return!");
        }
        return null;
    }
}
