<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
    <base href="<%=basePath%>" >
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ include file="pubLib.html" %>
</head>
<body>  
   <div class="notibar announcement">
       <a class="close"></a>
       <h3>通知公告</h3>
       <p>这是通知公告消息体，内容可以后台发送动态显示！</p>
   </div></br><!-- notification announcement -->
   <!-- 动态数据的加载需调用异步加载即可   -->
   <!--<div id="chartTitile" style="text-align:center;">
      <h3>停车场实时车位统计表(24H)</h3></br>
   </div>
   <div id="chartplace" style="height:450px;width:98%"></div>
   -->
</body>
</html>  
    