<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>资源权限设置</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <script type="text/javascript">
          function fnSaveInfo(){
              //验证信息是否正常
              MsgBox.Confirm("温馨提示","是否确认保存信息!",editAuth);
          };
          function editAuth(){
              var param = fngetFormJson(jQuery("#authform")) ;
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: param,
                  url: "<%=basePath%>sysSetUp/edit.do",
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
		<form  id="authform" class="stdform " method="post">
			<p>
				<label>角色id</label>
				<span class="field"><input type="text" name="roleid" id="roleid" class="longinput"  value="${auth.roleid}"/></span>
			</p>
			<p>
				<label>资源id</label>
				<span class="field"><input type="text" name="authorityid" id="authorityid" class="longinput"  value="${auth.authorityid}"/></span>
			</p>
			<p>
				<label>资源名称</label>
				<span class="field"><input type="text" name="authorityname" id="authorityname" class="longinput"  value="${auth.authorityname}"/></span>
			</p>
			<p>
				<label>父节id</label>
				<span class="field"><input type="text" name="fatherid" id="fatherid" class="longinput"  value="${auth.fatherid}"/></span>
			</p>
			<p>
				<label>菜单路径</label>
				<span class="field"><input type="text" name="url" id="url" class="longinput"  value="${auth.url}"/></span>
			</p>
			<p>
				<label>图&nbsp; &nbsp;片</label>
				<span class="field">
					<input type="text" name="icon" id="icon" class="longinput"  value="${auth.icon}"/>
				</span>
			</p>

			<p>
				<label>几级节点</label>
				<span class="field">
					<input type="text" name="children" id="children" class="longinput"  value="${auth.children}" />


				</span>
			</p>

			<p >
				<label>修改时间</label>
				<span class="field"><input type="text" name="modiftytime" id="modiftytime" class="longinput"  value="${auth.modiftytime}"/></span>
			</p>

			<p>
				<label>是否可用</label>
				<span class="field">
					<c:if test="${auth.enable}== 1">可用</c:if>
                    <c:if test="${auth.enable}== 0">不可用</c:if>
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