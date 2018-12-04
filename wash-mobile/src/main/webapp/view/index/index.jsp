<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <script type="text/javascript">
    var baseUrl = "${pageContext.request.contextPath}";
    </script> 
    <!--css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css"/>
	<link rel="stylesheet" href="http://cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <!--js -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/unslider.min.js" type="text/javascript"></script>
	<script src="http://cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body>
    <div class="carousel fl banner">
        <ul class="ul-carousel">
            <li>
                <img src="${pageContext.request.contextPath}/static/images/lb-01.jpg">
            </li>
            <li><img src="${pageContext.request.contextPath}/static/images/lb-02.jpg">
            </li>
            <li><img src="${pageContext.request.contextPath}/static/images/lb-03.jpg">
            </li>
        </ul>
    </div>
    <!-- carousel -->
    <div class="wrap" style="padding-bottom: 4rem;">
        <div class="item">
        	<c:forEach items="${commodityCategoryList}" var="commodityCategory">
	        	<div class="title">
	                <h1>${commodityCategory.name}</h1>
	            </div>
	            
	        	<div class="line">
	            <c:forEach items="${commodityMap[commodityCategory.id]}" var="commodity" >
	                <div class="box">
	                    <div class="box-img">
	                        <img src="${commodity.img}">
	                    </div>
	                    <h1>${commodity.name}</h1>
	                    <h2>￥${commodity.price}<i><img src="${pageContext.request.contextPath}/static/images/add.png" onclick="addCommodity(${commodity.id})"></i></h2>
	                </div>
        		</c:forEach>
	            </div>
	            
        	</c:forEach>
            
        </div>
        <!-- item -->
        
    </div>
    <!-- wrap -->
    <div class="menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/index.htm">
                <img src="${pageContext.request.contextPath}/static/images/index-02.png">
                    <p class="active">首页</p>
                </a>
            </li>
            
            <li>
                <a href="${pageContext.request.contextPath}/shopCar/list.htm"><p>
                <img src="${pageContext.request.contextPath}/static/images/car-01.png">
                <i id="shopCarNumber">${shopCarCount}</i></p>
                    <p>购物车</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userIndex.htm">
                <img src="${pageContext.request.contextPath}/static/images/me-01.png">
                    <p>我的</p>
                </a>
            </li>
        </ul>
    </div>
    <!-- menu -->
    <script type="text/javascript"> 
    
    $(function() {
        $('.banner').unslider({
            keys: true,
            dots: true
        });
    });
    
    function addCommodity(id){
    	//提交表单
		$.ajax({
			url : baseUrl + "/shopCar/ajax/addCommodity.htm",
			type : "POST",
			data : "commodityId=" + id,
			success : function(data) {
				data = eval('(' + data + ')');
				if (data.success == "true") {
					$.alert("加入购物车成功","");
					$("#shopCarNumber").html(data.shopCarCount);
				} else if(data.success == "false"){
					$.alert("加入购物车失败","");
				}
			}
		})
    }
    </script>
</body>

</html>
