package com.anuous.boot;

import com.anuous.boot.aop.impl.AopLearnService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

    @Autowired
    private AopLearnService aopLearnService;

    private static String  str = "";
    @Test
    public void contextLoads() {
    }
    @Before
    public void before(){
        str ="before";
        System.out.println("this test before !" + str);
    }
    @Test
    public void testDolearning() throws Exception{
        aopLearnService.doLearning( str);
    }
    public void after(){
        str = "after";
        System.out.println("this test after ！"+ str);
    }
}
