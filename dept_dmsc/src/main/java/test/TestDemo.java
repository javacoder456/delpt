package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestDemo {

    public static final String POST_URL = "http://localhost:8080/last/testService";
    public static void httpURLConnectionPOST () {
        try {
            URL url = new URL(POST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

            String app_key = "context="+ URLEncoder.encode("cs", "utf-8");
            // 将参数输出到连接
            dataout.writeBytes(app_key);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close();
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
            bf.close();
            connection.disconnect();
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        httpURLConnectionPOST();
    }




}
