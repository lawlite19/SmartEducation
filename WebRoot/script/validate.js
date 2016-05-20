/**
 * 去除多余空格函数
 * trim():去除两边空格 lTrim():去除左空格 rTrim(): 去除右空格
 * 用法：
 *     var str = "  hello ";
 *     str = str.trim();
 */
String.prototype.trim = function()
{
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}
String.prototype.lTrim = function()
{
    return this.replace(/(^[\s]*)/g, "");
}
String.prototype.rTrim = function()
{
    return this.replace(/([\s]*$)/g, "");
}

/**
*校验字符串是否为空
*返回值：
*如果不为空，定义校验通过，返回true，校验不通过，返回false
*/
function checkIsNotEmpty(str)
{
    if(str.trim() == "")
        return false;
    else
        return true;
}

/**
*校验字符串是否为整型
*返回值：
*如果为空，定义校验不通过，    返回true
*如果字串全部为数字，校验通过，返回true，校验不通过，返回false
*/
function checkIsInteger(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if(/^(\-?)(\d+)$/.test(str))
        return true;
    else
        return false;
}
/**
*校验整型最小值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验不通过，              返回true
*如果满足条件，大于等于给定值，校验通过，返回true，小于给定值，返回false
*/
function checkIntegerMinValue(str,val)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)>=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}
/**
*校验整型最大值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验不通过，              返回true
*如果满足条件，小于等于给定值，校验通过，返回true，大于给定值，返回false
*/
function checkIntegerMaxValue(str,val)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)<=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}
/**
*校验整型是否为非负数
*str：要校验的串。
*
*返回值：
*如果为空，定义校验不通过，返回true
*如果非负数，返回true，如果是负数，返回false
*/
function isNotNegativeInteger(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10) < 0)
            return false;
        else
            return true;
    }
    else
        return false;
}

/**
*校验字符串是否为浮点型
*返回值：
*如果为空，定义校验不通过，      返回true
*如果字串为浮点型，校验通过，返回true，校验不通过，返回false
*/
function checkIsDouble(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    //如果是整数，则校验整数的有效性
    if(str.indexOf(".") == -1)
    {
        if(checkIsInteger(str) == true)
            return true;
        else
            return false;
    }
    else
    {
        if(/^(\-?)(\d+)(.{1})(\d+)$/g.test(str))
            return true;
        else
            return false;
    }
}
/**
*校验浮点型最小值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验不通过，              返回true
*如果满足条件，大于等于给定值，校验通过，返回true，小于给定值，返回false
*/
function checkDoubleMinValue(str,val)
{
    //如果为空，则不通过校验
    if(str.trim() == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsDouble(str) == true)
    {
        if(parseFloat(str)>=parseFloat(val))
            return true;
        else
            return false;
    }
    else
        return false;
}
/**
*校验浮点型最大值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验不通过，              返回true
*如果满足条件，小于等于给定值，校验通过，返回true，如果大于给定值，返回false
*/
function checkDoubleMaxValue(str,val)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsDouble(str) == true)
    {
        if(parseFloat(str)<=parseFloat(val))
            return true;
        else
            return false;
    }
    else
        return false;
}
/**
*校验浮点型是否为非负数
*str：要校验的串。
*
*返回值：
*如果为空，定义校验不通过，返回true
*如果非负数，返回true；如果是负数，返回false
*/
function isNotNegativeDouble(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if(checkIsDouble(str) == true)
    {
        if(parseFloat(str) < 0)
            return false;
        else
            return true;
    }
    else
        return false;
}

/**
*校验字符串是否为日期型
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为日期型，校验通过，返回true；校验通过，返回false
*/
function checkIsValidDate(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    var pattern = /^((\d{4})|(\d{2}))-(\d{1,2})-(\d{1,2})$/g;
    if(!pattern.test(str))
        return false;
    var arrDate = str.split("-");
    if(parseInt(arrDate[0],10) < 100)
        arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    if(date.getFullYear() == arrDate[0]
       && date.getMonth() == (parseInt(arrDate[1],10) -1)+""
       && date.getDate() == arrDate[2])
        return true;
    else
        return false;
}
/**
*校验字符串是否为时间型
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为时间型，校验通过，返回true；校验通过，返回false
*/
function checkIsValidTime(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    var pattern = /^(\d{1,2}):(\d{2})$/g;
	if(pattern.test(str)) 
	{ 
		if( RegExp.$1 <24 && RegExp.$2<60) return true; 
	}else{
		alert("时间地址格式不正确");
		return false;
	}
}
/**
*校验两个日期的先后
*返回值：
*如果其中有一个日期为空，校验不通过,        返回false
*如果起始日期早于等于终止日期，校验通过，返回true；如果起始日期晚于终止日期，返回false
*/
function checkDateEarlier(strStart,strEnd)
{
    if(checkIsValidDate(strStart) == false || checkIsValidDate(strEnd) == false)
        return false;
    //如果有一个输入为空，则通过检验
    if (( strStart.trim() == "" ) || ( strEnd.trim() == "" ))
        return true;
    var arr1 = strStart.split("-");
    var arr2 = strEnd.split("-");
    var date1 = new Date(arr1[0],parseInt(arr1[1].replace(/^0/,""),10) - 1,arr1[2]);
    var date2 = new Date(arr2[0],parseInt(arr2[1].replace(/^0/,""),10) - 1,arr2[2]);
    if(arr1[1].length == 1)
        arr1[1] = "0" + arr1[1];
    if(arr1[2].length == 1)
        arr1[2] = "0" + arr1[2];
    if(arr2[1].length == 1)
        arr2[1] = "0" + arr2[1];
    if(arr2[2].length == 1)
        arr2[2]="0" + arr2[2];
    var d1 = arr1[0] + arr1[1] + arr1[2];
    var d2 = arr2[0] + arr2[1] + arr2[2];
    if(parseInt(d1,10) > parseInt(d2,10))
       return false;
    else
       return true;
}

