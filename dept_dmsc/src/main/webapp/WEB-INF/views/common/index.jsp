<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
    <base href="<%=basePath%>" >
    <title>主页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%@ include file="pubLib.html" %>
    <script type="text/javascript" src="js/custom/login.js"></script>
    <script type="text/javascript" src="js/custom/createTabs.js"></script>
    <script type="text/javascript" src="js/socket/sockjs.min.js"></script>
    <script type="text/javascript" src="js/socket/msg.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function(){
            getMainMenu();
            //加载主页信息
            addTab("div_mainPage","欢迎主页","sysSetUp/home.do");
        });

        function editUser(id) {
            addTab('nav_editUser','账号设置','userManger/editUser.do?userid='+id,true);
        }

    </script>
    <style type="text/css">
        #myTab li{ cursor:pointer;}
    </style>
</head>
<body id="mainBody" class="withvernav">
<div class="bodywrapper" >
    <div class="topheader">
        <div class="left">
            <h1 id="logo_username" class="logo" >${user.name}</h1>
            <span id="topTitile" class="slogan"></span>
            <br clear="all" />

        </div><!--left-->

        <div class="right">
            <div id="notification" class="notification">
                <a id="count" class="count" href="msgManger/list.do"><span>消息【${count}】</span></a>
            </div>
            <div class="userinfo">
                <img src="images/thumbs/avatar.png"  />
                <span id="userinfo_username">${user.name}</span>
            </div><!--userinfo-->

            <div class="userinfodrop">
                <div class="avatar">
                    <a><img src="images/thumbs/avatarbig.png"/></a>
                    <div class="changetheme">
                        切换主题: <br />
                        <a class="default"></a>
                        <a class="blueline"></a>
                        <a class="greenline"></a>
                        <a class="contrast"></a>
                        <a class="custombg"></a>
                    </div>
                </div><!--avatar-->
                <div class="userdata">
                    <ul>
                        <li><a><span>${user.name}|${user.phonenumber}</span></a></li>
                        <li><a href="javascript:void(0)" onclick="editUser('${user.userid}');">账号设置</a></li>
                        <li><a href="error/error.do">帮助</a></li>
                        <li><a href="login/logout.do">退出</a></li>
                    </ul>
                </div><!--userdata-->
            </div><!--userinfodrop-->
        </div><!--right-->
    </div><!--topheader-->

    <div class="header">
        <ul class="headermenu" id="headermenu"></ul> <!-- 顶部菜单 -->
        <div class="headerwidget">
            <div class="earnings">
                <div class="one_half">
                    <h3 id="timedata_nowtime">${nowtime}</h3>
                </div><!--one_half-->
                <div class="one_half last alignright">
                    <h3 id="timedata_week">${week}</h3>
                </div><!--one_half last-->
            </div><!--earnings-->
        </div><!--headerwidget-->

    </div><!--header-->

    <div class="vernav2 iconmenu">
        <ul id="submenu"></ul><!--子菜单  -->
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->

    <div id="centercontent" class="centercontent">
        <div class="pageheader" id="tabs" >
            <ul class="nav hornav" id="myTab"></ul>  <!-- 页面标签列表 -->
        </div>
        <!-- 页面内容列表，和页面标签列表对应 -->
        <div class="tab-content"></div> <!-- 动态生成 -->
        <br clear="all" />
    </div><!-- centercontent -->
</div><!--bodywrapper-->
<div id="pageOverlaymain"></div>
</body>
</html>
