<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
	  <base href="<%=basePath%>" />
	  <title>用户信息新增</title>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <%@ include file="../../common/pubLib.html" %>
	  <script type="text/javascript">
          function fnSaveInfo(){
              //验证信息是否正常
              MsgBox.Confirm("温馨提示","是否确认保存信息!",addUser);

          };
          function addUser(){
              var param = fngetFormJson(jQuery("#userform")) ;
              //提交信息
              jQuery.ajax({
                  type: "post",
                  dataType: "json",
                  data: param,
                  url: "<%=basePath%>userManger/add.do",
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
		<form  id="userform" class="stdform " method="post">
			<p>
				<label>姓&nbsp; &nbsp;名</label>
				<span class="field"><input type="text" name="name" id="name" class="longinput"  value=""/></span>
			</p>
			<p>
				<label>密&nbsp; &nbsp;码</label>
				<span class="field"><input type="text" name="password" id="password" class="longinput"  value=""/></span>
			</p>
			<p>
				<label>性&nbsp; &nbsp;别</label>
				<span class="field" >
                        	<input type="radio"  name="sex" checked="checked"  value='1'/> 男 &nbsp; &nbsp;
                        	<input type="radio"  name="sex" value='2'/> 女 &nbsp; &nbsp;
                        </span>
			</p>
			<p>
				<label>年&nbsp; &nbsp;龄</label>
				<span class="field"><input type="text" name="age" id="age" class="longinput"  value=""/></span>
			</p>

			<p>
				<label>邮&nbsp; &nbsp;箱</label>
				<span class="field"><input type="text" name="email" id="email" class="longinput" value="" /></span>
			</p>
			<p>
				<label>手&nbsp; &nbsp;机</label>
				<span class="field"><input type="text" name="phonenumber" id="phonenumber" class="longinput" value=""/></span>
			</p>
			<p>
				<label>联系地址 </label>
				<span class="field"><textarea cols="80" rows="3" name="address" id="address" class="longinput" ></textarea></span>
			</p>
			<p>
				<label>默认角色 </label>
				<span class="field">
					<select name="roleid" id="roleid" >
						<c:forEach items="${roles}" var="role">
							<c:if test="${role.roleid!=null}">
								<option value="${role.roleid}">${role.rolename}</option>
							</c:if >
						</c:forEach>
					</select>
				</span>
			</p>
			<p>
				<label>是否可用</label>
				<span class="field">
					<select name="enable" id="enable" >
						<option value="1">可用</option>
						<option value="0">不可用</option>
					</select>
                </span>
			</p>

			<p  id="stdformbutton" class="stdformbutton">
				<button  class="submit radius" type="button" onclick="fnSaveInfo();">保&nbsp; &nbsp;存</button>
			</p>
         </form><br/>
	  </div><!--subcontent-->
      </div><!--contentwrapper-->
	<div id="pageOverlaymain"></div>
</body>
</html>