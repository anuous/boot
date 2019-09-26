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
        String url1 = "https://hr360.tcl.com/Exam/Evaluate360/Exam?ProjectId=1624&moduleId=1031462&pageIndex=2&pid=0&IsIndividual=False";
        String url="https://hr360.tcl.com/Exam/Home/InvisibleLogin?code=JVb%2fxKoE8sX9eIziZCp2sHvnGkfbPkZX2Zsb4BEPSRcYZfJW8p%2bc7AuLCRDjA71rn7COLJ7IsHrtJk6%2bfJXESQ%3d%3d";
        Document doc = Jsoup.connect(url).get();
        String list360 = doc.outerHtml();
       System.out.println(list360);



    }
}
