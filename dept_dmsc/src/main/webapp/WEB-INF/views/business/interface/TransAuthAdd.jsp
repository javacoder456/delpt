<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>接口权限新增</title>
	<%@ include file="../../common/pubLib.html" %>
	<link rel="stylesheet" href="static/zTreeStyle/bootstrapStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="js/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="js/ztree/addMenu.js"></script>
	<script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery.ajax({
                type: "post",
                dataType: "json",
                data: {},
                url: "commonAuthTrans/queryAuth.do",
                success: function (data) {
                    var zTreeMenu = jQuery.fn.zTree.init(jQuery("#zTree"), setting, addAuths(data.configs));
                    console.log()
                }
            });

        });
        function fnSaveInfo(){
            //验证信息是否正常
            MsgBox.Confirm("温馨提示","是否确认保存信息!",addRole);

        };
        function addRole() {
            var param = fngetFormJson(jQuery("#form1"));
            param['configs'] = getCheckNodeIds();
            //提交信息
            jQuery.ajax({
                type: "post",
                dataType: "json",
                data: param,
                url: "<%=basePath%>commonAuthTrans/addAuthTrans.do",
                success: function (result) {
                    window.location.reload();
                },
            });
        }

	</script>
</head>

<body id="mainBody">
<div class="centercontent" style="margin-left: 120px;">
	<div id="default" class="subcontent">
		<form id="form1" class="stdform">
			<div id="wizard" class="wizard">
				<div id="step1" >
					<br clear="all" /><br />
					<h4>用户信息:</h4>
					<p>
						<label>用户ID:</label>
						<span class="field"><input type="text" name="userid" id="userid" class="longinput" /></span>
					</p>
					<p>
						<label>用户名:</label>
						<span class="field"><input type="text" name="username" id="username" class="longinput" /></span>
					</p>
					<p>
						<label>描述信息:</label>
						<span class="field"><input type="text" name="description" id="description" class="longinput" /></span>
					</p>
				</div>

				<div id="step2">
					<h4>资源信息:</h4>
					<div style="margin-left: 110px;">
						<ul id="zTree" class="ztree"></ul>
					</div>
				</div>
			</div>
			<p id="stdformbutton" class="stdformbutton">
				<button  class="submit radius" type="button" onclick="fnSaveInfo();">保&nbsp; &nbsp;存</button>
			</p>
		</form>
	</div>
</div>
<div id="pageOverlaymain"></div>
</body>
</html>