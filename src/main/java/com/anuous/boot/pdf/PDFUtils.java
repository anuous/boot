package com.anuous.boot.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PDFUtils {

//    pdf.fontPath=/resources/font/SourceHanSansCN-Normal.ttf
//    pdf.fontSize=14
//    pdf.srcPath.test=/resources/pdf/test-src.pdf
//    pdf.descPath.test=/resources/pdf/desc/test-rest-1.pdf
    private String fontPath ="./src/main/resources/font/SourceHanSansCN-Normal.ttf";

    private Integer fontSize =14;

    private String tempaltePath ="./src/main/resources/pdf/test-src.pdf";

    private String srcPath ="./src/main/resources/pdf/desc/test-rest-4.pdf";

    public String fillSpecialValues(Map<String,String>paramValues) throws Exception {
       return fillSpecialValues(tempaltePath,srcPath,paramValues);

    }
    public String fillSpecialValues(String templatePath, String descPath, Map<String,String>paramValues) throws  Exception{
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templatePath), new PdfWriter(descPath));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        form.setGenerateAppearance(true);

        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
         for(String key : paramValues.keySet()){
             form.getField(key).setValue(paramValues.get(key), font, fontSize);
         }
        pdfDoc.getWriter().flush();
        pdfDoc.close();
        return descPath;
    }


}
