<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>角色权限新增</title>
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
                url: "roleManger/getMenus.do",
                success: function (data) {
                    jQuery.fn.zTree.init(jQuery("#zTree"), setting, addMenus(data.menu));
                }
            });

		});
        function fnSaveInfo(){
            //验证信息是否正常
            MsgBox.Confirm("温馨提示","是否确认保存信息!",addRole);

        };
        function addRole() {
            var param = fngetFormJson(jQuery("#roleForm"));
            param['auths'] = getCheckNodeIds();
            //提交信息
            jQuery.ajax({
                type: "post",
                dataType: "json",
                data: param,
                url: "<%=basePath%>roleManger/add.do",
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
			<form id="roleForm" class="stdform">
				<div id="wizard" class="wizard">
					<br clear="all" /><br />
					<div id="step1" >
						<h4>角色信息:</h4>
						<p>
							<label>角色名称:</label>
							<span class="field"><input type="text" name="rolename" id="rolename" class="longinput" /></span>
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
