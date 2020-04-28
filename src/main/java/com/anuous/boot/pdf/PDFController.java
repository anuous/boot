package com.anuous.boot.pdf;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    private PDFUtils PpdfUtils;

    @RequestMapping(method= RequestMethod.GET,value = "/fill")
    public void testFillValue(HttpServletResponse response){

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        Map<String,String> paramValues = new HashMap<>(4);
        paramValues.put("textArea-A","textArea-A:测试域 A企业");
        paramValues.put("textArea-B","textArea-B:测试域 B企业");
        paramValues.put("SignatureA","签名域");
        String tempfilePath ="";
        FileInputStream fis = null;
        try {
            tempfilePath= PpdfUtils.fillSpecialValues(paramValues);
             fis= new FileInputStream(tempfilePath);
             byte[] re = {};
            ByteArrayInputStream bis = new ByteArrayInputStream(re);


            IOUtils.copy (fis, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(tempfilePath!=null && tempfilePath.length()>0){
                File file = new File(tempfilePath);
                if(file.isFile()){
                    file.delete();
                }
            }
        }
    }

    @RequestMapping(method= RequestMethod.GET,value = "/table")
    public void createTable(HttpServletResponse response) throws  Exception{

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        String[] headers=new String[]{"发票号码","发票代码","开票日期","购货方","销货方（经销商）","发票金额"};
        List<Invoice> invoices = new ArrayList<Invoice>();
        int res =0;
        while(res<102){
            Invoice tmp = new  Invoice("10001"+res,"1","20191029", "木森林股份有限公司","世界上最长的公司是我家是我家是我家是我家是我家是我家","100000");
            invoices.add(tmp);
            res++;
        }
        String tempfilePath ="";
        FileInputStream fis = null;
        try {
            tempfilePath= PpdfUtils.insertTablesToPDF(headers,invoices);
            fis= new FileInputStream(tempfilePath);
            byte[] re = {};
            ByteArrayInputStream bis = new ByteArrayInputStream(re);

            IOUtils.copy (fis, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(tempfilePath!=null && tempfilePath.length()>0){
                File file = new File(tempfilePath);
                if(file.isFile()){
                    file.delete();
                }
            }
        }
    }

    @RequestMapping(method= RequestMethod.GET,value = "/fillTable")
    public void testFillValueAndInsertTable(HttpServletResponse response){

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        Map<String,String> paramValues = new HashMap<>(4);
        paramValues.put("textArea-A","textArea-A:测试域 A企业");
        paramValues.put("textArea-B","textArea-B:测试域 B企业");
        paramValues.put("SignatureA","签名域");
        String[] headers=new String[]{"发票号码","发票代码","开票日期","购货方","销货方（经销商）","发票金额"};
        List<Invoice> invoices = new ArrayList<Invoice>();
        Invoice invoice = new  Invoice("10001","1","20191029", "木森林股份有限公司","世界上最长的公司是我家是我家是我家是我家是我家是我家","100000");
        Invoice invoice1 = new  Invoice("10002","2","20191029","木森林股份有限公司","世界上最长的公司是我家","100000");
        Invoice invoice2 = new  Invoice("10003","3","20191029","木森林股份有限公司","世界上最长的公司是我家","100000");
        Invoice invoice3 = new  Invoice("10004","4","20191029","木森林股份有限公司","世界上最长的公司是我家","100000100000");
        invoices.add(invoice);
        invoices.add(invoice1);
        invoices.add(invoice2);
        invoices.add(invoice3);
        int res =0;
        while(res<102){
            Invoice tmp = new  Invoice("10001"+res,"1","20191029", "木森林股份有限公司","世界上最长的公司是我家是我家是我家是我家是我家是我家","100000");
            invoices.add(tmp);
            res++;
        }
        String tempfilePath ="";
        FileInputStream fis = null;
        try {
            tempfilePath= PpdfUtils.fillValueAndInsertTables(headers,invoices,paramValues);
            fis= new FileInputStream(tempfilePath);
            byte[] re = {};
            ByteArrayInputStream bis = new ByteArrayInputStream(re);

            IOUtils.copy (fis, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(tempfilePath!=null && tempfilePath.length()>0){
                File file = new File(tempfilePath);
                if(file.isFile()){
                    file.delete();
                }
            }
        }
    }
}
