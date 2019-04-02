package com.zh.common.dbmanger.tran.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.service.InterfaceconfigService;
import com.zh.common.CommonConstant;
import com.zh.common.dbmanger.AppMessage;
import com.zh.common.dbmanger.datasource.MultiDataSourceHolder;
import com.zh.common.dbmanger.tran.CommonDealDataService;
import com.zh.entity.Interfaceconfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommonQueryService implements CommonDealDataService {

    private static Logger logger = LoggerFactory.getLogger(CommonQueryService.class);

    @Resource
    private DataSource dataSource;

    @Resource
    private InterfaceconfigService interfaceService;

    @Override
    public Interfaceconfig initConfig(Map<String, String> reqMap) {
        MultiDataSourceHolder.changeDataSource(CommonConstant.DATASOURCE_LAST);
        return interfaceService.getById(reqMap.get("tradeid"));
    }

    @Override
    public Interfaceconfig initConfigForPage(Map<String, String> reqMap) {
        Integer pageNo = Integer.parseInt(reqMap.get("pageNo"));
        Integer pageSize = Integer.parseInt(reqMap.get("pageSize"));
        Interfaceconfig config = initConfig(reqMap);
        StringBuffer sql = new StringBuffer(config.getSqltext());
        StringBuffer common = new StringBuffer();
        Integer pageStart = (pageNo-1) * pageSize;
        common.append(" limit ").append(pageStart).append(",").append(pageSize);
        sql.append(common);
        config.setSqltext(sql.toString());
        return config;
    }

    @Override
    public Integer getTotal(Map<String, String> reqMap) throws IOException {
        Interfaceconfig config = initConfig(reqMap);
        String[] array = config.getSqltext().split("FROM",2);
        if(array.length <= 1){
            array = config.getSqltext().split("from",2);
        }
        StringBuffer countSQL = new StringBuffer("SELECT COUNT(*) as total FROM");
        countSQL.append(array[1]);
        config.setSqltext(countSQL.toString());
        try {
            JSONObject jsonObject = dealResult(reqMap,config);
            JSONArray result = (JSONArray)jsonObject.get("result");
            JSONObject total = (JSONObject)result.get(0);
            return total.getInteger("total");
        }catch (Exception e){
            throw new IOException("获取分页合计信息出错：",e);
        }
    }

    @Override
    public void changeCharSet(Map<String, String> reqMap) {

    }

    @Override
    public AppMessage checkData(Map<String, String> reqMap) {
        return null;
    }

    @Override
    public AppMessage checkAuth(Map<String, String> reqMap, Interfaceconfig config) {
        return null;
    }

    @Override
    public JSONObject dealResult(Map<String, String> reqMap, Interfaceconfig interfaceConfig)
            throws IOException {
        JSONObject json = new JSONObject();
        AppMessage appMsg = new AppMessage();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //数据源配置
        if(interfaceConfig == null){
            appMsg.setAppCode("1");
            appMsg.setErrMsg("无法获取相关参数信息！");
            return json;
        }
        String dataSourceId = interfaceConfig.getDatasourceid();
        String type = interfaceConfig.getTradeservicetype();
        String sqlText = interfaceConfig.getSqltext().trim();
        if (StringUtils.isEmpty(dataSourceId) || StringUtils.isEmpty(type)
                || StringUtils.isEmpty(sqlText)) {
            return json;
        }
        MultiDataSourceHolder.changeDataSource(dataSourceId);
        try {
            connection = dataSource.getConnection();
            if(CommonConstant.QUERY_TYPE.equals(type)){
                preparedStatement = connection.prepareStatement(sqlText);
                resultSet = preparedStatement.executeQuery();
                json.put("result",findForJson(resultSet));
            }else {
                int result = updateBySql(sqlText,connection);
                json.put("result",result);
            }
            logger.info("result:{}",json.toJSONString());
            return json;
        }catch (Exception e){
            appMsg.setAppCode("1");
            appMsg.setErrMsg("获取数据失败！");
            json.put("appMsg",appMsg);
            try {
                if(connection!= null){
                    connection.close();
                }
            }catch (Exception ex){
                logger.error("获取数据库连接异常1{}",ex);
            }
            try {
                if(preparedStatement!= null){
                    preparedStatement.close();
                }
            }catch (Exception ex){
                logger.error("获取数据库连接异常2{}",ex);
            }
            try {
                if(resultSet!= null){
                    resultSet.close();
                }
            }catch (Exception ex){
                logger.error("获取数据库连接异常3{}",ex);
            }
            throw new IOException("获取数据异常：",e);
        }finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null) {
                try{
                    preparedStatement.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                try{
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public JSONObject dealCellResult(String fwbh) throws IOException {
        Interfaceconfig config = interfaceService.getById(fwbh);
        return dealResult(new HashMap<String, String>(),config);
    }

    /**
     * 增、删、改
     * @param sql
     * @param connection
     * @return 返回结果
     * @throws SQLException
     */
    private int updateBySql(String sql,Connection connection) throws SQLException{
        return connection.prepareStatement(sql).executeUpdate();
    }

    /**
     * 查询多条记录
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private JSONArray findForJson(ResultSet resultSet) throws SQLException {
        JSONArray array = new JSONArray();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount(); // 获取列数
        // 遍历ResultSet中的每条数据
        while (resultSet.next()) {
            // 遍历每一列
            JSONObject jsonObj = new JSONObject(); //json对象
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = resultSet.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        return array;
    }

}
