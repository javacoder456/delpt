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
              var column = ["tradeserviceid","add","edit","delete"];
              initTablePageTool("commonQuery/queryForPage.action?tradeid=Q004",true,false,true,column);
          });
          function fnAdd(id) {
              addTab("nav_"+id,"接口信息新增","commonQuery/addInterface.do",true);
          }
          function fnEdit(id) {
              addTab("nav_"+id,"接口信息修改","commonQuery/editInterface.do?tradeid="+id,true);
          }
          function fnDelete(id) {
              MsgBox.Confirm("温馨提示","是否确认删除信息!",deleteUser,id);
          }
          function deleteUser(id){
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: {"tradeid":id},
                  url: "<%=basePath%>commonQuery/delete.do",
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
				<h3>新增接口列表</h3>
			</div><!--contenttitle-->
			<input type="reset" class="reset radius2" value="重置" onclick="javascript:window.location.reload(); "/>
			<table cellpadding="0" cellspacing="0" border="0" style="font-size: 14px;"
				   id="table2" class="tablePageTool stdtable stdtablecb">
				<thead>
					<tr>
						<th class="head0"></th>
						<th name="tradeserviceid" class="head1">服务编号</th>
						<th name="tradeservicetype" class="head0">服务类型</th>
						<th name="tradeservicename" class="head1">服务名称</th>
						<th name="serviceconsumerid" class="head0">服务提供者</th>
						<th name="serviceconsumername" class="head1">服务提供者名称</th>
						<th name="datasourceid" class="head0">数据源</th>
						<th name="createtime" class="head1">创建时间</th>
						<th class="head0"></th>
					</tr>
				</thead>
				<c:if test="${empty users}">
					<tr>
						<td colspan="11" align="center"><a>无记录!</a></td>
					</tr>
				</c:if>
				<tbody></tbody>
			</table><br/>
			<br/>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>