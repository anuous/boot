package com.anuous.boot;

import com.anuous.boot.listener.MyApplicationEnvironmentPreparedEventListener;
import com.anuous.boot.listener.MyApplicationFailedEventListener;
import com.anuous.boot.listener.MyApplicationPreparedEventListener;
import com.anuous.boot.listener.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
       //ApplicationContext context =  SpringApplication.run(BootApplication.class, args);
        SpringApplication application = new SpringApplication(BootApplication.class);
        application.addListeners(new MyApplicationStartedEventListener());
        application.addListeners(new MyApplicationEnvironmentPreparedEventListener());
        application.addListeners(new MyApplicationPreparedEventListener());
        application.addListeners(new MyApplicationFailedEventListener());
        ApplicationContext context = application.run(args);
       String [] beannNames = context.getBeanDefinitionNames();
       /*for(int i =0,size = beannNames.length;i<size;i++){
           System.out.println(i+"\t"+ beannNames[i]);
       }*/

       BigDecimal a = new BigDecimal(1);
        //System.out.println(a);
        BigDecimal b = new BigDecimal(2);
        BigDecimal c = new BigDecimal(0);
        c = a.add(b);
        System.out.println(c);
        c=c.add(a);
        System.out.println(c);

    }

    
}
