<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title>登录</title>
	<%@ include file="loginLib.html" %>
	<script type="text/javascript">
        jQuery(document).ready(function(){
            var error = jQuery("#errormsg").html();
            if(error!=""){
                jQuery('#warn').css("display","block");
                setTimeout(function (){jQuery('#warn').css('display','none');},3000);
            }else {
                jQuery('#warn').css('display','none');
            }
        });

	</script>

</head>
	<body>
		<div class="wrap login_wrap">
			<div class="content">
				<div class="logo"></div>
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">登&nbsp;&nbsp;录</div>
						<form id="login" action="login/valid.do" method="post" >
							
							<div class="form_text_ipt">
								<input name="username" type="text" placeholder="账号">
							</div>
							<div id="warn" class="ececk_warning"><span id="errormsg">${error}</span></div>
							<div class="form_text_ipt">
								<input name="password" type="password" placeholder="密码">
							</div>
							<div class="form_check_ipt">
								<div class="left check_left">
									<label><input name="" type="checkbox"> 下次自动登录</label>
								</div>
								<div class="right check_right">
									<a href="#">忘记密码</a>
								</div>
							</div>
							<div class="form_btn">
								<button type="submit">登录</button>
							</div>
							<div class="form_reg_btn">
								<span>还没有帐号？</span><a href="login/register.do">马上注册</a>
							</div>
						</form>
						<div class="other_login">
							<div class="left other_left">
								<span></span>
							</div>
							<div class="right other_right"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
