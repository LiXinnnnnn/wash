<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/layout_tiles/public_include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/shipping-address.css')}"/>
    <script src="${mytag:getStaticContext('js/jquery.form.js')}" type="text/javascript"></script>
    <%-- <script src="${mytag:getStaticContext('js/validate.expand.js')}" type="text/javascript"></script> --%>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0' />
</head>

<body>
    <div class="title">
        <h1><a href="${pageContext.request.contextPath}/address/list.htm"><img src="${mytag:getStaticContext('images/left.png')}"></a>新增收货地址</h1>
    </div>
    <form action="${pageContext.request.contextPath}/address/ajax/add.htm" id="addForm" name="addForm">
    <div class="add-line">
        <input type="text" placeholder="收货人姓名" name="name" id="name"></input>
    </div>
    <div class="add-line">
        <input type="text" placeholder="手机号码" name="mobile" id="mobile"></input>
    </div>
    <div class="add-line">
        <input type="text" placeholder="详细地址" name="address" id="address"></input>
    </div>
   
    <div class="add-address-btn"style="position: relative;margin-top: 1rem;">
        <button type="button" onclick="add()">保存</button>
    </div>
    </form>
    
    <script type="text/javascript">
    
  //不能为中文
    
    function add(){
    	var name = $("#name").val();
    	var mobile = $("#mobile").val();
    	var address = $("#address").val();
    	
    	if(name == ''){
    		$.alert("姓名不为空");
    		return;
    	}
    	if(mobile == ''){
    		$.alert("手机不为空");
    		return;
    	}
    	if(address == ''){
    		$.alert("地址不为空");
    		return;
    	}else{
    		//提交表单
			$.ajax({
				url : baseUrl + "/address/ajax/add.htm",
				type : "POST",
				data : $('#addForm').serialize(),
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.success == true) {
						
						$.confirm({
				    		  title: '',
				    		  text: '新增成功',
				    		  onOK: function () {
				    			  window.location.href = baseUrl + "/address/list.htm";
				    		  },
				    		  onCancel: function () {
				    		  }
				    		});
					} else {
						$.alert("新增失败");
					}
				}
			})
    	}
			
    		
    }

    
    </script>
</body>

</html>
