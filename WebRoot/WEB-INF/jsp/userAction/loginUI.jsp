<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<title>智慧教育后台登录界面</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf" %>
<meta property="qc:admins" content="005476307763751313454163757" />
<!-- qq的js -->
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
 data-appid="101304940" data-redirecturi="http://www.soeasystudy.com/back.jsp" charset="utf-8"></script>
<!-- 微博js -->
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=2840992581&debug=true" type="text/javascript" charset="utf-8"></script>
<!-- 验证码切换 -->
<script type="text/javascript">
	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义    
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。    
		obj.src = "rand.action?d=" + timenow;
	}
	// 在被嵌套时就刷新上级窗口
	if(window.parent != window){
		window.parent.location.reload(true);
	}
	</script>
</head>
<body>
<form action="user_login.action" method="post">
   <font color="red"><s:fielderror fieldName="login"/></font>
   <br/>
    用户名：<input type="text" name="userNum" id="userNameLogin"  />
    <br/> 
    密码：<input type="password" name="password" id="userPassword"/>
    <br/>
    <input type="text" name="randomCode" id="code" >
    <img src="rand.action" onclick="changeValidateCode(this)" title="点击图片刷新验证码" style={width:30px;height:40px}/>
    <br/>
    <input type="radio" value="管理员" name="userType" checked="checked" />管理员
    <br/>
    <input type="radio" value="负责人" name="userType" />负责人
    
    <br/>
        <input type="radio" value="学生" name="userType" />学生
    <br/>
        <input type="radio" value="老师" name="userType" />老师
    <br/>
    <input type="submit" id= "submitForm" value="登录"/>
    </form>
<s:form action="qqLoginInfo_bindUserUI.action" method="post" id="formQQ">
<s:hidden name="openId" id="hidQQ_OpenId" value=""></s:hidden>
<s:hidden name="accessToken" id="hidQQ_AccessToken" value=""></s:hidden>
<s:hidden name="nikeName" id="hidQQ_NikeName" value=""></s:hidden>
<s:hidden name="gender" id="hidQQ_Gender" value=""></s:hidden>
<s:hidden name="birthPlace" id="hidQQ_BirthPlace" value=""></s:hidden>
<s:hidden name="birthYear" id="hidQQ_BirthYear" value=""></s:hidden>
<s:hidden name="figureUrl" id="hidQQ_FigureUrl" value=""></s:hidden>
</s:form>
<s:form action="weiboInfo_bindUserUI.action" method="post" id="formWeibo">
<s:hidden name="identifier" id="hidWeibo_Identifier" value=""></s:hidden>
<s:hidden name="name" id="hidWeibo_Name" value=""></s:hidden>
<s:hidden name="avatar" id="hidWeibo_Avatar" value=""></s:hidden>
<s:hidden name="location" id="hidWeibo_Location" value=""></s:hidden>
<s:hidden name="gender" id="hidWeibo_Gender" value=""></s:hidden>
<s:hidden name="followersCount" id="hidWeibo_Followers" value=""></s:hidden>
<s:hidden name="friendsCount" id="hidWeibo_Friends" value=""></s:hidden>
<s:hidden name="weiBoClass" id="hidWeibo_Class" value=""></s:hidden>
<s:hidden name="createTime" id="hidWeibo_CreateTime" value=""></s:hidden>
<s:hidden name="description" id="hidWeibo_Desc" value=""></s:hidden>
</s:form>

	<span id="qqLoginBtn"></span>
	<!-- 登录  -->
	<script type="text/javascript">
		QC.Login({
			btnId : "qqLoginBtn",//插入按钮的html标签id
			size : "A_M",//按钮尺寸
		});
	</script>
	<!-- 获取信息 -->
	<script type="text/javascript">
		//从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数
		var paras = {};
		//用JS SDK调用OpenAPI
		QC.api("get_user_info", paras)
		//指定接口访问成功的接收函数，s为成功返回Response对象
		.success(function(s) {
			//成功回调，通过s.data获取OpenAPI的返回数据
			alert("获取用户信息成功！当前用户昵称为：" + s.data.nickname);
			alert("当前用户性别为：" + s.data.gender);
			alert("当前用户所在城市为：" + s.data.province + s.data.city);
			alert("当前用户出生日期为：" + s.data.year);
			alert("当前用户头像地址为：" + s.data.figureurl_2);
			$("#hidQQ_NikeName").attr("value",s.data.nickname);
			$("#hidQQ_Gender").attr("value",s.data.gender);
			$("#hidQQ_BirthPlace").attr("value",s.data.province + s.data.city);
			$("#hidQQ_BirthYear").attr("value",s.data.year);
			$("#hidQQ_FigureUrl").attr("value",s.data.figureurl_2);
			
			if (QC.Login.check()) {//如果已登录
				QC.Login.getMe(function(openId, accessToken) {
					alert([ "当前登录用户的", "openId为：" + openId,
							"accessToken为：" + accessToken ].join("\n"));
					$("#hidQQ_OpenId").attr("value",openId);
					$("#hidQQ_AccessToken").attr("value",accessToken);
					//window.location.href = "qQLoginInfo_bindUser.action";
					$("#formQQ").submit();
				});
				}
		})
		//指定接口访问失败的接收函数，f为失败返回Response对象
		.error(function(f) {
			//失败回调
			alert("获取用户信息失败！");
		})
		//指定接口完成请求后的接收函数，c为完成请求返回Response对象
		.complete(function(c) {
			//完成请求回调
			alert("获取用户信息完成！");
			});
	</script>
	
	<!-- 微博登录开始 -->
	<br/><br/>
	<div id="wb_connect_btn"></div>
	<script type="text/javascript">
	WB2.anyWhere(function (W) {
	    W.widget.connectButton({
	        id: "wb_connect_btn",
	        type: '3,2',
	        callback: {
	            login: function (o) { //登录后的回调函数
	                alert("login: " + o.name);
	            	alert("id:"+o.id);
	            	alert("avatar:"+o.avatar_large);
	            	alert("location:"+o.location);
	            	alert("gender:"+o.gender);
	            	alert("粉丝:"+o.followers_count);
	            	alert("关注:"+o.friends_count);
	            	alert("等级:"+o.class);
	            	alert("创建时间:"+o.created_at);
	            	alert("描述:"+o.description);
	    			$("#hidWeibo_Identifier").attr("value",o.id);
	    			$("#hidWeibo_Name").attr("value",o.name);
	    			$("#hidWeibo_Avatar").attr("value",o.avatar_large);
	    			$("#hidWeibo_Location").attr("value",o.location);
	    			$("#hidWeibo_Gender").attr("value",o.gender);
	    			$("#hidWeibo_Followers").attr("value",o.followers_count);
	    			$("#hidWeibo_Friends").attr("value",o.friends_count);
	    			$("#hidWeibo_Class").attr("value",o.class);
	    			$("#hidWeibo_CreateTime").attr("value",o.created_at);
	    			$("#hidWeibo_Desc").attr("value",o.description);
	    			
	    			$("#formWeibo").submit();
	            },
	            logout: function () { //退出后的回调函数
	                alert('logout');
	            }
	        }
	    });
	});
	
	</script>
</body>
</html>
