package com.anuous.boot.test;

import java.util.ArrayList;
import java.util.List;

public class TestArray {

    public static void main(String[] args) {

        List<A> aList = new ArrayList<A>();
        aList.add(new A("1","2"));
        aList.add(new A("2","2"));

        String [] asd = aList.stream().map(a -> a.getA()).toArray(String[]::new);

        System.out.println(asd);

    }
}


class A {

    private String a;

    private String b;

    public A(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}