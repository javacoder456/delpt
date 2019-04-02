<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/custom/widgets.js"></script>
    <script type="text/javascript">
        function showMore(id) {
            addTab("nav_notice","消息列表","msgManger/getNotice.do?senderid="+id,true);
        }
    </script>
</head>
<div class="widgetbox">
    <div class="title">
        <h3>我的消息列表</h3>
    </div>
    <div class="widgetcontent userlistwidget nopadding">
        <ul>
            <c:if test="${empty notices}">
                <div class="info">
                    <a>&nbsp;&nbsp;无记录</a><br />
                </div>
            </c:if>
            <c:forEach items="${notices}" var="notice">
                <li>
                    <div class="avatar" style="cursor:pointer;" onclick="showMore(${notice.senderid});">
                        <img src="images/thumbs/avatar1.png" />
                    </div>
                    <div class="info">
                        <a>${notice.sendername}</a><br />
                        <span>${notice.titile}<br/>${notice.sendtime}<br/></span>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <a class="more" style="cursor:pointer;" onclick="showMore('');">展示更多</a>
    </div><!--widgetcontent-->
</div><!--widgetbox-->
</HTML>