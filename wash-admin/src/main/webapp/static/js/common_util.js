//验证URL格式
function checkUrl(url){
	var patrn = /^(http|https)\:\/\/[\:\w\.\-\/]+(\?[\w\?\%\.\&\-\/\#\^\=]*)?/;
	if(patrn.exec(url)){
		return true;
	}
	return false;
}

//验证联系电话格式
function checkPhone(phone){
	var patrn = /^(\d{3,4}\-?)?\d{7,8}$/;
	if(patrn.exec(phone)){
		return true;
	}
	return false;
}
//验证手机格式
function checkMobile(mobile){
	var patrn= /^1[3|4|5|8][0-9]\\d{8}$/;
	if(patrn.exec(mobile)){
		return true;
	}
	return false;
}
//验证邮箱格式
function checkEmail(email){
	var patrn= /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	if(patrn.exec(email)){
		return true;
	}
	return false;
}
//检查字符串基本格式,type:1-数字,2-字母,3-数字字母,4-数字字母. ,5-任意
function checkString(str,minLength,maxLength,type){
	if(str == '' || str.length == 0){
		return false;
	}
	if(str.length > maxLength || str.length < minLength){
		return false;
	}
	var patrn = '';
	if(type == 1){
		patrn = /\d+/;
	}
	if(type == 2){
		patrn = /[a-zA-Z]+/;
	}
	if(type == 3){
		patrn = /^[0-9a-zA-Z]*$/g;
	}
	if(type == 4){
		patrn = /^[0-9a-zA-Z.]*$/g;
	}
	if(type == 5){
		return true;
	}
	if(patrn.test(str)){
		return true;
	}
	return false;
}

//检查经纬度
function checkLocal(s)
{
    s=s.replace(/\s/g,""); //剔除输入中包含的空白字符（可能由复制粘贴等原因引入）
    if(s.indexOf(".")>-1) //首先必须存在小数点？
    {
            var pb=s.split(".");
            var p=pb[0];
            var b=pb[1];
            var np=Number(p);
            if(p="")np=0;//（0.xx也能写作.xx，因此允许小数点前是空的）
            //然后判断小数点前后是否是数字（精度必须是小数点后5位数？），再判断是否符合数字范围。
            if(!isNaN(np) && !isNaN(Number(b)) && b.length<=5 && np>=-180 && np<=180)
            {
                 return true;
            }
    }
    return false;
}


//验证身份证
function checkIdcard(v_card) {
    var reg = /^\d{15}(\d{2}[0-9X])?$/i;
    if (!reg.test(v_card)) {
        return false;
    }

    if (v_card.length == 15) {

        //return false;
        var n = new Date();
        var y = n.getFullYear();
        if (parseInt("19" + v_card.substr(6, 2)) < 1900 || parseInt("19" + v_card.substr(6, 2)) > y) {
            return false;
        }
        
        var birth = v_card.substr(8, 2) + "-" + v_card.substr(10, 2) + "-" + "19" + v_card.substr(6, 2);
        if (!isDate(birth)) {
            return false;
        }
    }
    if (v_card.length == 18) {
        var n = new Date();
        var y = n.getFullYear();
        if (parseInt(v_card.substr(6, 4)) < 1900 || parseInt(v_card.substr(6, 4)) > y) {
            return false;
        }

        var birth = v_card.substr(10, 2) + "-" + v_card.substr(12, 2) + "-" + v_card.substr(6, 4);
        if (!isDate(birth)) {
            return false;
        }


        iW = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);

        iSum = 0;
        for (var i = 0; i < 17; i++) {
            iC = v_card.charAt(i);
            iVal = parseInt(iC);
            iSum += iVal * iW[i];
        }

        iJYM = iSum % 11;
        if (iJYM == 0) sJYM = "1";
        else if (iJYM == 1) sJYM = "0";
        else if (iJYM == 2) sJYM = "x";
        else if (iJYM == 3) sJYM = "9";
        else if (iJYM == 4) sJYM = "8";
        else if (iJYM == 5) sJYM = "7";
        else if (iJYM == 6) sJYM = "6";
        else if (iJYM == 7) sJYM = "5";
        else if (iJYM == 8) sJYM = "4";
        else if (iJYM == 9) sJYM = "3";
        else if (iJYM == 10) sJYM = "2";

        var cCheck = v_card.charAt(17).toLowerCase();
        if (cCheck != sJYM) {
            return false;
        }
    }
    return true;
}
//验证日期格式
function isDate(strDate) {
	var strSeparator = "-";
	var strDateArray;
	var intYear;
	var intMonth;
	var intDay;
	var boolLeapYear;
	strDateArray = strDate.split(strSeparator);
	if (strDateArray.length != 3)
		return false;
	intYear = parseInt(strDateArray[2], 10);
	intMonth = parseInt(strDateArray[0], 10);
	intDay = parseInt(strDateArray[1], 10);
	if (isNaN(intYear) || isNaN(intMonth) || isNaN(intDay))
		return false;
	if (intMonth > 12 || intMonth < 1)
		return false;
	if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12)
			&& (intDay > 31 || intDay < 1))
		return false;
	if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intDay > 30 || intDay < 1))
		return false;
	if (intMonth == 2) {
		if (intDay < 1)
			return false;
		boolLeapYear = false;
		if ((intYear % 100) == 0) {
			if ((intYear % 400) == 0)
				boolLeapYear = true;
		} else {
			if ((intYear % 4) == 0)
				boolLeapYear = true;
		}
		if (boolLeapYear) {
			if (intDay > 29)
				return false;
		} else {
			if (intDay > 28)
				return false;
		}
	}
	return true;
}

//关闭弹出框
function dialogClose(){
	$("#content").hide();
	$("#mask").hide();
}

//显示弹出框
function showDialog(){
	$("#mask").show();
	$("#content").show();
}
 

//显示文件名
function fileChange(target,objNameId) {           
	$("input[name='"+objNameId+"']").val($(target).val());
}
