package com.zh.utils;

import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesUtil {

    private static Properties props;
    private static String file = "properties/params.properties";

    public static void setFile(String file) {
        ReadPropertiesUtil.file = file;
    }

    /**
     * 获取properties配置文件
     * @param key
     * @return
     */
    public static String getValue(String key){
        if(props == null){
            getResource();
        }
        return props.getProperty(key);
    }

    /**
     * GetProperties
     * 使用ClassLoader加载properties配置文件生成对应的输入流
     * @return
     */
    public static void getResource() {
        if (props == null) {
            try {
                // 使用properties对象加载输入流
                props = new Properties();
                InputStream is = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream(file);
                props.load(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
