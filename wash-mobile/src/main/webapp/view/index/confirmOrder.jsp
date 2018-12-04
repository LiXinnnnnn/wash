<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>确认订单</title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/confirm-order.css')}"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
    <!-- <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> -->
</head>

<body>
    <div class="order-head">
        <p><a href="${pageContext.request.contextPath}/shopCar/list.htm"><img src="${mytag:getStaticContext('images/left.png')}"></a>确认订单</p>
    </div>
    <div class="wrap" style="padding-bottom: 4rem;">
    <form action="" id="submitOrderForm">
    	<c:if test="${empty userAddress}">
    	<div class="content">
    		<a href="${pageContext.request.contextPath}/address/add.htm">地址为空，去新增~</a>
    	</div>
    	</c:if>
    	<c:if test="${not empty userAddress}">
        <div class="content">
        	<input type="hidden" value="${userAddress.id }" name="userAddressId" id="userAddressId" >
            <div class="consignee line">
                <p><img src="${mytag:getStaticContext('images/person.png')}">收货人:<i></i></p>
            </div>
            <div class="name line">
                <div class="name-left fl">
                    <p>${userAddress.name }</p>
                    <p>${userAddress.mobile }</p>
                </div>
                <div class="name-right fr">
                    <a onclick="changeAddress(${userAddress.id})" href="javascript:void(0);">
                    <img src="${mytag:getStaticContext('images/Right.png')}"></a>
                </div>
            </div>
            <div class="consignee line">
                <p><img src="${mytag:getStaticContext('images/address.png')}">收货地址</p>
            </div>
            <div class="name line">
                <p>${userAddress.address }</p>
            </div>
        </div>
        </c:if>
        <!-- content -->
        <div class="content" style="margin-top: 1rem;">
            <div class="consignee line">
                <p><img src="${mytag:getStaticContext('images/order.png')}" style="margin-top: -2px;">订单详情</p>
            </div>
            <div class="name line">
            <c:forEach items="${commodityList}" var="commodity" >
            	<input type="hidden" value="${commodity.id }" name="shopCarId" id="shopCarId" >
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
                    
                        <p>小计:￥<fmt:formatNumber type="number" value="${commodity.number * commodity.price}" pattern="0.00" maxFractionDigits="2"/> </p>
                    </div>
                </div>
	   		</c:forEach>
                
            </div>
            <div class="consignee line time">
               
                <input type="text" placeholder="备注" name="comment">
            </div>
        </div>
        <!-- content -->
        <div class="settlement">
            <ul>
                <li style="width: 20%;">
                    <p>共${commodityList.size()}件</p>
                </li>
                <li style="width: 50%;">
                    <p>合计：<i>￥<fmt:formatNumber type="number" value="${totalCount}" pattern="0.00" maxFractionDigits="2"/></i></p>
                </li>
                <li style="width: 30%;">
                    <input type="button" value="结算" onclick="submitOrder()"></input>
                </li>
            </ul>
        </div>
    </form>
    </div>
    <!-- wrap -->
    <script type="text/javascript">
    
    function changeAddress(addressId){
    	var url = window.location.href;
    	url = encodeURI(url);
    	window.location.href = baseUrl + "/address/selectAddress.htm?addressId=" + addressId + "&url=" + url;
    }
    
    
    function submitOrder(){
    	var addressId = $("#userAddressId").val();
    	
    	/* var luckElements = document.getElementsByName("shopCarId");
    	var shopCarIds = [];
    	for(var i=0;i<luckElements.length;i++){  
            shopCarIds.push(luckElements[i].value);
        }  */ 
        if(addressId == '' || typeof(addressId) == "undefined"){
        	$.alert("请选择地址","");
        }else{
    	$.ajax({
			url : baseUrl + "/order/ajax/submitOrder.htm",
			type : "POST",
			data : $('#submitOrderForm').serialize(),
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
