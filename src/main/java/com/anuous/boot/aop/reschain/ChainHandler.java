package com.anuous.boot.aop.reschain;

public abstract  class ChainHandler {

    public void execute(Chain chain){
        this.processHandler();
        chain.process();

    }
    protected abstract void processHandler();
}
