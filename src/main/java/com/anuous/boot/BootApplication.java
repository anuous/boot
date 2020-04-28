package com.anuous.boot;

import com.anuous.boot.listener.MyApplicationEnvironmentPreparedEventListener;
import com.anuous.boot.listener.MyApplicationFailedEventListener;
import com.anuous.boot.listener.MyApplicationPreparedEventListener;
import com.anuous.boot.listener.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.anuous.boot"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class BootApplication {

    private  int value;
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

        /*StudyServiceImpl impl = new StudyServiceImpl();
        StudyServiceImpl2 impl2 = new StudyServiceImpl2();
        StaticProxyStudyService staticProxyStudyService = new StaticProxyStudyService(impl,impl2);
        staticProxyStudyService.begin();
        staticProxyStudyService.study();*/
//        StudyServiceImpl impl = new StudyServiceImpl();
//        //System.out.println(DynProxyFactory.getProxyInstance(impl));
//        IStudyService proxy = (IStudyService) DynProxyFactory.getProxyInstance(impl);
//        proxy.begin();
    }

    
}
