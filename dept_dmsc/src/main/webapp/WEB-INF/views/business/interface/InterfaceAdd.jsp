<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>查询接口新增</title>
	<%@ include file="../../common/pubLib.html" %>
	<script type="text/javascript">
        jQuery(document).ready(function(){

		});
        function fnSaveInfo(){
            //验证信息是否正常
            MsgBox.Confirm("温馨提示","是否确认保存信息!",addRole);

        };
        function addRole() {
            var param = fngetFormJson(jQuery("#form1"));
            //提交信息
            jQuery.ajax({
                type: "post",
                dataType: "json",
                data: param,
                url: "<%=basePath%>commonQuery/save.do",
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
		<div class="contenttitle2">
			<h3>接口信息新增</h3>
		</div>
		<form  id="form1" class="stdform " method="post">
			<p>
				<label>服务编号：</label>
				<span class="field"><input type="text" name="tradeserviceid" id="tradeserviceid" class="longinput"  value="${config.tradeserviceid}"/></span>
			</p>
			<p>
				<label>服务类型：</label>
				<span class="field">
					<select name="tradeservicetype" id="tradeservicetype">
						<option value="query">查询接口</option>
						<option value="others">服务接口</option>
						<option value="others">其他接口</option>
					</select>
				</span>
			</p>
			<p>
				<label>服务名称：</label>
				<span class="field"><input type="text" name="tradeservicename" id="tradeservicename" class="longinput"  value="${config.tradeservicename}"/></span>
			</p>
			<p>
				<label>调用方式：</label>
				<span class="field"><input type="text" name="trademethod" id="trademethod" class="longinput"  value="${config.trademethod}"/></span>
			</p>
			<p>
				<label>操作类型：</label>
				<span class="field"><input type="text" name="opertion" id="opertion" class="longinput"  value="${config.opertion}"/></span>
			</p>

			<p>
				<label>服务提供者id：</label>
				<span class="field">
					<select name="serviceconsumerid" id="serviceconsumerid"  >
						<option value="1">管理员</option>
						<option value="2">普通用户</option>
					</select>
				</span>
			</p>
			<p>
				<label>数据源：</label>
				<span class="field">
					<select name="datasourceid" id="datasourceid"  >
						<option value="dataSource1">dataSource1</option>
						<option value="dataSource2">dataSource2</option>
					</select>
				</span>
			</p>

			<p>
				<label>执行语句或地址：</label>
				<span class="field"><textarea cols="80" rows="3" name="sqltext" id="sqltext" class="longinput" ></textarea></span>
			</p>

			<p>
				<label>回调语句或地址：</label>
				<span class="field"><textarea cols="80" rows="3" name="callbackid" id="callbackid" class="longinput" ></textarea></span>
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
