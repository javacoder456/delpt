<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta charset="utf-8">
	<title>注册界面</title>
	<%@ include file="loginLib.html" %>
</head>
<body>
	<div class="wrap login_wrap">
		<div class="content">
			<div class="logo"></div>
			<div class="login_box">
				<div class="login_form">
					<div class="login_title">注&nbsp;&nbsp;册</div>
					<form action="/userManger/add.do" method="post">

						<div class="form_text_ipt">
							<input name="username" type="text" placeholder="姓名">
						</div>
						<div class="form_text_ipt">
							<input name="password" type="password" placeholder="密码">
						</div>
						<div class="form_text_ipt">
							<input name="pwd" type="password" placeholder="重复密码">
						</div>
						<%--<div class="form_text_ipt">
							<input name="code" type="text" placeholder="验证码">
						</div>--%>
						<div class="ececk_warning"><span>错误提示</span></div>

						<div class="form_btn">
							<button type="submit">注册</button>
						</div>
						<div class="form_reg_btn">
							<span>已有帐号？</span><a href="login/login.do">马上登录</a>
						</div>
					</form>
					<div class="other_login"></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
