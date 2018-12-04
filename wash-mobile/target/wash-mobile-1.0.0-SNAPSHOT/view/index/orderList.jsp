<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/orderList.css')}">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body style="background-color: #f2f2f2;">
    <div class="title">
        <h1><a href="${pageContext.request.contextPath}/user/userIndex.htm">
        <img src="${mytag:getStaticContext('images/left.png')}"></a>订单查询</h1>
    </div>
    <div class="order-search">
        <img src="${mytag:getStaticContext('images/search.png')}">
        <input type="text" placeholder="搜索所有订单"></input>
    </div>
    <c:forEach items="${orderList}" var="order">
    <div class="query-box" style="margin-top: 10px">
        <div class="q-head">
        <c:choose>
        	<c:when test="${order.status == -1}">
	        	<p>订单取消</p>
	        </c:when>
	        <c:when test="${order.status == 1}">
	        	<p>等待买家付款</p>
	        </c:when>
	        <c:when test="${order.status == 2}">
	        	<p>付款成功</p>
	        </c:when>
	        <c:when test="${order.status == 3}">
	        	<p>商家确认订单</p>
	        </c:when>
	        <c:when test="${order.status == 4}">
	        	<p>洗衣中</p>
	        </c:when>
	        <c:when test="${order.status == 5}">
	        	<p>订单结束</p>
	        </c:when>
        </c:choose>
            
        </div>
        <div class="q-content">
        	<c:forEach items="${order.commodityList}" var="commodity">
            <div class="order-item">
                <div class="order-pic">
                    <img src="${commodity.img}">
                </div>
                <div class="order-infor">
                    <h2>${commodity.name}</h2>
                    <h2>单价:${commodity.price}</h2>
                    <h2>数量:${commodity.number}</h2>
                </div>
                <div class="order-price">
                    <p>小计:￥${commodity.number * commodity.price}</p>
                </div>
            </div>
            </c:forEach>
        </div>
        <!-- q-content -->
        <div class="q-price">
            <p>共<i>${order.commodityList.size()}</i>件商品 合计:<i>￥${order.money}</i></p>
        </div>
        <div class="more">
            <a href="${pageContext.request.contextPath}/order/orderDetail.htm?orderId=${order.id}">更多</a>
            <ul style="margin-left: 75%">
            <c:if test="${order.status == 1}">
                <li class="active" onclick="cancleOrder(${order.id})">取消订单</li>
             </c:if>   
              <%-- <c:if test="${order.status == 1 || order.status == -1 || order.status == 5}">
                <li>删除订单</li>
                </c:if> --%>
            </ul>
        </div>
    </div>
    </c:forEach>
    <!-- query-box -->
    <script type="text/javascript">
    $('.more li').click(function() {
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    
    function cancleOrder(id){
    	$.confirm({
    		  title: '',
    		  text: '确定要取消订单吗',
    		  onOK: function () {
    			  $.ajax({
    					url : baseUrl + "/order/ajax/cancleOrder.htm",
    					type : "POST",
    					data : {"id" : id},
    					success : function(data) {
    						data = eval('(' + data + ')');
    						if(data.success == true){
    							location.reload();
    						}else{
    							
    						}
    					}
    				})
    		  },
    		  onCancel: function () {
    		  }
    		});
    }
    
    </script>
</body>

</html>
