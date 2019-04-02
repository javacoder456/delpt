<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>接口查询测试</title>
	<%@ include file="../../common/pubLib.html" %>
	<script type="text/javascript">
        jQuery(document).ready(function(){
        });

	</script>
</head>

<body id="mainBody">
<div class="centercontent" style="margin-left: 120px;">
	<div id="default" class="subcontent">
		<form id="form1" class="stdform" action="<%=basePath%>commonQuery/queryForPage.action" method="post">
			<p>
				<label>服务ID:</label>
				<span class="field"><input type="text" name="tradeid" id="tradeid" class="longinput" /></span>
			</p>
			<p>
				<label>服务类型:</label>
				<span class="field"><input type="text" name="tradetype" id="tradetype" class="longinput" value="query"/></span>
			</p>
			<span class="field"><input type="text" name="pageNo" id="pageNo" class="longinput" value="1"/></span>
			<span class="field"><input type="text" name="pageSize" id="pageSize" class="longinput" value="10"/></span>
			<p id="stdformbutton" class="stdformbutton">
				<input  class="submit radius" type="submit" />
			</p>
		</form>
	</div>
</div>
<div id="pageOverlaymain"></div>
</body>
</html>