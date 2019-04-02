<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>测试分页插件</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../common/pubLib.html" %>
      <link rel="stylesheet" href="css/dataTables.bootstrap.css" type="text/css" />
	  <!--引入JavaScript-->
	  <script type="text/javascript" language="javascript" src="js/plugins/jquery.dataTables.min.js"></script>
	  <script type="text/javascript" language="javascript" src="js/tables/tablePageTool.init.js"></script>
      <script type="text/javascript">
          jQuery(document).ready(function(){
              var column = ["add","edit"];
              initTablePageTool("test/getData.do",true,false,true,column);
          });

      </script>
  </head>

<body id="mainBody">
	<div id="contentwrapper" class="contentwrapper">
	<div id="basicform" class="subcontent">
		<form  id="authform" class="stdform " method="post">
			<div class="contenttitle2">
				<h3>用户信息列表</h3>
			</div><!--contenttitle-->
			<input type="reset" class="reset radius2" value="重置" onclick="javascript:window.location.reload(); "/>
			<table id="table2" class="stdtable stdtablecb tablePageTool" style="font-size: 14px;" cellpadding="0" cellspacing="0" border="0" >
				<thead> <!--表头  -->
					<tr>
						<th class="head0"></th>
						<th name ="roleid" class="head1">角色id</th>
						<th name ="name" class="head0">用户名</th>
						<th name ="age" class="head1">年龄</th>
                        <th name ="address" class="head0">联系信息</th>
                        <th name ="faultrole" class="head1">默认权限</th>
                        <th name = "lastmodifytime" class="head0">创建时间</th>
                        <th name = "enable" class="head1">是否可用</th>
						<th class="head0"></th>
					</tr>
				</thead>
				<tbody>
					<!-- 初始化绘制 -->
				</tbody>
			</table><br/>
			<br/>
         </form>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>