<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>资源权限设置</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <%@ include file="../../common/pageLib.html" %>
	  <script type="text/javascript">
          jQuery(document).ready(function(){
              var column = ["authorityid","add","edit","delete"];
              initTablePageTool("commonQuery/queryForPage.action?tradeid=Q005",true,false,true,column);
          });
          function fnAdd(id) {

              fnGetBusinessMenu("/sysSetUp/addAuth.do?fatherid="+id,id,"资源新增");
          }
          function fnEdit(id) {

              fnGetBusinessMenu("/sysSetUp/setup.do?authorityid="+id,id,"资源修改");
          }
          function fnDelete(id) {
              MsgBox.Confirm("温馨提示","是否确认删除信息!",deleteAuth,id);
          }
          function deleteAuth(id){
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: {"authorityid":id},
                  url: "<%=basePath%>sysSetUp/delete.do",
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
				<h3>资源路径列表</h3>
			</div><!--contenttitle-->

				<input type="reset" class="reset radius2" value="重置" onclick="javascript:window.location.reload(); "/>

			<table id="table2"  cellpadding="0" cellspacing="0" border="0" style="font-size: 14px;"
				   class="stdtable stdtablecb tablePageTool">
				<thead>
					<tr>
						<th class="head0"></th>
						<th name= "authorityid" class="head0">资源id</th>
						<th name= "authorityname" class="head1">资源名称</th>
						<th name= "fatherid" class="head0">父节点id</th>
						<th name= "url" class="head1">资源路径</th>
						<th name= "icon" class="head0">图片</th>
						<th name= "createtime" class="head1">创建时间</th>
						<th name= "enable" class="head0">是否可用</th>
						<th name= "children" class="head1">节点级别</th>
						<th class="head0"></th>
					</tr>
				</thead>
				<c:if test="${empty data}">
					<tr>
						<td colspan="11" align="center"><a>无记录!</a></td>
					</tr>
				</c:if>
				<tbody></tbody>
			</table>
			
			<br/><br/>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>