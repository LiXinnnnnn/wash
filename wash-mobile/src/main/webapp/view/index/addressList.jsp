<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/shipping-address.css')}"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body style="background-color: #f2f2f2;padding-bottom: 4rem;">
    <div class="title">
        <h1><a href="${pageContext.request.contextPath}/user/userIndex.htm"><img src="${mytag:getStaticContext('images/left.png')}"></a>管理收货地址</h1>
    </div>
    <c:forEach items="${addressList}" var="address">
    <div class="run-line">
        <div class="r-name">
            <h1><i>${address.name}</i><span>${address.mobile}</span></h1>
            <h2>${address.address}</h2>
        </div>
        <div class="r-btns">
        	<c:if test="${address.isDefault == 1}">
            <label>默认地址</label>
            </c:if>
            <ul>
            	<c:if test="${address.isDefault == 0}">
            	<li class="active" style="width: 105px" onclick="setDefaultAddress(${address.id})">设为默认地址</li>
            	</c:if>
                <li class="active" onclick="deleteAddress(${address.id})">删除</li>
                <!-- <li class="active">编辑</li> -->
            </ul>
        </div>
    </div>
    </c:forEach>
    
    <div class="add-address-btn">
        <input type="button" value="新增收货地址" onclick="window.location='${pageContext.request.contextPath}/address/add.htm'"></input>
    </div>
    <script type="text/javascript">
    $('.r-btns li').click(function() {
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    
    function deleteAddress(addressId){
    	$.confirm({
    		  title: '',
    		  text: '确定要删除地址吗',
    		  onOK: function () {
    			  $.ajax({
    					url : baseUrl + "/address/ajax/deleteAddress.htm",
    					type : "POST",
    					data : {"addressId" : addressId},
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
    
    function setDefaultAddress(addressId){
    	$.confirm({
    		  title: '',
    		  text: '设为默认地址？',
    		  onOK: function () {
    			  $.ajax({
    					url : baseUrl + "/address/ajax/setDefaultAddress.htm",
    					type : "POST",
    					data : {"addressId" : addressId},
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


