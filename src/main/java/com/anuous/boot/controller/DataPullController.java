package com.anuous.boot.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * jsoup java数据爬取
 */
public class DataPullController {

    public static void  main(String[] agrs) throws IOException {
        Document doc = Jsoup.connect("http://www.tcmap.com.cn/list/daima_list.html").get();
        Elements list360 = doc.getElementsByAttributeValue("id","list360");
       System.out.println(list360.size());
       List<String> lists = list360.eachText();
        for (String str:lists) {
           String[] res= str.split(" ");
            for (String s: res) {
                if(StringUtils.isEmpty(s.trim())){
                    continue;
                }
                System.out.print(s.trim()+" ");
            }
            System.out.println();
        }



    }
}
