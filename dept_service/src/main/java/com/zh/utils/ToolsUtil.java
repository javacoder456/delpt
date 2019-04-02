package com.zh.utils;

import java.io.File;
import java.sql.Date;


public class ToolsUtil {

    /**
     * 获取批次号
     * @param num
     * @return
     */
    public synchronized static String getBatchNo(Integer num) {
        if (num >= 999) {
            num = 0;
        }
        num++;
        return  Date.valueOf("yyyyMMddHHmmssSSS") + String.format("%1$,03d", num);
    }

    public static String getResetFilePath(String localPath,String fileName){
        return localPath + File.separator + fileName;
    }


}
