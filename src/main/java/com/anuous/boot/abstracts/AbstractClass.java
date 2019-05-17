package com.anuous.boot.abstracts;

abstract class AbstractClass {

    private int age ;

    private String name;

    AbstractClass(int age,String name){
        this.age=age;
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public void say(){
        initName("");
        initLanguage("");
    }

   public  abstract void initName(String initName);

    public  abstract void initLanguage(String word);

}
