package com.anuous.boot.aop.reschain;

public class ChainHandlerB extends ChainHandler {

    @Override
    protected void processHandler() {
        System.out.println("this is in ChainHandlerB");
    }
}
