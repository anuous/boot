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
import java.util.Collections;
import java.util.HashMap;
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
}
