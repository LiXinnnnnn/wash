<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/shipping-address.css')}">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body>

    <div class="title">
        <h1><img src="${mytag:getStaticContext('images/left.png')}" onclick="window.history.back()">选择收货地址</h1>
    </div>
<c:forEach items="${addressList}" var="address">
    <div class="select-line">
        <div class="select-line-infor">
            <h1><i>${address.name}</i> <span>${address.mobile}</span></h1>
            <h2>${address.address}
            <c:if test="${address.isDefault == 1}">
            <i>&nbsp;(默认地址)</i>
            </c:if>
            </h2>
        </div>
        <div class="select-line-input">
        <c:choose>
        	<c:when test="${address.id == addressId}">
        	<input type="radio" checked="checked" name="selectRadio" onclick="window.location='${url}&addressId=${address.id}'"></input>
        	</c:when>
        	<c:otherwise>
	        <input type="radio"  name="selectRadio"  onclick="window.location='${url}&addressId=${address.id}'"></input>
	        </c:otherwise>
        </c:choose>
        
        </div>
    </div>
</c:forEach>    
    <div class="add-address-btn">
        <input type="button" value="新增收货地址" onclick="window.location='${pageContext.request.contextPath}/address/add.htm'"></input>
    </div>
</body>

</html>
