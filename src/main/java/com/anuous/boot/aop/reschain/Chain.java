package com.anuous.boot.aop.reschain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class Chain {
    private List<ChainHandler> handlerList;
    private int index = 0;

    public List<ChainHandler> getHandlerList() {
        return handlerList;
    }

    public void setHandlerList(List<ChainHandler> handlerList) {
        this.handlerList = handlerList;
    }



    public void process(){
        if(index>= handlerList.size()){
            return;
        }
        handlerList.get(index++).execute(this);
    }
}
