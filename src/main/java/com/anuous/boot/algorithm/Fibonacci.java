package com.anuous.boot.algorithm;

public class Fibonacci {

    /**
     * 斐波拉契数列实现
     * @param n
     * @return
     */
    public static int fab(int n){
        if(n<=2) {
            return 1;
        }
        return fab(n-1)+fab(n-2);
    }

    public static int fab2(int n){
        if(n<0){
            return -1;
        }
        if(n<2){
            return 1;
        }
        int a=0;
        int b=1;
        int res = 0;
        for (int i = 2; i <=n; i++) {
            res = a+b;
            a= b;
            b=res;
        }
        return res;

    }

    public static int tailFab(int n,int resa, int resb){//reas = f(n-1),resb=f(n-2)
        if(n<=0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return resa+resb;
        }
        int res = resa+resb;
        resb=resa;
        resa=res;
        return tailFab(n-1,resa,resb);
    }
    public static void main(String[] args) {

        for(int i=1;i<15;i++){
            long start= System.currentTimeMillis();
            System.out.print(i+"的fab value ="+tailFab(i,1,0)+" , ");
            long time= System.currentTimeMillis()-start;
            System.out.println("耗时为："+time+"ms");
        }

    }
}
