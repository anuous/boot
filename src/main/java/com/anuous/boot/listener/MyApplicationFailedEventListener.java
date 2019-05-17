package com.anuous.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationFailedEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        logger.info("进入MyApplicationFailedEventListener的时间：{}",System.currentTimeMillis());
        Throwable throwable = event.getException();
        System.out.println(throwable.getMessage());
    }
}
