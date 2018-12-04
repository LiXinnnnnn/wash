var UIAlertDialogApi = function () {
    var handleDialogs = function() {        
		   $('#demo_1').click(function(){
			  bootbox.dialog({
				  // dialog的内容
				  message: "<p>excel有错误,请检查修改后再重新提交。</p><ul><li>-第2行cpuId数据库里边已经存在</li><li>-第3行cpuId数据库里边已经存在</li><li>-第4行cpuId数据库里边已经存在</li></ul>",   
				  // dialog的标题
				  title: "提示",   
				  // 退出dialog时的回调函数，包括用户使用ESC键及点击关闭
				  onEscape: function() {},   
				  // 是否显示此dialog，默认true
				  show: true,   
				  // 是否显示body的遮罩，默认true
				  backdrop: true,   
				  // 是否显示关闭按钮，默认true
				  closeButton: true,   
				  // 是否动画弹出dialog，IE10以下版本不支持
				  animate: true,   
				  // dialog的类名
				  className: "notice",   
				  // dialog底端按钮配置
				  buttons: {     						
					"确定": {
					  className: "btn-primary btn-lg btn-block",
					  callback: function() {}
					}
				  }
				});
			});
			//end #demo_1    
		   $('#demo_2').click(function(){
			  bootbox.dialog({		 
				  message: "<p>检查完毕，上传的表格没有问题，可以导入数据。</p>",   		  
				  title: "提示",   		  
				  onEscape: function() {},   		 
				  show: true,   		  
				  backdrop: true,   		  
				  closeButton: true,   		  
				  animate: true,   		  
				  className: "notice",   		  
				  buttons: {     						
					"从excel表中导入数据": {
					  className: "btn-primary btn-lg btn-block",
					  callback: function() {}
					}
				  }
				});
			});
            //end #demo_2           
    }
    var handleAlerts = function() {
        
        $('#alert_show').click(function(){

            Metronic.alert({
                container: $('#alert_container').val(), // alerts parent container(by default placed after the page breadcrumbs)
                place: $('#alert_place').val(), // append or prepent in container 
                type: $('#alert_type').val(),  // alert's type
                message: $('#alert_message').val(),  // alert's message
                close: $('#alert_close').is(":checked"), // make alert closable
                reset: $('#alert_reset').is(":checked"), // close all previouse alerts first
                focus: $('#alert_focus').is(":checked"), // auto scroll to the alert after shown
                closeInSeconds: $('#alert_close_in_seconds').val(), // auto close after defined seconds
                icon: $('#alert_icon').val() // put icon before the message
            });

        });

    }
    return {
        //main function to initiate the module
        init: function () {
            handleDialogs();
            //handleAlerts();
        }
    };

}();