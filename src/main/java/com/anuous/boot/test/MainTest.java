package com.anuous.boot.test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainTest {
    private static Pattern  RSS = Pattern.compile("^\\s*\\S+\\s*$");
    public static void main(String[] args)    {
        Map map =new HashMap<String,String>();
        map.put("jdh12548","123");
        map.put("jdh12549","345");
        List<User> userList = new ArrayList<User>();
        User a=new User("jdh12548","18681882257");
        User b=new User("jdh12549","15934863258");
        User c=new User("jdh12540","13684568645");
        userList.add(c);
        userList.add(b);
        userList.add(a);
        for(int i=0;i<userList.size();i++){
            if("13684568645".equals(userList.get(i).getMobileNo())){
                continue;
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                String value= (String) entry.getValue();
                if(key.equals(userList.get(i).getLoginName())){
                    System.out.println(String.format("给手机号【%s】的用户发送用户名【%s】和密码【%s】",userList.get(i).getMobileNo(),key,value));
                }
            }
        }

    }

    private void loop(int i){
        System.out.println("current  i = " + (i++));
        loop(i);
    }

    private static List<String> collect(List<List<String>> lists){
        lists.stream().map(strLists->strLists.stream().map(str->str.toString())).collect(Collectors.toList());
        return null;
    }
    public static class User{
        private String loginName;

        private String mobileNo;

        public User(String loginName, String mobileNo) {
            this.loginName = loginName;
            this.mobileNo = mobileNo;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }
    }
}
