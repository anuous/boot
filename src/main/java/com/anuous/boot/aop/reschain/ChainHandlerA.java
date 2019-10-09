package com.anuous.boot.aop.reschain;

public class ChainHandlerA extends ChainHandler {

    @Override
    protected void processHandler() {
        System.out.println("this is in ChainHandlerA");
    }
}
