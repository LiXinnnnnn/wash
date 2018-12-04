//登录验证
function loginSubmit() {
	operateCookies();
	//用户名检查规则：不能为空，数字和字母,长度1-20
	if ($("#username").val().trim() == "") {
		$('.alert-danger').find('span').html( "请输入用户名");
		$('.alert-danger').show(); 
		$('#username').focus();
		return false;
	}
	if (!checkString($("#username").val(),2,20,4)){
		$('.alert-danger').find('span').html( "用户名为2-20位的数字、字母和.");
		$('.alert-danger').show(); 
		$('#username').focus();
		return false;
	}
	//密码规则：不能为空，数字和字母，长度1-20
	if ($('#password').val().trim() == "") {
		$('.alert-danger').find('span').html( "请输入密码");
		$('.alert-danger').show(); 
		$("#password").focus();
		return false;
	}
	if (!checkString($('#password').val(),3,20,4)){
		$('.alert-danger').find('span').html( "密码为3-20位的数字、字母和.");
		$('.alert-danger').show(); 
		$('#password').focus();
		return false;
	}
	//验证码
	/*if ($('#j_captcha').val().trim() == "") {
		$('.alert-danger').find('span').html( "请输入验证码");
		$('.alert-danger').show(); 
		$("#j_captcha").focus();
		return false;
	}
	if (!checkString($('#j_captcha').val(),4,4,3)){
		$('.alert-danger').find('span').html( "验证码格式错误");
		$('.alert-danger').show(); 
		$('#j_captcha').focus();
		return false;
	}*/
	//提交登录表单
	$.ajax({
		type : "POST",
		url : baseUrl + "/operator/ajax/loginSubmit.htm",
		data : "loginName=" + $("#username").val() + "&password="
				+ $("#password").val() + "&kaptcha=" +$("#j_captcha").val(),
		success : function(data) {
			data = eval('(' + data + ')');
			if (data.success == "true") {
				var page=data.path;
				
					window.location.href = baseUrl + "/order/list.htm";
				
				
			} else { 
				$('.alert-danger').find('span').html(data.msg);
				$('.alert-danger').show(); 
				$('#kaptchaImage').hide().attr('src', baseUrl+'/operator/login/kaptcha.htm?' + Math.floor(Math.random()*100) ).fadeIn();  
			    event.cancelBubble=true;  
			}
		}
	})
}

//修改密码
function updatePassword() {
	$("#form1").validate({
		errorClass : "Notice",
		debug : true,
		rules : {
			oldPassword : {
				required : true,
				rangelength:[3,20],
				checkPassword : true,
				noScript:true
			},
			newPassword :{
				required : true,
				rangelength:[3,20],
				checkPassword : true,
				noScript:true
			},
			confirmPassword : {
				equalTo : "#newPassword"
			}
		},
		messages : {
			oldPassword : {
				required : "<i class='fa fa-info-circle'></i>请输入旧密码",
				rangelength : "<i class='fa fa-info-circle'></i>输入长度必须介于 3 和 20 之间的字符串",
			},
			newPassword : {
				required : "<i class='fa fa-info-circle'></i>请输入新密码",
				rangelength : "<i class='fa fa-info-circle'></i>输入长度必须介于 3 和 20 之间的字符串",
			},
			confirmPassword : {
				equalTo : "两次输入密码不一致不一致"
			}
		},
		submitHandler: function(form) {
			//提交表单
			$.ajax({
				url : baseUrl + "/operator/ajax/updatePassword.htm",
				type : "POST",
				data : "oldPassword=" + $('#oldPassword').val() + "&newPassword="
						+ $('#newPassword').val(),
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.success == "true") {
						showInfo(data.msg,baseUrl + "/operator/login.htm");
					} else if(data.success == "false"){
						showInfo(data.msg,null);
					}else{
						showInfo(data.msg,baseUrl + "/operator/login.htm?page="+window.location.href);
					}
					//window.location.href = baseUrl + "/device/deviceList.htm";
				}
			})
		 },
	});
}

function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', baseUrl+'/operator/login/kaptcha.htm?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
} 

$(function(){  //生成验证码         
    $('#kaptchaImage').click(function () {  
    $(this).hide().hide().attr('src', baseUrl+'/operator/login/kaptcha.htm?' + Math.floor(Math.random()*100) ).fadeIn(); });      
});   
 
window.onbeforeunload = function(){  
    //关闭窗口时自动退出  
    if(event.clientX>360&&event.clientY<0||event.altKey){     
        alert(parent.document.location);  
    }  
};  

//操作cookies
function operateCookies(){
	 if ($("#remember").attr("checked") == "checked") {
	   var username = $("#username").val();
       var password = $("#password").val();
       $.cookie("rememberUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
       $.cookie("cookie_username", username, { expires: 7 }); 
       $.cookie("cookie_password", password,  { expires: 7 }); 
       console.log('coookie saved');
       }
       else {
      
       $.cookie("rememberUser", "false", { expires: -1 });        // 删除 cookie
       $.cookie("cookie_password", '', { expires: -1 });
       $.cookie("cookie_username", '', { expires: -1 });
       console.log('coookie deleted');
       }
}
//新增
function addSubmit() {
	$("#form_submit").validate({
		errorClass : "Notice",
		debug : true,
		rules : {
			loginName : {
				required: true,
				maxlength:20 ,//数据库里最大长度32
				noChinese : true,
				noScript:true
			},
			pwd :{
			     required: true,
			     //minlength:1,
			     maxlength:20, //32
			     noScript:true
			},
			email :{
				maxlength:64, //64
				noScript:true,
				checkemail:true
			} 
		},
		messages : {
			loginName : {
				required:"<i class='fa fa-info-circle'></i> 请输入用户名",
				maxlength:"<i class='fa fa-info-circle'></i> 长度不能大于20个字符",
			},
			pwd: {
			    required:"<i class='fa fa-info-circle'></i> 请输入密码",
			    //minlength: "<i class='fa fa-info-circle'></i> 长度不能大于5",
			    maxlength: "<i class='fa fa-info-circle'></i> 长度不能大于20个字符",
			},
			email: {
				maxlength: "<i class='fa fa-info-circle'></i> 长度不能大于64个字符",
			
			}
		},
		 submitHandler: function(form) {
			 $.ajax({
					url : baseUrl + "/operator/ajax/add.htm",
					type : "POST",
					data : "loginName=" + $('#loginName').val() + "&pwd=" + $('#pwd').val()
							+ "&email=" + $('#email').val(),
					success : function(data) {
						data = eval('(' + data + ')');
						if (data.success == "true") {
							showInfo(data.msg,baseUrl + "/operator/operatorList.htm");
						} else if(data.success == "false"){
							showInfo(data.msg,null);
						}else{
							showInfo(data.msg,baseUrl + "/operator/login.htm?page="+window.location.href);
						}
					}
				});
		 },

		
	});
}
