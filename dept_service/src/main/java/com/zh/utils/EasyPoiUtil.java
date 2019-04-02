package com.zh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.zh.entity.test.TestInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: EasyPoiUtil <br/>
 * Function: EXCEL导入导出工具类. <br/>
 * date: 2019年2月23日 09:41:31<br/>
 *
 * @author hzhang
 * @version 1.0.0
 * @since JDK 1.8
 */
public class EasyPoiUtil {


    private static Logger logger = LoggerFactory.getLogger(EasyPoiUtil.class);

    /**
     *
     * 导出excel
     * @param exportParams 导出参数
     * @param list 数据集
     * @param pojoClass
     * @param file
     * @throws Exception
     */
    public static void exportExcel(ExportParams exportParams, List<?> list, Class<?> pojoClass, File file) throws Exception {
        exportParams.setCreateHeadRows(true);
        Workbook workbook = null;
        try {
            workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.mkdir();
        }
        OutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
    }


    /**
     * 导出excel
     * @param list 数据集
     * @param title 表头
     * @param sheetName sheet名称
     * @param pojoClass 实体
     * @param fileName 文件名
     * @param isCreateHeader  是否创建表头
     * @param response response
     * @throws Exception  exception
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   boolean isCreateHeader, HttpServletResponse response) throws Exception {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * 导出excel
     * @param list 数据集
     * @param title 表头
     * @param sheetName sheet名称
     * @param pojoClass 实体
     * @param fileName 文件名
     * @param response  response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * 导出excel
     * @param list 数据集
     * @param fileName 文件名
     * @param response response
     * @throws Exception  Exception
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
            throws Exception {
        defaultExport(list, fileName, response);
    }

    /**
     * 导入excel
     * @param filePath 文件路径
     * @param titleRows 标题位置
     * @param headerRows 表头位置
     * @param pojoClass 实体
     * @return List<T>  返回list
     * @throws Exception Exception
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass)
            throws Exception {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new Exception("模板不能为空");
        } catch (Exception e) {
            logger.error("出错，错误原因：",e);
            throw new Exception(e.getMessage());
        }
        return list;
    }

    /**
     * 导入excel
     * @param file 文件
     * @param titleRows 标题位置
     * @param headerRows 表头位置
     * @param pojoClass 实体
     * @throws Exception Exception
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)
            throws Exception {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new Exception("excel文件不能为空");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return list;
    }


    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook = null;
        try {
            workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workbook != null)
            try {
                downLoadExcel(fileName, response, workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook)
            throws Exception {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
            throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.XSSF);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }



    public static void main(String[] args){
        List<TestInfo> testInfos = new ArrayList<>();
        for(int i = 0; i<5 ; i++){
            TestInfo testInfo = new TestInfo();
            testInfo.setId("123");
            testInfo.setName("ces1");
            testInfo.setSex(2);
            testInfo.setBirthday(new Date());
            testInfo.setRegistrationDate(new Date());
            testInfos.add(testInfo);
        }
        try {
            ExportParams exportParams = new ExportParams("测试1","",ExcelType.HSSF);
            File file = new File("C:\\Users\\gaoming\\Desktop\\exp\\测试.xls");
            exportExcel(exportParams,testInfos,TestInfo.class,file);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
