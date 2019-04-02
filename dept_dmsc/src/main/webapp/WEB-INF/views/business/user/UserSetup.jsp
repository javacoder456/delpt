<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>用户权限设置</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <%@ include file="../../common/pageLib.html" %>
	  <script type="text/javascript">
          jQuery(document).ready(function(){
              var column = ["userid","add","edit","delete"];
              //initTablePageTool("userManger/list.do",true,false,true,column);
              initTablePageTool("commonQuery/queryForPage.action?tradeid=Q003",true,false,true,column);
          });
          function fnAdd(id) {
              addTab("nav_"+id,"用户信息新增","userManger/addUser.do",true);
          }
          function fnEdit(id) {
              addTab("nav_"+id,"用户信息修改","userManger/editUser.do?userid="+id,true);
          }
          function fnDelete(id) {
              MsgBox.Confirm("温馨提示","是否确认删除信息!",deleteUser,id);
          }
          function deleteUser(id){
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: {"userid":id},
                  url: "<%=basePath%>userManger/delete.do",
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
				<h3>用户信息列表</h3>
			</div><!--contenttitle-->

			<input type="reset" class="reset radius2" value="重置" onclick="javascript:window.location.reload(); "/>
			<table cellpadding="0" cellspacing="0" border="0" style="font-size: 14px;"
				   id="table2" class="tablePageTool stdtable stdtablecb">
				<thead>
					<tr>
						<th class="head0"></th>
						<th name="roleid" class="head1">角色id</th>
						<th name="name" class="head0">用户名</th>
						<th name="age" class="head1">年龄</th>
						<th name="address" class="head0">联系信息</th>
						<th name="faultrole" class="head1">默认权限</th>
						<th name="lastmodifytime" class="head0">创建时间</th>
						<th name="enable" class="head1">是否可用</th>
						<th class="head0"></th>
					</tr>
				</thead>
				<c:if test="${empty users}">
					<tr>
						<td colspan="11" align="center"><a>无记录!</a></td>
					</tr>
				</c:if>
				<tbody>
				<%--<c:forEach items="${users}" var="user">
					<tr>
						<td align="center"><input type="checkbox" /></td>
						<td>${user.roleid}</td>
						<td>${user.name}</td>
						<td>${user.age}</td>
						<td class="center">${user.address}</td>
						<td class="center">${user.faultrole}</td>
						<td class="center">${user.lastmodifytime}</td>
						<td class="center">
							<c:if test="${user.enable == 1}">可用</c:if>
							<c:if test="${user.enable == 0}">不可用</c:if>
						</td>
						<td class="aCenter" >
							<a href="javascript:void(0);" onclick="fnAddUser(${user.userid})">新增</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="fnEditUser(${user.userid});">修改</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="fnDeleteUser(${user.userid})">删除</a>
						</td>
					</tr>
				</c:forEach>--%>
				</tbody>
			</table><br/>
			<br/>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>