package com.anuous.boot.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import org.springframework.stereotype.Component;

import java.util.List;
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

    private String srcPath ="./src/main/resources/pdf/desc/test-desc-4.pdf";

    private String desc ="./src/main/resources/pdf/desc/test-desc-4.pdf";

    private String tmp1 ="./src/main/resources/pdf/desc/tmp1-desc-4.pdf";

    private String tmp2 ="./src/main/resources/pdf/desc/tmp2-desc-4.pdf";


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


    public String fillValueAndInsertTables(String[] headers,List<Invoice> invoices,Map<String,String>paramValues)throws Exception{
        //1、根据PDF模板 填充域 生产新的PDF
        PdfDocument tmpPdf1 = new PdfDocument(new PdfReader(tempaltePath), new PdfWriter(tmp1));
        PdfAcroForm form = PdfAcroForm.getAcroForm(tmpPdf1, true);
        form.setGenerateAppearance(true);
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
        for(String key : paramValues.keySet()){
            form.getField(key).setValue(paramValues.get(key), font, fontSize);
        }
        tmpPdf1.close();
        //2、生产表格临时PDF文件2
        PdfDocument tmpPdf2 = new PdfDocument( new PdfWriter(tmp2));
        Document doc = new Document(tmpPdf2, new PageSize(PageSize.A4));
        //doc.setMargins(40, 30, 30, 30);
        Table table = createTables(headers,invoices);
        doc.add(table);
        //doc.setRenderer(new DocumentRenderer(doc));
        doc.close();
        //3、PDF文件合并
        PdfWriter writer = new PdfWriter(srcPath);
        writer.setSmartMode(true);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.initializeOutlines();
        PdfDocument addedDoc1 = new PdfDocument(new PdfReader(tmp1));
        addedDoc1.copyPagesTo(1, addedDoc1.getNumberOfPages(), pdfDoc);
        addedDoc1.close();
        PdfDocument addedDoc2 = new PdfDocument(new PdfReader(tmp2));
        addedDoc2.copyPagesTo(1, addedDoc2.getNumberOfPages(), pdfDoc);
        addedDoc2.close();

        pdfDoc.close();
        return srcPath;
    }
    /**
     *创建PDF表格
     * @param headers  表头
     * @param invoices 表格数据
     * @return
     * @throws Exception
     */
    private Table createTables(String[]headers,List<Invoice> invoices) throws  Exception{
        if(headers==null || headers.length ==0){
            return null;
        }
        if(invoices == null || invoices.size()==0){
            return null;
        }
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
        Table table =new Table(new float[headers.length]);
        for (int i = 0; i < headers.length; i++) {
            table.addCell(headers[i]).setFont(font).setAutoLayout();
        }
        for (Invoice invoice:invoices) {
            table.addCell(invoice.getA()).setFont(font).setAutoLayout();
            table.addCell(invoice.getB()).setFont(font).setAutoLayout();
            table.addCell(invoice.getC()).setFont(font).setAutoLayout();
            table.addCell(invoice.getD()).setFont(font).setAutoLayout();
            table.addCell(invoice.getE()).setFont(font).setAutoLayout();
            table.addCell(invoice.getF()).setFont(font).setAutoLayout();
        }
        return table;
    }


    protected static class ExtraTableRenderer extends DocumentRenderer {

        public ExtraTableRenderer(Document document) {
            super(document);
        }

        // If renderer overflows on the next area, iText uses getNextRender() method to create a renderer for the overflow part.
        // If getNextRenderer isn't overriden, the default method will be used and thus a default rather than custom
        // renderer will be created
        @Override
        public IRenderer getNextRenderer() {
            return new ExtraTableRenderer(document);
        }

        @Override
        protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
            LayoutArea area = super.updateCurrentArea(overflowResult);
            if (area.getPageNumber() == 1) {
                area.getBBox().decreaseHeight(266);
            }

            return area;
        }

    }

}
