package com.anuous.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LearnAspect {


    @Pointcut("args(com.anuous.boot.aop.ParamTest) && within(com.anuous.boot.aop.impl.*)")
    public void learn(){
    }
    @Before("learn()")
    public void before(){
        System.out.println(" 执行方法 ----before");
    }
//    @Around("learn()")
//    public void doLearning(ProceedingJoinPoint point) throws Throwable {
//        System.out.println(" 执行方法 ----doLearning --before");
//        point.proceed();
//        System.out.println(" 执行方法 ----doLearning --after");
//    }
//    @After("learn()")
//    public void after(){
//        System.out.println(" 执行方法之前----after");
//    }
//    @AfterReturning("learn()")
//    public void afterReturn(){
//        System.out.println(" 执行方法之前----afterReturn");
//    }
//    @AfterThrowing("learn()")
//    public void afterException(){
//        System.out.println(" 执行方法之前----afterException");
//    }
}
