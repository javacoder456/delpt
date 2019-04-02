<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>错误页面</title>
    <%@ include file="pubLib.html" %>
</head>

<body>
<div class="bodywrapper">
    <div class="contentwrapper padding10">
    	<div class="errorwrapper error403">
        	<div class="errorcontent">
                <h1>错误</h1>
                <h3>服务发生未知错误！ </h3>
                <p>更多查看...</p> <br />
                <button class="stdbtn btn_black" onclick="history.back()">返回</button> &nbsp;
            </div><!--errorcontent-->
        </div><!--errorwrapper-->
    </div>
</div><!--bodywrapper-->

</body>
</html>
