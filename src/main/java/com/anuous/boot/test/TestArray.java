package com.anuous.boot.test;

public class TestArray {

    public static void main(String[] args) {
         int a = 3;
         int b =a;
         int c = a;

        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(b ==c);

        System.out.println("===================");
        a = 6;

        System.out.println(b==c);


    }
}
