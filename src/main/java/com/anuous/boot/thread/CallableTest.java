package com.anuous.boot.thread;

import java.util.concurrent.*;

public class CallableTest {

    public static  void main(String [] args){
        System.out.println("hello");
        LinkedBlockingQueue<Stu>  queues = new LinkedBlockingQueue<Stu>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               System.out.println("asdf is testing !");
            }
        });
        thread.start();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledThreadPoolExecutor =Executors.newScheduledThreadPool(10);
        Future<?> task = executor.submit(new Walk());

        System.out.println(task.isDone());;
    }


}
 class  Run implements Callable<Stu>{
    @Override
    public Stu call() {
        System.out.println("we are running !");
        return new Stu("callAble");
    }
}

 class Walk implements Runnable{

    public Walk(){}

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("开始休眠------10S");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠结束------");
        System.out.println("we are walking !");
    }
}

 class Stu implements Callable<String>{

    private String indexStr;

    public Stu(String indexStr){
        this.indexStr=indexStr;
    }

    @Override
    public String call() {
        System.out.println(this.indexStr);
        return indexStr;
    }
}