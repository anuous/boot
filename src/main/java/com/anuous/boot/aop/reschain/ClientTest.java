package com.anuous.boot.aop.reschain;

public class ClientTest {

    public static void main(String[] args) {
        Handler handlerA =new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();

        handlerA.setHandler(handlerB);
        handlerB.setHandler(handlerC);
        handlerA.execute();
    }
}
