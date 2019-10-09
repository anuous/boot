package com.anuous.boot.aop.reschain;

public class ChainHandlerC extends ChainHandler {

    @Override
    protected void processHandler() {
        System.out.println("this is in ChainHandlerC");
    }
}
