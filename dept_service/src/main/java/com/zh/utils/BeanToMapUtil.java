package com.zh.utils;

import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanToMapUtil {

    private List<Object> prepareSql(StringBuffer sql, Map<String,Object> paramMap){
        List<Object> result = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        int i = 1;
        for(Map.Entry<String, Object> entry : paramMap.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(StringUtils.isNotBlank(key)){
                sql.append(" and " + key + "= ?" + i);
                objectList.add(value);
                i++;
            }
        }
        result.add(sql.toString());
        result.add(objectList);
        return result;
    }

    public static Map dtoToMap(Object obj){
        Map<String,Object> map = new HashMap();
        if(null != obj ){
            Class cla = obj.getClass();
            do{
                Field[] fields = cla.getDeclaredFields();
                for(Field field : fields ){
                    String name = field.getName();
                    try {
                        Method method = cla.getMethod("get"+initCap(name));
                        Object object = method.invoke(obj);
                        if(object==null){
                            continue;
                        }else {
                            map.put(name, object);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                //获取父类属性
                cla = cla.getSuperclass();
            }while(cla != Object.class);
        }
        return map;
    }

    private static String initCap(String attr){
        return attr.substring(0, 1).toUpperCase() + attr.substring(1);
    }

}
