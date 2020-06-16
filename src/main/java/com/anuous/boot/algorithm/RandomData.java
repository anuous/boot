package com.anuous.boot.algorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomData {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("D://tmp.txt");
        int i = 0;
        long startTime = System.currentTimeMillis();
        while(i<1000000){
            fw.write(""+BigDecimal.valueOf(999*Math.random()).setScale(2,RoundingMode.HALF_DOWN)+"\r\n");
            i++;
        }
        System.out.println("写入初始数据花费时间："+(System.currentTimeMillis()-startTime)+" ms");
        fw.close();
    }
}
