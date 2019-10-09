package com.anuous.boot.aop.impl;

import com.anuous.boot.aop.ParamTest;
import org.springframework.stereotype.Service;

public interface AopLearnService {

    public void doLearning(String str) throws Exception;
    public void test(ParamTest test);

}
