<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>个人主页</title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/personal-index.css')}"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body>
    <div class="personal-information">
        <div class="person-pic">
            <div class="personal-information">
                <div class="personal-picture">
                    <img src="${wx_user.wxImg}"">
                </div>
                <div class="personal-name">
                    <h1>${wx_user.wxName}</h1>
                    <%-- <p>
                    <c:choose>
					    <c:when test="${not empty wx_user.sex && wx_user.sex == 1}">
					     男
					    </c:when>
					    <c:when test="${not empty wx_user.sex && wx_user.sex == 2}">
					     女
					    </c:when>
					    <c:otherwise>
					       未知
					    </c:otherwise>
					</c:choose>
                    </p> --%>
                </div>
            </div>
        </div>
    </div><!-- personal-information -->
    <div class="common-btn" >
    	<input type="button" value="订单查询" onclick="window.location='${pageContext.request.contextPath}/order/list.htm'" ></input>
    </div>
    <div class="common-btn" >
    	<input type="button" value="地址管理" onclick="window.location='${pageContext.request.contextPath}/address/list.htm'" />
    </div>
    <div class="menu">
        <ul>
           <li>
                <a href="${pageContext.request.contextPath}/index.htm">
                <img src="${mytag:getStaticContext('images/index-01.png')}">
                    <p>首页</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/shopCar/list.htm"><p>
                <img src="${mytag:getStaticContext('images/car-01.png')}">
                <i id="shopCarNumber">${shopCarCount}</i></p>
                    <p class="active">购物车</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userIndex.htm">
                <img src="${mytag:getStaticContext('images/me-02.png')}">
                    <p>我的</p>
                </a>
            </li>
        </ul>
    </div>
    <!-- menu -->
</body>

</html>
