//字母数字
jQuery.validator.addMethod('checkPassword', function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]+.*$/.test(value);
}, "<i class='fa fa-info-circle'></i>只能包括英文字母和数字和.");

//不能为中文
jQuery.validator.addMethod('noChinese', function(value, element) {
	return this.optional(element) || ! /.*[\u4e00-\u9fa5]+.*$/.test(value);
	}, "<i class='fa fa-info-circle'></i>不能含有汉字");

//不能为中文
jQuery.validator.addMethod('number', function(value, element) {
	return this.optional(element) ||  /.*\d+.*$/.test(value);
	},  "<i class='fa fa-info-circle'></i>只能是数字");

//过滤攻击脚本
jQuery.validator.addMethod('noScript', function(value, element) {
	var pArr = ["<.+?>", "%3c.+?%3e", "&lt;.+?&gt;","on(.+?)=(.+?)", "on(.+?)%3(.+?)"
	         , "onfocus=(.+?)", "onmousemove=(.+?)", "onmousemove%3(.+?)"
	         , "javascript:(.+?)", "eval\\((.+?)"
	         , "href=(.+?)", "href%3(.+?)"
	         , "onclick=(.+?)"];
	for (var i=0; i<pArr.length; i++) {
    	var index = value.search(pArr[i]);
    	if (index > -1){
    		return false;
    	}
	}
	return true;
	}, 
	"<i class='fa fa-info-circle'></i>不能含有非法脚本");

jQuery.validator.addMethod('checkemail', function(value, element) {
	return this.optional(element) ||  /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(value);
	}, "<i class='fa fa-info-circle'></i>请正确填写邮箱信息");