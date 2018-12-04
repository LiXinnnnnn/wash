<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/include.inc.jsp" %>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/shopping-cart.css')}"/>
    <%-- <script src="${mytag:getStaticContext('js/jquery.spinner.js')}" type="text/javascript"></script> --%>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body>
    <div class="cart-head" style="display: none;">
        <h1>我的洗衣篮</h1>
    </div>
    
    <form action="${pageContext.request.contextPath}/order/confirmOrder.htm" method="get" id="shopCarForm">
    <div class="cart-line" style="padding-bottom: 5rem;">
    	<c:if test="${commodityList.size() == 0}">
    	<div style="text-align:center;height: 60%;line-height: 300px;color: #C9C9C9;">购物车空空如也~</div>
    	</c:if>
    	<c:forEach items="${commodityList}" var="commodity" >
	        <ul>
	            <li class="select-box" style="width: 10%;">
	                <input type="checkbox" value="${commodity.id}" id="checkbox${commodity.id}" name="shopCarId" checked="checked" 
	                onchange="changePrice(${commodity.id})"/>
	            </li>
	            <li style="width: 25%;"><img src="${commodity.img}" width="75%;"></li>
	            <li style="width: 30%;">
	                <h1>${commodity.name}</h1>
	                <h2>
	                
	                <img src="${mytag:getStaticContext('images/jian.png')}" onclick="changeNumber(-1,${commodity.id})">
	                <input type="text" id="${commodity.id}" value="${commodity.number}" readonly="readonly" />
	                <img src="${mytag:getStaticContext('images/jia.png')}" onclick="changeNumber(1,${commodity.id})" >
	                </h2>
	            </li>
	            <li class="price" style="width: 25%;">
	           		<i id="price${commodity.id}">￥<fmt:formatNumber type="number" value="${commodity.number * commodity.price}" pattern="0.00" maxFractionDigits="2"/></i>
	            </li>
	            <li class="select-box" style="width: 10%;">
	           		<i class="icon-trash" style="vertical-align: middle;margin-top: 40px;" onclick="deleteCommodity(${commodity.id})"></i>
	            </li>
	        </ul>
   		</c:forEach>
               
    </div>
    <!-- cart-line -->
    <div class="settlement">
        <ul>
         
            <li style="width: 60%;">
                <p>合计：<i>￥</i><i id="totalCount">
                <fmt:formatNumber type="number" value="${totalCount}" pattern="0.00" maxFractionDigits="2"/>
                </i></p>
            </li>
            <li style="width: 30%;">
                <input type="button" onclick="submitForm()" value="结算" id="shopcarButton"
                <c:if test="${totalCount > 0}">
                	style="background-color: red"
                </c:if>
                ></input>
            </li>
        </ul>
    </div>
    </form>
    
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
                <img src="${mytag:getStaticContext('images/car-02.png')}">
                <i id="shopCarNumber">${shopCarCount}</i></p>
                    <p class="active">购物车</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userIndex.htm">
                <img src="${mytag:getStaticContext('images/me-01.png')}">
                    <p>我的</p>
                </a>
            </li>
        </ul>
    </div>
    <!-- menu -->
<script type="text/javascript">

function submitForm(){
	var totalCount = $("#totalCount").html();
	totalCount = parseFloat(totalCount);
	if(totalCount > 0){
		$("#shopCarForm").submit();
	}	
}

function changePrice(id){
	var totalCount = $("#totalCount").html();
	totalCount = parseFloat(totalCount);
	var changePrice = $("#price" + id).html();
	changePrice = changePrice.substring(1,changePrice.length);
	changePrice = parseFloat(changePrice);
	if($("#checkbox" + id).is(':checked')){
		totalCount += changePrice;
	}else{
		totalCount -= changePrice;
	}
	totalCount = totalCount.toFixed(2);
	$("#totalCount").html(totalCount);
	//
	if(totalCount == 0){
		$("#shopcarButton").css("background-color","#4F4F57");
	}else{
		$("#shopcarButton").css("background-color","red");
	}
}

/**
 * -1,+1
 */
function changeNumber(addOrminus,commodityId){
	var commodityNumber = $("#" + commodityId).val();
	if(addOrminus == -1 && commodityNumber == 1){
		return ;
	}else{
	$.ajax({
		url : baseUrl + "/shopCar/ajax/changeNumber.htm",
		type : "POST",
		data : {"addOrminus" : addOrminus , "commodityId" : commodityId},
		success : function(data) {
			data = eval('(' + data + ')');
			if(data.success == true){
				var price = data.result;
				$("#" + commodityId).val(price.commodityNumber);
				$("#totalCount").html(price.totalCount);
				$("#price" + commodityId).html("￥" + price.commodityPrice);
			}else{
				
			}
		}
	})
	}
}

function deleteCommodity(commodityId){
	$.confirm({
	  title: '',
	  text: '确定要删除商品吗',
	  onOK: function () {
		  $.ajax({
				url : baseUrl + "/shopCar/ajax/deleteCommodity.htm",
				type : "POST",
				data : {"commodityId" : commodityId},
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
