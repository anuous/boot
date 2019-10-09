package com.anuous.boot.pdf;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class PdfCommonFactory {


    /**
     * 在pdf上创建表单域
     *
     * @param pdf
     * @param savePath
     * @param fields：  [{
     *                 "page": "1",
     *                 "positionX": "x轴的距离",
     *                 "positionY": "y轴的距离",
     *                 "width": "长",
     *                 "height": "宽",
     *                 "font":"字体，ttf格式",
     *                 "fontSize": "字体大小",
     *                 "name":"表单域的名称",
     *                 "value":"表单域的值，可选"
     *                 },
     *                 {
     *                 "page": "1",
     *                 "positionX": "x轴的距离",
     *                 "positionY": "y轴的距离",
     *                 "width": "长",
     *                 "height": "宽",
     *                 "font":"字体，ttf格式",
     *                 "fontSize": "字体大小",
     *                 "name":"表单域的名称",
     *
     *
     *                 "value":"表单域的值，可选"
     *                 }
     *                 ]
     * @throws IOException
     */
    public static void createText(File pdf, String savePath, JSONArray fields) throws IOException {
        // 编辑后的文件
        PdfWriter pdfWriter = new PdfWriter(savePath);
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdf), pdfWriter);
        PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDocument, true);
        //font
        String ttfPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX.concat("font")).getPath();
        FontProgram fontProgram;
        PdfFont font;
        fontProgram = FontProgramFactory.createFont("G:\\intelijIdea_workplace\\tool\\src\\main" +
                "\\resources\\font\\STKAITI.TTF");
        font = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);

        PdfTextFormField pdfTextFormField;
        JSONObject field;
        Rectangle rectangle;

        for (int i = 0; i < fields.size(); i++) {
            field = fields.getJSONObject(i);
            // 读取ttf字体文件
            fontProgram = FontProgramFactory.createFont(ttfPath.concat(File.separator).concat(field.getString
                    ("font")));
            // 编码使用 PdfEncodings.IDENTITY_H
            font = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);
            // 设置表单域的位置
            rectangle = new Rectangle(field.getFloatValue("positionX"), field.getFloatValue("positionY"), field
                    .getFloatValue("width"), field.getFloatValue("height"));
            pdfTextFormField = PdfTextFormField.createText(pdfDocument, rectangle, field.getString("name"), field
                    .getString("value"));
            pdfTextFormField.setBorderWidth(0).setReadOnly(true).setFontAndSize(font,
                    field.getIntValue("fontSize"));
            /** 将表单域加入pdf的指定页中 */
            pdfAcroForm.addField(pdfTextFormField, pdfDocument.getPage(field.getIntValue("page")));

        }
        // 将表单域中的value嵌入到pdf文件中
        pdfAcroForm.flattenFields();
        pdfDocument.close();
        pdfWriter.close();
    }
}
