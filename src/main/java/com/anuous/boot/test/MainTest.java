package com.anuous.boot.test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MainTest {
    private static Pattern  RSS = Pattern.compile("^\\s*\\S+\\s*$");
    public static void main(String[] args) {
        System.out.println(RSS.matcher("TCL集团有限公司").matches());
    }

    private void loop(int i){
        System.out.println("current  i = " + (i++));
        loop(i);
    }
}
