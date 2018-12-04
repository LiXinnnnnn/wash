<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/confirm-order.css')}"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
    <!-- <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> -->
</head>

<body>
    <div class="order-head">
        <p><img src="${mytag:getStaticContext('images/left.png')}" onclick="history.back()">订单详情</p>
    </div>
    <div class="wrap" style="padding-bottom: 4rem;">
    
    	<div class="content">
            <div class="consignee line">
                <p>订单号:${orderDetail.orderNo}</p>
            </div>
            
            <div class="consignee line">
                <p>订单状态：<c:choose>
        	<c:when test="${orderDetail.status == -1}">
	        	<p>订单取消</p>
	        </c:when>
	        <c:when test="${orderDetail.status == 1}">
	        	<p>等待买家付款</p>
	        </c:when>
	        <c:when test="${orderDetail.status == 2}">
	        	<p>付款成功</p>
	        </c:when>
	        <c:when test="${orderDetail.status == 3}">
	        	<p>商家确认订单</p>
	        </c:when>
	        <c:when test="${orderDetail.status == 4}">
	        	<p>洗衣中</p>
	        </c:when>
	        <c:when test="${orderDetail.status == 5}">
	        	<p>订单结束</p>
	        </c:when>
        </c:choose></p>
            </div>
        </div>
        
        <div class="content" style="margin-top: 1rem;">
            <div class="consignee line">
                <p><img src="${mytag:getStaticContext('images/person.png')}">收货人:<i></i></p>
            </div>
            <div class="name line">
                <div class="name-left fl">
                    <p>${userAddress.name }</p>
                    <p>${userAddress.mobile }</p>
                </div>
                
            </div>
            <div class="consignee line">
                <p><img src="${mytag:getStaticContext('images/address.png')}">收货地址</p>
            </div>
            <div class="name line">
                <p>${userAddress.address }</p>
            </div>
        </div>
        
        <!-- content -->
        <div class="content" style="margin-top: 1rem;">
            <div class="consignee line">
                <p><img src="${mytag:getStaticContext('images/order.png')}" style="margin-top: -2px;">订单详情</p>
            </div>
            <div class="name line">
            <c:forEach items="${orderDetail.commodityList}" var="commodity" >
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
            <div class="consignee line time">
               
                <input type="text" placeholder="备注" name="comment" value="">
            </div>
        </div>
        <!-- content -->
        <div class="settlement">
            <ul>
                <li style="width: 20%;">
                    <p>共${orderDetail.commodityList.size()}件</p>
                </li>
                <li style="width: 50%;">
                    <p>合计：<i>￥${orderDetail.money}</i></p>
                </li>
                <c:choose>
        	
	        <c:when test="${orderDetail.status == 1}">
	        	 <li style="width: 30%;">
                    <input type="button" value="结算" onclick="submitOrder(${orderDetail.id})"></input>
                </li>
	        </c:when>
	       
        </c:choose>
               
            </ul>
        </div>
    </div>
    <!-- wrap -->
    <script type="text/javascript">    
    
    function submitOrder(orderId){
    	
    	
    	/* var luckElements = document.getElementsByName("shopCarId");
    	var shopCarIds = [];
    	for(var i=0;i<luckElements.length;i++){  
            shopCarIds.push(luckElements[i].value);
        }  */ 
        
    	$.ajax({
			url : baseUrl + "/order/ajax/repayOrder.htm",
			type : "POST",
			data : {"orderId":orderId},
			success : function(data) {
				data = eval('(' + data + ')');
				if(data.success == false){
					$.alert("创建订单失败","");
					window.location.href = baseUrl + "/order/list.htm";
				}else{
					var wxJs = data.result;
					onBridgeReady(wxJs.appId,wxJs.timeStamp,wxJs.nonceStr,wxJs.packageString,wxJs.signType,wxJs.paySign);
					//window.location.href = baseUrl + "/index.htm";
				}
			}
		})
        	
    }
    
    function onBridgeReady(appId,timeStamp,nonceStr,packageString,signType,paySign){
    	   WeixinJSBridge.invoke(
    	       'getBrandWCPayRequest', {
    	           "appId":appId,     //公众号名称，由商户传入     
    	           "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
    	           "nonceStr":nonceStr, //随机串     
    	           "package":packageString,     
    	           "signType":signType,         //微信签名方式：     
    	           "paySign":paySign //微信签名 
    	       },
    	       function(res){  
    	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
    	        	   $.alert("支付成功","");
    	        	  // return ;
    	        	   window.location.href = baseUrl + "/order/list.htm";
    	           } // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
    	       }
    	   ); 
    	}
    	
    </script>
    
</body>

</html>
