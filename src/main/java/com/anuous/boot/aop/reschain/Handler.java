package com.anuous.boot.aop.reschain;

public abstract class Handler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void execute(){
        this.processd();
        if(handler!=null){
            handler.execute();
        }
    }
    protected abstract void processd();
}