/**
*校验字符串是否为email型
*返回值：
*如果为空，定义校验不通过，         返回true
*如果字串为email型，校验通过，返回true；如果email不合法，返回false
*/
function checkEmail(str)
{
    //如果为空，则通过校验
    if(str.trim() == "")
        return true;
    if (str.charAt(0) == "." || str.charAt(0) == "@" || str.indexOf('@', 0) == -1 || str.indexOf('.', 0) == -1 || str.lastIndexOf("@") == str.length - 1 || str.lastIndexOf(".") == str.length - 1) {
        alert("EMail格式不正确，正确格式如：abc@abc.com");
        return false;
	}
    else{
        return true;
	}
}

/**
*校验字符串是否为IPv4型
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为IPv4型，校验通过，返回true；不合法，返回false
*/
function checkIPv4(strIP)
{
	//如果为空，则不通过校验
	if (strIP.trim() == "") 
		return true; 
	if(/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g.test(strIP)) 
	{ 
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true; 
	} else{
		alert("IPv4地址格式不正确，正确格式如：255.255.255.255");
		return false;
	}
}

/**
*校验字符串是否为IPv6型
*返回值：
*如果为空，定义校验不通过，         返回true
*如果字串为IPv6型，校验通过，返回true；不合法，返回false
*/
function checkIPv6(strIP)
{
	//如果为空，则不通过校验
	if (strIP.trim() == "")
		return true; 
	if(/^(\d+)\.(\d+)\.(\d+)\.(\d+)\.(\d+)\.(\d+)$/g.test(strIP)) 
	{ 
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256 && RegExp.$5<256 && RegExp.$6<256) 
			return true; 
	} 
	else{
		alert("IPv6地址格式不正确，正确格式如：255.255.255.255.255.255");
		return false;
	}
}

/**
*校验字符串是否是带区号的固定电话格式；例如：0511-4405222 或 021-87888822
*返回值：
*如果为空，定义校验不通过，						返回true
*如果字串为带区号的固定电话格式，校验通过，返回true；校验不通过，返回false
*/
function checkTelNumber(str)
{
	//如果为空，则不通过校验
	if (str.trim() == "") 
		return true; 
	if(/^(\d{3,4})-(\d{7,8})$/.test(str)) 
		return true; 
	else{
		alert("固定电话号码格式不正确，正确格式如：0511-4405222 或 021-87888822");
		return false;
	}
}

/**
*校验字符串是否是手机号码格式
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为手机号码格式，校验通过，返回true；校验不通过，返回false
*/
function checkMobileNumber(str)
{
	//如果为空，则不通过校验
	if (str.trim() == "") 
		return true; 
	if(/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(str)) 
		return true; 
	else{
		alert("手机号码格式不正确，正确格式如：13512345678");
		return false;
	}
}

/**
*校验字符串是否是邮政编码格式
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为邮政编码格式，校验通过，返回true；校验不通过，返回false
*/
function checkPostalCode(str)
{
	//如果为空，则不通过校验
	if (str.trim() == "") 
		return true; 
	if(/^[1-9]{1}(\d{5})$/.test(str)) 
		return true; 
	else{
		alert("邮政编码格式不正确，正确格式必须为非0开头的6位数");
		return false;
	}
}

/**
*校验字符串是否是身份证号码格式
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为身份证号码格式，校验通过，返回true；校验不通过，返回false
*/
function checkID(str)
{
	//如果为空，则不通过校验
	if (str.trim() == "") 
		return true; 
	if(/^[1-9]((\d{17})|(\d{16}x)|(\d{14})|(\d{13}x))$/i.test(str)) 
		return true; 
	else{
		alert("身份证号码格式不正确");
		return false;
	}
}

