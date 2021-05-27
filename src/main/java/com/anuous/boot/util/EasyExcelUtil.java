import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
*  EasyExcel工具类
 * @author jwhappy
 * @date 2020/4/21
 */
@Slf4j
public class EasyExcelUtil
{
    /**
     * 无模板读取（从sheet1的第1行开始读）
     * @param inputStream
     * @return List<List<String>>
     */
    public static List<Object> read(InputStream inputStream){
        return EasyExcelFactory.read(inputStream, new Sheet(1, 0));
    }

    /**
     * 无模板读取（自己指定从sheet1的哪一行开始读）
     * @param inputStream
     * @param headLineMun 表头占的行数，从0开始
     * @return List<List<String>>
     */
    public static List<Object> read(InputStream inputStream, Integer headLineMun){
        return EasyExcelFactory.read(inputStream, new Sheet(1, headLineMun));
    }

    /**
     * 无模板读取（自己指定sheet和开始的行）
     * 返回 List<List<String>>
     * @param inputStream
     * @param sheetNo sheet页号，从1开始
     * @param headLineMun 表头占的行数，从0开始
     * @return List<List<String>>
     */
    public static List<Object> read(InputStream inputStream, Integer sheetNo, Integer headLineMun){
        return EasyExcelFactory.read(inputStream, new Sheet(sheetNo, headLineMun));
    }

    /**
     * 按模板读取（从sheet1的第2行开始读取）
     * @param inputStream
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @return List<Object>, Object对应具体的clazz类
     */
    public static List<Object> read(InputStream inputStream, final Class<? extends BaseRowModel> clazz){
        return EasyExcelFactory.read(inputStream, new Sheet(1, 1, clazz));
    }

    /**
     * 按模板读取（从指定sheet的第2行开始读）
     * @param inputStream
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param sheetNo sheet页号，从1开始
     * @return List<Object>, Object对应具体的clazz类
     */
    public static List<Object> read(InputStream inputStream, final Class<? extends BaseRowModel> clazz, Integer sheetNo){
        return EasyExcelFactory.read(inputStream, new Sheet(sheetNo, 1, clazz));
    }

    /**
     * 按模板读取（自定义sheet和表头行数）
     * @param inputStream
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param sheetNo sheet页号，从1开始
     * @param headLineMun 表头占的行数，从0开始
     * @return List<Object>, Object对应具体的clazz类
     */
    public static List<Object> read(InputStream inputStream, final Class<? extends BaseRowModel> clazz, Integer sheetNo, Integer headLineMun){
        return EasyExcelFactory.read(inputStream, new Sheet(sheetNo, headLineMun, clazz));
    }

