<%@ page pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <base href="<%=basePath%>" />
    <title>文件上传测试</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%@ include file="../common/pubLib.html" %>
    <link rel="stylesheet" href="css/upload/webuploader.css" type="text/css" />
    <link rel="stylesheet" href="css/upload/style.css" type="text/css" />
    <script type="text/javascript" src="js/upload/upload.js"></script>
    <script type="text/javascript" src="js/upload/webuploader.js"></script>
    <script type="text/javascript">
        function downloadFile() {


        };
    </script>
</head>
  
<body >
	<div id="contentwrapper" class="contentwrapper">
	<div id="basicform" class="subcontent">
  		 <!-- 多个测试 -->
            <!--头部，相册选择和格式选择-->
            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>将文件拖到这里，单个文件大小不超过2M，单次上传文件总大小不超过20M</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div>
                    <div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
                <div class="btns">
                    <div id="download">
                        <input id="fileDownload" /></div></br>
                        <button class="downloadBtn" id="downloadBtn" onclick="downloadFile();">开始下载</button>
                </div>
            </div>
    </div><br/>

	</div><!--subcontent-->
    </div><!--contentwrapper-->
</body>
</html>