/**
*校验字符串是否符合登陆名格式规范，只能输入5-20个以字母开头、可带数字、“_”的字串
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串是否符合登陆名格式，校验通过，返回true，不通过，返回false
*/
function checkUserName(str)
{
	//如果为空，则不通过校验
	if (str.trim() == "") 
		return true; 
	if(/^[a-zA-Z]{1}([a-zA-Z0-9]){4,19}$/.test(str)) 
		return true; 
	else
		return false;
}

/**
*校验字符串是否符合密码格式规范，只能输入5-20个以字母开头、可带数字、“_”的字串
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串是否符合登陆名格式，校验通过，返回true，不通过，返回false
*/
function checkPasswd(str)
{
	//如果为空，则不通过校验
	if (str.trim() == "") 
		return true; 
	if(/^(\w){6,20}$/.test(str)) 
		return true; 
	else
		return false;
}
/**
*校验字符串是否是URL地址格式的
*返回值：
*如果为空，定义校验不通过，返回true
*如果字串为URL地址，校验通过，返回true；校验不通过，返回false
*/
function checkURL(str)
{
	var regExp = new RegExp("^((https|http|ftp|rtsp|mms)://)[^/s]*");
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(regExp.test(str))
        return true;
    else{
		alert("URL地址格式不正确，形如：http://abc.com");
        return false;
	}
}

/**
*校验字符串是否为中文
*返回值：
*如果为空，定义校验不通过，         返回true
*如果字串为中文，校验通过，返回true；如果字串为非中文，返回false
*/
function checkIsChinese(str)
{
    //如果值为空，通过校验
    if (str == "")
        return true;
    var pattern = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi;
    if (pattern.test(str))
        return true;
    else
        return false;
}

/**
 * 计算字符串的长度，一个汉字两个字符
 */
String.prototype.realLength = function()
{
  return this.replace(/[^\x00-\xff]/g,"**").length;
}

/**
*校验字符串是否符合自定义正则表达式
*str 要校验的字串  pat 自定义的正则表达式
*返回值：
*如果为空，定义校验不通过，         返回true
*如果字串符合，校验通过，           返回true
*如果字串不符合，                   返回false    参考提示信息：必须满足***模式
*/
function checkMask(str,pat)
{
    //如果值为空，通过校验
    if (str == "")
        return true;
    var pattern = new RegExp(pat,"gi")
    if (pattern.test(str))
        return true;
    else
        return false;
}

/**

 * 得到文件的后缀名
 * oFile为file控件对象
 */
function getFilePostfix(oFile)
{
    if(oFile == null)
        return null;
    var pattern = /(.*)\.(.*)$/gi;
    if(typeof(oFile) == "object")
    {
        if(oFile.value == null || oFile.value == "")
            return null;
        var arr = pattern.exec(oFile.value);
        return RegExp.$2;
    }
    else if(typeof(oFile) == "string")
    {
        var arr = pattern.exec(oFile);
        return RegExp.$2;
    }
    else
        return null;
}
/*
转换为时间格式
*/
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
     (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
     RegExp.$1.length == 1 ? o[k] :
     ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

//function checkDate(data) {
//    if (data.trim() == "")
//        return true; 
//    else
//    {
//     var reg = new RegExp(^(?:(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(\/|-|\.)(?:0?2\1(?:29))$)|(?:(?:1[6-9]|[2-9]\d)?\d)(\/|-|\.)(?:(?:(?:0?[13578]|1[02])\2(?:31))|(?:(?:0?[1,3-9]|1[0-2])\2(29|30))|(?:(?:0?[1-9])|(?:1[0-2]))\2(?:0?[1-9]|1\d|2[0-8]))$);
//        if (!/^\(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/.test(data.trim())) {
//            alert("日期格式不正确！1正确格式为1900-10-10");
//            return false;
//        }
//        else
//            return true;
//    }
//}
function checkDate(data) {
    if (data.trim() == "")
        return true;
    else {
        var mycheck = /^(?:(?:1[6-9]|[2-9]\d)?\d{2}[\-\.](?:0?[1,3-9]|1[0-2])[\-\.](?:29|30))(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\d)?\d{2}[\-\.](?:0?[1,3,5,7,8]|1[02])[\-\.]31)(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])[\-\.]0?2[\-\.]29)(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:16|[2468][048]|[3579][26])00[\-\.]0?2[\-\.]29)(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\d)?\d{2}[\-\.](?:0?[1-9]|1[0-2])[\-\.](?:0?[1-9]|1\d|2[0-8]))(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$/;
        if (!mycheck.test(data.trim())) {
            alert("日期格式不正确！正确格式为1900-10-10！");
            return false;
        }
        else
            return true;

    }
}