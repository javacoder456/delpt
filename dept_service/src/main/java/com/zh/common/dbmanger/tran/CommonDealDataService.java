package com.zh.common.dbmanger.tran;

import com.alibaba.fastjson.JSONObject;
import com.zh.common.dbmanger.AppMessage;
import com.zh.entity.Interfaceconfig;
import java.io.IOException;
import java.util.Map;


public interface CommonDealDataService {

    /**
     * 初始化参数配置
     * @param reqMap
     */
    Interfaceconfig initConfig(Map<String,String> reqMap);

    /**
     * 初始化参数配置 分页sql
     * @param reqMap
     */
    Interfaceconfig initConfigForPage(Map<String,String> reqMap);

    Integer getTotal(Map<String,String> reqMap) throws IOException;

    /**
     * 编码转换
     * @param reqMap
     */
    void changeCharSet(Map<String,String> reqMap);

    /**
     * 校验参数
     * @param reqMap
     */

    AppMessage checkData(Map<String,String> reqMap);

    /**
     * 权限校验
     * @param reqMap
     * @param config
     * @return
     */
    AppMessage checkAuth(Map<String,String> reqMap , Interfaceconfig config);

    /**
     * 数据处理
     * @param reqMap
     * @param config
     * @return
     */
    JSONObject dealResult(Map<String,String> reqMap, Interfaceconfig config) throws IOException;

    /**
     * 数据处理内部调用方法
     * @param fwbh 服务编号
     * @return JSONObject
     */
    JSONObject dealCellResult(String fwbh) throws IOException;
}
