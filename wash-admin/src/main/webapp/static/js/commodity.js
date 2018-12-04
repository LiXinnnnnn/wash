
function add(){
	
	$("#add_form_submit").validate({
		errorClass : "Notice",
		debug : true,
		rules : {
			name :{
				required : true,
				maxlength : 10
			},
			categoryId : {
				required : true
			},
			unit : {
				required : true,
				maxlength : 5
			},
			price : {
				required : true
			},
			img : {
				required : true,
				minlength : 1
			}

		},
		messages : {
			
			name : {
				required : "<i class='fa fa-info-circle'></i>请输入商品名称",
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过10个字"
			},
			categoryId : {
				maxlength : "<i class='fa fa-info-circle'></i>请选择商品分类"
			},
			unit : {
				required : "<i class='fa fa-info-circle'></i>请输入商品单位",
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过5个字"
			},
			price : {
				required : "<i class='fa fa-info-circle'></i>请输入商品价格"
			},
			img : {
				required : "<i class='fa fa-info-circle'></i>请选择图片",
				minlength : "<i class='fa fa-info-circle'></i>请选择图片"
			}
			
		},
		submitHandler: function(form) {
			
			//提交表单
			$.ajax({
				url : baseUrl + "/commodity/ajax/add.htm",
				type : "POST",
				data : $('#add_form_submit').serialize(),
				success : function(data) {
					endLoad();
					data = eval('(' + data + ')');
					if (data.success == "true") {
						showInfo(data.msg,baseUrl + "/commodity/list.htm");
					} else if(data.success == "false"){
						showInfo(data.msg,null);
					}else{
						showInfo(data.msg,baseUrl + "/operator/login.htm?page="+window.location.href);
					}
				}
			})
		 }
	});
}



function updateMer(){
	
	$("#update_form_submit").validate({
		errorClass : "Notice",
		debug : true,
		rules : {
			name :{
				required : true,
				maxlength : 100
			},
			shortName : {
				maxlength : 50
			},
			mscMerId : {
				required : true,
				maxlength : 50,
				noChinese : true
			},
			email : {
				checkemail : true,
				noChinese : true,
				maxlength : 100
			},
			telephone : {
				number : true,
				maxlength : 50
			},
			address : {
				required : true,
				maxlength : 250
			},
			merIndustryId : {
				required : true,
			}

		},
		messages : {
			
			name : {
				required : "<i class='fa fa-info-circle'></i>请输入商户名",
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过100个字"
			},
			shortName : {
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过50个字"
			},
			mscMerId : {
				required : "<i class='fa fa-info-circle'></i>请输入MSC商户号",
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过50个字"
			},
			email : {
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过100个字符"
			},
			telephone : {
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过50个数字"
			},
			address : {
				required : "<i class='fa fa-info-circle'></i>请输入商户地址",
				maxlength : "<i class='fa fa-info-circle'></i>输入长度不超过250个字"
			},
			merIndustryId : {
				required : "<i class='fa fa-info-circle'></i>请选择行业",
			}
			
		},
		submitHandler: function(form) {
			
			//提交表单
			startLoad();
			$.ajax({
				url : baseUrl + "/mer/ajax/update.htm",
				type : "POST",
				data : $('#update_form_submit').serialize(),
				success : function(data) {
					endLoad();
					data = eval('(' + data + ')');
					if (data.success == "true") {
						showInfo(data.msg,baseUrl + "/mer/list.htm");
					} else if(data.success == "false"){
						showInfo(data.msg,null);
					}else{
						showInfo(data.msg,baseUrl + "/operator/login.htm?page="+window.location.href);
					}
				}
			})
		 }
	});
}

function showInfoSelf(content, returnUrl){
	$("#alert-modal-content").empty();
	$("#alertModalLabel").text('消息');
	$("#alert-modal-content").text(content);
	$("#alert-model-cancel").hide();

	if(returnUrl != null ){
		 
		$("#alert-modal-comfirm").attr("data-dismiss","modal");		 
		$("#alert-modal-comfirm").on("click", function(e){
			e.preventDefault();
			window.location.href = returnUrl;
		}
		);
	 
	}else{
		$("#alert-modal-comfirm").attr("data-dismiss","modal");
		$("#alert-modal-comfirm").removeAttr("href");
	}
	$("#alertModal").modal('toggle');
}



function startLoad(){
	
    Metronic.blockUI({
        target: '.table-container',
        overlayColor: 'none',
        animate: true
    });
	
}

function endLoad(){
	
	Metronic.unblockUI('.table-container');
}