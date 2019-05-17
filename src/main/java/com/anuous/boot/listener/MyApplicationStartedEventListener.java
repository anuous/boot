package com.anuous.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 *springBoot启动时候 通过事件监听机制以方便扩展
 * 1、ApplicationStartedEvent  springboot启动开始时执行的事件
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info("进入MyApplicationStartedEventListener的时间：{}",System.currentTimeMillis());
        SpringApplication app=event.getSpringApplication();
        app.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                System.out.println("<<<<<<<<<<<this is a Banner Test !>>>>>>>>>>>>>>>>>>>");
            }
        });
        logger.info("这里是测试springBoot启动事件监听处理。MyApplicationStartedEventListener");
    }
}
