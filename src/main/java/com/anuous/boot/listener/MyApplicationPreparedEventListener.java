package com.anuous.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ApplicationPreparedEvent:spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 */
public class MyApplicationPreparedEventListener  implements ApplicationListener<ApplicationPreparedEvent> {

    private Logger logger = LoggerFactory.getLogger(MyApplicationPreparedEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        logger.info("进入MyApplicationPreparedEventListener的时间：{}",System.currentTimeMillis());
        ConfigurableApplicationContext cac = event.getApplicationContext();
        cac.getApplicationName();
    }
}