    /**
     * 按模板读取（从sheet1的第2行开始读取）
     * 1000行以上的数据建议用这个读取
     * @param inputStream
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param <T> 模板类泛型
     * @return List<T>
     */
    public static <T extends BaseRowModel> List<T> readModel(InputStream inputStream, final Class<? extends BaseRowModel> clazz){
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, clazz), excelListener);
        return excelListener.getRows();
    }

    /**
     * 按模板读取（从指定sheet的第2行开始读）
     * 1000行以上的数据建议用这个读取
     * @param inputStream
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param sheetNo sheet页号，从1开始
     * @param <T> 模板类泛型
     * @return List<T>
     */
    public static <T extends BaseRowModel> List<T> readModel(InputStream inputStream, final Class<? extends BaseRowModel> clazz, Integer sheetNo){
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(sheetNo, 1, clazz), excelListener);
        return excelListener.getRows();
    }

    /**
     * 按模板读取（自定义sheet和表头行）
     * 1000行以上的数据建议用这个读取
     * @param inputStream
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param sheetNo sheet页号，从1开始
     * @param headLineMun 表头占的行数，从0开始
     * @param <T> 模板类泛型
     * @return List<T>
     */
    public static <T extends BaseRowModel> List<T> readModel(InputStream inputStream, final Class<? extends BaseRowModel> clazz, Integer sheetNo, Integer headLineMun){
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(sheetNo, headLineMun, clazz), excelListener);
        return excelListener.getRows();
    }

    /**
     * 按模板写入（写入sheet1，表头占第一行）
     * @param outputStream
     * @param data 写入的数据（模板对象List）
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     */
    public static void writeModel(OutputStream outputStream, List<? extends BaseRowModel> data, final Class<? extends BaseRowModel> clazz){
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
        Sheet sheet = new Sheet(1, 1, clazz);
        excelWriter.write(data, sheet);
        excelWriter.finish();
    }

    /**
     * 按模板写入（指定sheet，表头占第一行）
     * @param outputStream
     * @param data 写入的数据（模板对象List）
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param sheetNo sheet页号，从1开始
     */
    public static void writeModel(OutputStream outputStream, List<? extends BaseRowModel> data, final Class<? extends BaseRowModel> clazz, Integer sheetNo){
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
        Sheet sheet = new Sheet(sheetNo, 1, clazz);
        excelWriter.write(data, sheet);
        excelWriter.finish();
    }

    /**
     * 按模板写入（指定sheet页和sheet名称，指定表头行数）
     * @param outputStream
     * @param data 写入的数据（模板对象List）
     * @param clazz 模板类，需要继承BaseRowModel类，字段用@ExcelProperty注解
     * @param headLineMun 表头占的行数，从0开始
     * @param sheetNo sheet页号，从1开始
     * @param sheetName sheet名称
     */
    public static void writeModel(OutputStream outputStream, List<? extends BaseRowModel> data, final Class<? extends BaseRowModel> clazz, Integer headLineMun, Integer sheetNo, String sheetName){
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
        Sheet sheet = new Sheet(sheetNo, headLineMun, clazz);
        sheet.setSheetName(sheetName);
        excelWriter.write(data, sheet);
        excelWriter.finish();
    }

    /**
     * 无模板写入（写入sheet1，表头占第一行）
     * @param outputStream
     * @param data 写入的数据（List<List<Object>>）
     * @param table 表设置，可以设置表头字段，以及表的样式设置
     */
    public static void write(OutputStream outputStream, List<List<Object>> data, Table table){
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
        Sheet sheet = new Sheet(1, 0);
        excelWriter.write1(data, sheet, table);
        excelWriter.finish();
    }

    /**
     * 无模板写入（指定sheet，表头占第一行）
     * @param outputStream
     * @param data 写入的数据（List<List<Object>>）
     * @param table 表设置，可以设置表头字段，以及表的样式设置
     * @param sheetNo sheet页号，从1开始
     */
    public static void write(OutputStream outputStream, List<List<Object>> data, Table table, Integer sheetNo){
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
        Sheet sheet = new Sheet(sheetNo, 0);
        excelWriter.write1(data, sheet, table);
        excelWriter.finish();
    }

    /**
     * 无模板写入（指定sheet页和sheet名称，指定表头行数）
     * @param outputStream
     * @param data 写入的数据（List<List<Object>>）
     * @param table 表设置，可以设置表头字段，以及表的样式设置
     * @param headLineMun 表头占的行数，从0开始
     * @param sheetNo sheet页号，从1开始
     * @param sheetName sheet名称
     */
    public static void write(OutputStream outputStream, List<List<Object>> data, Table table, Integer headLineMun, Integer sheetNo, String sheetName){
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
        Sheet sheet = new Sheet(sheetNo, headLineMun);
        sheet.setSheetName(sheetName);
        excelWriter.write1(data, sheet, table);
        excelWriter.finish();
    }

    /**
     * 多个sheet页的数据链式写入
     * ExcelUtil.writeWithSheets(outputStream)
     *                  .writeModel( Collections.singletonList(excelModel), ExcelModel.class, "sheet1Name")
     *                  .write(data,table,"sheet2Name")
     *                  .finish();
     * @param outputStream
     * @return
     */
    public static EasyExcelWriterFactory writeWithSheets(OutputStream outputStream){
        EasyExcelWriterFactory excelWriter = new EasyExcelWriterFactory(outputStream, ExcelTypeEnum.XLSX);
        return excelWriter;
    }

    /**
     * 多个sheet页的数据链式写入
     * ExcelUtil.writeWithSheets(response, exportFileName)
     *                  .writeModel( Collections.singletonList(excelModel), ExcelModel.class, "sheet1Name")
     *                  .write(data,table,"sheet2Name")
     *                  .finish();
     * @param response
     * @param exportFileName 导出文件名
     * @return
     */
    public static EasyExcelWriterFactory writeWithSheetsWeb(HttpServletResponse response, String exportFileName) throws IOException
    {
        //添加响应头信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcelWriterFactory excelWriter = new EasyExcelWriterFactory(response.getOutputStream(), ExcelTypeEnum.XLSX);
        return excelWriter;
    }
}

@Slf4j
class ExcelListener<T extends BaseRowModel> extends AnalysisEventListener<T>
{
    private final List<T> rows = new ArrayList<>();

    @Override
    public void invoke(T object, AnalysisContext context) {
        rows.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("read {} rows", rows.size());
    }

    public List<T> getRows() {
        return rows;
    }
}
