package com.zh.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: JDBCUtil <br/>
 * Function: JDBC简单封装的工具类. <br/>
 * date: 2018年5月21日  <br/>
 *
 * @author zhangheng
 * @version 1.0.0
 * @since JDK 1.8
 */
public class JDBCUtil {

        //数据库用户名
        private static final String USERNAME = "root";
        //数据库密码
        private static final String PASSWORD = "root";
        //驱动信息
        private static final String DRIVER = "com.mysql.jdbc.Driver";
        //数据库地址
        private static final String URL = "jdbc:mysql://localhost:3306/bbs?useSSL=false";
        private Connection connection = null;
        private PreparedStatement pstmt = null;
        private ResultSet resultSet = null;
        public JDBCUtil() {
            try{
                Class.forName(DRIVER);
                System.out.println("数据库连接成功！");

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        /**
         * 获得数据库的连接
         * @return
         */
        public Connection getConnection(){
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

        /**
         * 增、删、改
         * @param sql
         * @param params list参数列表
         * @return 返回结果
         * @throws SQLException
         */
        public boolean updateByPreStatement(String sql, List<Object>params)throws SQLException{
            boolean flag = false;
            int result = -1;
            pstmt = connection.prepareStatement(sql);
            int index = 1;
            if(params != null && !params.isEmpty()){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(index++, params.get(i));
                }
            }
            result = pstmt.executeUpdate();
            flag = result > 0 ? true : false;
            return flag;
        }

        /**
         * 查询单条记录
         * @param sql
         * @param params
         * @return
         * @throws SQLException
         */
        public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException{
            Map<String, Object> map = new HashMap<String, Object>();
            int index  = 1;
            pstmt = connection.prepareStatement(sql);
            if(params != null && !params.isEmpty()){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(index++, params.get(i));
                }
            }
            resultSet = pstmt.executeQuery();//返回查询结果
            ResultSetMetaData metaData = resultSet.getMetaData();
            int col_len = metaData.getColumnCount();
            while(resultSet.next()){
                for(int i=0; i<col_len; i++ ){
                    String cols_name = metaData.getColumnName(i+1);
                    Object cols_value = resultSet.getObject(cols_name);
                    if(cols_value == null){
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
            }
            return map;
        }

        /**
         * 查询多条记录
         * @param sql
         * @param params
         * @return
         * @throws SQLException
         */
        public List<Map<String, Object>> findModelResults(String sql, List<Object> params) throws SQLException{
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            int index = 1;
            pstmt = connection.prepareStatement(sql);
            if(params != null && !params.isEmpty()){
                for(int i = 0; i<params.size(); i++){
                    pstmt.setObject(index++, params.get(i));
                }
            }
            resultSet = pstmt.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int cols_len = metaData.getColumnCount();
            while(resultSet.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                for(int i=0; i<cols_len; i++){
                    String cols_name = metaData.getColumnName(i+1);
                    Object cols_value = resultSet.getObject(cols_name);
                    if(cols_value == null){
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
                list.add(map);
            }

            return list;
        }

        /**
         * 通过反射机制查询单条记录
         * @param sql
         * @param params
         * @param cls
         * @return
         * @throws Exception
         */
        public <T> T findSimpleRefResult(String sql, List<Object> params, Class<T> cls )throws Exception{
            T resultObject = null;
            int index = 1;
            pstmt = connection.prepareStatement(sql);
            if(params != null && !params.isEmpty()){
                for(int i = 0; i<params.size(); i++){
                    pstmt.setObject(index++, params.get(i));
                }
            }
            resultSet = pstmt.executeQuery();
            ResultSetMetaData metaData  = resultSet.getMetaData();
            int cols_len = metaData.getColumnCount();
            while(resultSet.next()){
                //通过反射机制创建一个实例
                resultObject = cls.newInstance();
                for(int i = 0; i<cols_len; i++){
                    String cols_name = metaData.getColumnName(i+1);
                    Object cols_value = resultSet.getObject(cols_name);
                    if(cols_value == null){
                        cols_value = "";
                    }
                    Field field = cls.getDeclaredField(cols_name);
                    field.setAccessible(true); //打开javabean的访问权限
                    field.set(resultObject, cols_value);
                }
            }
            return resultObject;

        }

        /**
         * 通过反射机制查询多条记录
         * @param sql
         * @param params  list参数
         * @param cls 实体类
         * @return
         * @throws Exception
         */
        public <T> List<T> findMoreRefResults(String sql,List<Object> params,Class<T> cls)throws Exception {
            List<T> list = new ArrayList<T>();
            int index = 1;
            pstmt = connection.prepareStatement(sql);
            if(params != null && !params.isEmpty()){
                for(int i = 0; i<params.size(); i++){
                    pstmt.setObject(index++, params.get(i));
                }
            }
            resultSet = pstmt.executeQuery();
            ResultSetMetaData metaData  = resultSet.getMetaData();
            int cols_len = metaData.getColumnCount();
            while(resultSet.next()){
                //通过反射机制创建一个实例
                T resultObject = cls.newInstance();
                for(int i = 0; i<cols_len; i++){
                    String cols_name = metaData.getColumnName(i+1);
                    Object cols_value = resultSet.getObject(cols_name);
                    if(cols_value == null){
                        cols_value = "";
                    }
                    Field field = cls.getDeclaredField(cols_name);
                    field.setAccessible(true); //打开javabean的访问权限
                    field.set(resultObject, cols_value);
                }
                list.add(resultObject);
            }
            return list;
        }

        /**
         *释放数据库连接
         *
         */
        public void releaseConn(){
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(pstmt != null)
            {  try{
                    pstmt.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null)
            {  try{
                    connection.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }

        }

       


}
