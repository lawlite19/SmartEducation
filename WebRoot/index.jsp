<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>QQ第三方登录</title>
<meta property="qc:admins" content="005476307763751313454163757" />
<script type="text/javascript"
	src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
	data-appid="101304940"
	data-redirecturi="http://www.soeasystudy.com/back.jsp" charset="utf-8"></script>
</head>
<body>


	<span id="qqLoginBtn"></span>
	<!-- 登录  -->
	<script type="text/javascript">
		QC.Login({
			btnId : "qqLoginBtn",//插入按钮的html标签id
			size : "A_L",//按钮尺寸
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
	<!-- 是否登录成功 -->
	<script type="text/javascript">
		if (QC.Login.check()) {//如果已登录
			QC.Login.getMe(function(openId, accessToken) {
				alert([ "当前登录用户的", "openId为：" + openId,
						"accessToken为：" + accessToken ].join("\n"));
			});
			//这里可以调用自己的保存接口
			//...
		}
	</script>
	<!--
function Add(){
	var openId=$("#hidOpenId").val();
	var accessToken=$("#hidAccessToken").val();
	
	$.ajax({
		type:"POST",
		url:"xxx.action",
		data:"openId="+openId+"&accessToken="+accessToken+"",
		success:function(result){
			alert("aa"+result);
		}
	});
}
 

	<form action="" method="post">
		<input type="hidden" id="hidOpenId" /> 
		<input type="hidden"id="hidAccessToken" />
		<input type="button" value="提交" id="btnSubmit" onclick="Add();" />
	</form>
	 -->
</body>
</html>