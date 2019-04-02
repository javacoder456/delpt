<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>资源权限设置</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <script type="text/javascript">
          function fnSaveInfo(){
              //验证信息是否正常
              MsgBox.Confirm("温馨提示","是否确认保存信息!",addAuth);

          };
          function addAuth(){
              var param = fngetFormJson(jQuery("#authform")) ;
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: param,
                  url: "<%=basePath%>sysSetUp/add.do",
                  success: function (result) {
                      window.location.reload();
                  },
              });
          }
	  </script>
	
  </head>

<body id="mainBody">
	<div id="contentwrapper" class="contentwrapper">
	<div id="basicform" class="subcontent">
		<form  id="authform" class="stdform " method="post">
			<c:if test="${auth.children !=2}">
				<p>
					<label>资源名称</label>
					<span class="field"><input type="text" name="authorityname" id="authorityname" class="longinput"  value=""/></span>
				</p>
				<p>
					<label>父节id</label>
					<span class="field"><input type="text" name="fatherid" id="fatherid" class="longinput"  value="${auth.authorityid}"/></span>
				</p>
				<p id="p_children">
					<label>节点层级</label>
					<span class="field">
						<select name="children" id="children"  >
							<option value="0">根节点</option>
							<option value="1">一级节点</option>
							<option value="2">二级节点</option>
						</select>
					</span>
				</p>
				<p>
					<label>角色id</label>
					<span class="field">
						<select name="roleid" id="roleid">
							<option value="1">管理员</option>
							<option value="2">普通用户</option>
						</select>
					</span>
				</p>

				<p>
					<label>菜单路径</label>
					<span class="field"><input type="text" name="url" id="url" class="longinput"  value=""/></span>
				</p>
				<p>
					<label>图&nbsp; &nbsp;片</label>
					<span class="field">
						<select name="icon" id="icon">
							<option value="elements" >elements</option>
							<option value="editor" >editor</option>
							<option value="widgets" >widgets</option>
							<option value="support" >support</option>
							<option value="gallery" >gallery</option>
							<option value="typo" >typo</option>
							<option value="drafts" >drafts</option>
							<option value="trash" >trash</option>
							<option value="icon-flatscreen" >icon-flatscreen</option>
							<option value="icon-pencil" >icon-pencil</option>
							<option value="icon-speech" >icon-speech</option>
							<option value="icon-message" >icon-message</option>
							<option value="icon-chart" >icon-chart</option>
						</select>
					</span>
				</p>
				<p>
					<label>是否可用</label>
					<span class="field">
						<select name="enable" id="enable">
							<option value="1" >是</option>
							<option value="0" >否</option>
						</select>
					</span>
				</p>

				<p id="stdformbutton" class="stdformbutton">
					<button  class="submit radius" type="button" onclick="fnSaveInfo();">保&nbsp; &nbsp;存</button>
				</p>
			</c:if>
			<c:if test="${auth.children ==2}">
				<div class="errorwrapper error403">
					<div class="errorcontent">
						<h3>系统提示</h3>
						<span>该节点为二级子节点无法新增</span><br />
					</div><!--errorcontent-->
				</div>
			</c:if>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>