<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>角色权限设置</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <script type="text/javascript">
          function fnAddRole(id) {
              addTab("nav_"+id,"角色新增","roleManger/addRole.do",true);
          }
          function fnEditRole(id) {
              addTab("nav_"+id,"角色修改","roleManger/editRole.do?roleid="+id,true);
          }
          function fnDeleteRole(id) {
			  //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: {"roleid":id},
                  url: "<%=basePath%>roleManger/delete.do",
                  success: function (result) {
                      window.location.reload();
                  }
              });
          }

	  </script>
  </head>

<body id="mainBody">
	<div id="contentwrapper" class="contentwrapper">
	<div id="basicform" class="subcontent">
		<form  id="authform" class="stdform " method="post">
			<div class="contenttitle2">
				<h3>系统角色列表</h3>
			</div><!--contenttitle-->
			<input type="reset" class="reset radius2" value="重置"  onclick="javascript:window.location.reload();"/>

			<table  id="table2" cellpadding="0" cellspacing="0" border="0" style="font-size: 14px;"class="stdtable stdtablecb">
				<thead>
					<tr>
						<th class="head0">角色id</th>
						<th class="head1">角色名称</th>
						<th class="head0">描述</th>
						<th class="head1">修改时间</th>
						<th class="head0">管理员</th>
						<th class="head1">操作</th>
					</tr>
				</thead>
				<c:if test="${empty data}">
					<tr>
						<td align="center"><a>无记录!</a></td>
					</tr>
				</c:if>
				<tbody>
				<c:forEach items="${data}" var="res">
					<tr>
						<td>${res.roleid}</td>
						<td>${res.rolename}</td>
						<td>${res.description}</td>
						<td class="aCenter">${res.modiftytime}</td>
						<td class="aCenter">
							<c:if test="${res.isadmin == 1}">是</c:if>
							<c:if test="${res.isadmin != 1}">否</c:if>
						</td>

						<td class="aCenter">
							<a href="javascript:void(0);" onclick="fnAddRole(${res.roleid})">新增</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="fnEditRole(${res.roleid});">修改</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="fnDeleteRole(${res.roleid})">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table><br/>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>