<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>角色权限编辑</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <link rel="stylesheet" href="css/bootstrapStyle.css" type="text/css">
	  <script type="text/javascript" src="js/jquery.ztree.core.js"></script>
	  <script type="text/javascript" src="js/ztree/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="js/ztree/addMenu.js"></script>
	  <script type="text/javascript">
          jQuery(document).ready(function(){
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: {},
                  url: "roleManger/getMenus.do",
                  success: function (data) {
                      var zTreeMenu = jQuery.fn.zTree.init(jQuery("#zTree"), setting, addMenus(data.menu));
                      if("${auths eq null}" == "false"){
                          var checkNodes = ${auths};
                          for(var i = 0; i < checkNodes.length; i++){
                              var node = zTreeMenu.getNodeByParam("id",checkNodes[i]);
                              zTreeMenu.selectNode(node);
                              zTreeMenu.checkNode(node, true, false);
                          }
					  }
                  }
              });
		  });
          function fnSaveInfo(){
              //验证信息是否正常
              MsgBox.Confirm("温馨提示","是否确认保存信息!",editRole);
          };
          function editRole(){
              var param = fngetFormJson(jQuery("#roleform")) ;
              param['auths'] = getCheckNodeIds();
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: param,
                  url: "<%=basePath%>roleManger/edit.do",
                  success: function (result) {
                      window.location.reload();
                  },
              });
          }
	  </script>
	
  </head>

<body  id="mainBody">
	<div id="contentwrapper" class="contentwrapper">
	<div id="basicform" class="subcontent">
		<form  id="roleform" class="stdform " method="post">
			<p  style="display: none">
				<label>角色id</label>
				<span class="field"><input type="text" name="roleid" id="roleid" class="longinput"  value="${role.roleid}"/></span>
			</p>

			<p>
				<label>角色名称</label>
				<span class="field"><input type="text" name="rolename" id="rolename" class="longinput"  value="${role.rolename}"/></span>
			</p>
			<p>
				<label>描述</label>
				<span class="field"><input type="text" name="description" id="description" class="longinput"  value="${role.description}"/></span>
			</p>
			<div>
				<label>资源菜单</label>
				<span class="field">
					<ul id="zTree" class="ztree"></ul>
				</span>
			</div>
			<p>
				<label>管理员</label>
				<span class="field">
					<select name="isadmin" id="isadmin">
						<c:if test="${role.isadmin ==1}">
							<option value="" >无</option>
							<option value="0" >否</option>
							<option value="1" >是</option>
						</c:if>
						<c:if test="${role.isadmin !=1}">
							<option value="" >无</option>
							<option value="0" >否</option>
						</c:if>
                   	</select>
				</span>
			</p>
			<p >
				<label>修改时间</label>
				<span class="field"><input type="text" name="modiftytime" id="modiftytime" class="longinput"  value="${role.modiftytime}"/></span>
			</p>

			<p>
				<label>是否可用</label>
				<span class="field">
					<select name="enable" id="enable">
						<option value="0" >不可用</option>
						<option value="1" >可用</option>
					</select>
				</span>
			</p>

			<p id="stdformbutton" class="stdformbutton">
				<button  class="submit radius" type="button" onclick="fnSaveInfo();">保&nbsp; &nbsp;存</button>
			</p>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>