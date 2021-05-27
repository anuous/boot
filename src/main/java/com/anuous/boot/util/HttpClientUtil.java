package com.anuous.boot.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {

    private static final String Authorization="eyJhbGciOiJIUzUxMiJ9.eyJDVVJSRU5UX1VTRVIiOnsidXNlcmlkIjoiNzQ5MDM5NDA0NDQxNDAzMzkyIiwibG9naW5OYW1lIjoibHVveXVhbmd1YW5nIiwicGhvbmUiOiIxMzY4MTY0Njk2MiIsInRlcm1pbmFsQ29kZSI6ImpkaF9tZ3QiLCJjb2RlIjoiMTcxNjkxIiwidXNlck5hbWUiOiLnvZfmupDlub8ifX0.zdy_Zkr3M__3mQlsdK8UAl8fxA8402fIZpnt45Qim7HIk9CVoCjf6zGYlYJ_e-ECioUyP8N0-HCcTiy0IlFL-A";
    //private static final String Cookie="";

    public static void   doGetWIthoutReturn(String url,String ContractId,String destFileSource,String fileName,String refer) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        FileOutputStream fos = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if(StringUtils.isNotBlank(refer)){
                httpGet.addHeader("Referer",refer);
            }

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                 fos = new FileOutputStream(new File(destFileSource+fileName));
                entity.writeTo(fos);
                System.out.println("下载成功:"+ContractId);
            }else{
                System.out.println("下载失败:"+ContractId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if(fos!=null){
                    fos.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static <T> Object  doGet(String url, Map<String, String> param, Class<? extends T> returnClass) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //T obj = JSON.parseObject(resultString,returnClass);
        return JSON.parseObject(resultString,returnClass);
    }

    public static <T>Object doGet(String url, Class<? extends T> returnClass) {
        return doGet(url, null,returnClass);
    }

    public static <T>Object doPost(String url, Map<String, String> param ,Class<? extends T> returnClass) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //return resultString;
        return  JSON.parseObject(resultString,returnClass);
    }

    public static <T>Object doPost(String url,Class<? extends T> returnClass) {
        return doPost(url, null);
    }

    public static <T>Object doPostJson(String url, String json,Class<? extends T> returnClass) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            httpPost.setHeader("Authorization",Authorization);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return resultString;
        return JSON.parseObject(resultString,returnClass);
    }
}
