package com.anuous.boot.aop.impl;

import com.anuous.boot.aop.ParamTest;
import com.anuous.boot.aop.annotation.Learning;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AopLearnServiceImpl implements AopLearnService {

    @Override
    @Learning
    public void doLearning(String str) throws Exception {
        int length = StringUtils.isEmpty(str)?0:str.length();
        switch (length){
            case 0:
                System.out.println("学习中开小差了");
                throw new Exception("");
                default:
                    System.out.println(String.format("%s 正在学习中！",str));
        }

    }

    @Override
    public void test(ParamTest test) {
        System.out.println(test.getName());
    }
}
