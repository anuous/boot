package com.anuous.boot.aop.reschain;

import java.util.Arrays;
import java.util.List;

public class ChainClientTest {
    public static void main(String[] args) {
        List<ChainHandler> handlerList = Arrays.asList(new ChainHandlerA(),new ChainHandlerB(),new ChainHandlerC());
        Chain chain = new Chain();
        chain.setHandlerList(handlerList);
        chain.process();
    }
}
