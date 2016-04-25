<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<title>智慧教育后台登录界面</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf" %>

    <style type="text/css">
        .auto-style1 {
            width: 90px;
            height: 27px;
        }

        .auto-style2 {
            height: 27px;
        }
    </style>
<meta property="qc:admins" content="005476307763751313454163757" />
<!-- qq的js -->
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
 data-appid="101304940" data-redirecturi="http://www.soeasystudy.com/back.jsp" charset="utf-8"></script>
<!-- 微博js -->
<script type="text/javascript" src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=2840992581"  charset="utf-8"></script>
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
<body style="background-color: #0055AD">
<!-- 第三方登录 -->
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
	<!-- 登录  
	<span id="qqLoginBtn"></span>
	<script type="text/javascript">
		QC.Login({
				btnId : "qqLoginBtn",//插入按钮的html标签id
				//size : "B_S",//按钮尺寸
			});
	</script> -->
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
<!-- 登录 -->
   <form id="formLogin" action="user_login.action" method="post">
        <table style="width: 100%; position: absolute; left: 0px; top: 0px;" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_left.jpg'); background-repeat: repeat-x; width: 25%"></td>
                <td style="width: 1000px; border: 0px">
                    <table bgcolor="#0055AD" border="0" cellpadding="0" cellspacing="0" width="1000">
                        <tr>
                            <td rowspan="6" style="background-image: url('${pageContext.request.contextPath}/style/login/login_left.jpg'); width: 5px; height: 660px;"></td>
                            <td colspan="5" style="background-image: url('${pageContext.request.contextPath}/style/login/login_top.jpg'); width: 990px; height: 60px;"></td>
                            <td rowspan="6" style="background-image: url('${pageContext.request.contextPath}/style/login/login_right.jpg'); width: 5px; height: 660px;"></td>
                        </tr>
                        <tr>
                            <td colspan="5" style="background-image: url('${pageContext.request.contextPath}/style/login/login_titleold.jpg'); width: 990px; height: 125px;"></td>
                        </tr>
                        <tr>
                            <td colspan="3" style="background-image: url('${pageContext.request.contextPath}/style/login/login_logo_left.jpg'); width: 605px; height: 73px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_logo.jpg'); width: 264px; height: 73px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_logo_right.jpg'); width: 121px; height: 73px;"></td>
                        </tr>
                        <tr>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_center_left.jpg'); width: 46px; height: 299px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_center_font.jpg'); width: 56px; height: 299px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_center_pic.jpg'); width: 503px; height: 299px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_center_form.jpg'); width: 264px; height: 299px; vertical-align: top;">
                             <%--登录表单开始--%>
                                <table style="width: 90%; margin-left: 5px; margin-top: 10px;">
                                    <tr>
                                        <td colspan="2" style="text-align: center;color:red;">
                                            <s:fielderror fieldName="login"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 90px; text-align: right; font-size: 14px;">用户名:</td>
                                        <td>
                                            <input type="text" name="userNum" id="userNameLogin" class="inpu" width="168px"  />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right; font-size: 14px;" class="auto-style1">密&nbsp;&nbsp; 码:</td>
                                        <td class="auto-style2">
                                            <input type="password" name="password" id="userPassword" class="inpu"  width="168px"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <td style="text-align: right; font-size: 14px;" class="auto-style1">验证码:</td>
                                        <td>
                                            <input name="randomCode" id="code" class="inpu" width="50px" style="width:70px;" />
    										<img src="rand.action" onclick="changeValidateCode(this)" title="点击图片刷新验证码" style="cursor:pointer;" height="24px" width="74px"/>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td colspan="2" style="text-align: left; font-size: 14px; padding-left:80px" >
                                        	<input id="manager" type="radio" value="管理员" name="userType" checked="checked" />
                                        	<label for="manager">管理员</label>
                                        	
                                        	<input id="student" type="radio" value="学生" name="userType" />
    										<label for="student">学生</label>
                                        	<br/>
                                        	<input id="responsible" type="radio" value="负责人" name="userType" />
        									<label for="responsible">负责人</label>
    								
        									<input id="teacher" type="radio" value="老师" name="userType" />
                                        	<label for="teacher">老师</label>
                                     </td>
                                    </tr>
                                    <tr>
                                        <td>
                                        </td>
                                        <td>
                                        	<input type="submit" id= "submitForm" class="ftn" value="登录" />
                                        </td>
                                    </tr>
                                    <%--<tr>
                                       <td>&nbsp;</td>
                                   </tr>
                                    <tr>
                                        <td style="font-size: 14px; text-align: right; vertical-align: bottom;">&nbsp;</td>
                                        <td align="right">
                                            <a href='firmRegistration.aspx'><span style="text-decoration: underline;  font-family: 'Arial Black', Gadget, sans-serif; color: blue">企业注册</span></a>
                                            <a href='eduRegistration.aspx'><span style="text-decoration: underline;  font-family: 'Arial Black', Gadget, sans-serif; color: blue">教育机构注册</span></a></td>
                                    </tr>--%>
                                    <tr>
                                        <td class="third-part" colspan="2" style="text-align: center">
                                            <fieldset style="width: 92%; text-align: left; line-height: 23px; padding-left: 5px;">
                                                <legend>第三方登录</legend>
                                                
                                                <a style="cursor:pointer;" onclick="return window.open('https://graph.qq.com/oauth2.0/authorize?client_id=101304940&response_type=token&scope=all&redirect_uri=http%3A%2F%2Fwww.soeasystudy.com%2Fback.jsp', 'oauth2Login_10235' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes');" id="qqAuthorizationUrl" class="qq"></a>
                                               
                                                <a style="cursor:pointer;" onclick="return window.open('https://api.weibo.com/2/oauth2/authorize?client_id=2840992581&response_type=token&display=js&transport=html5&referer=http://www.soeasystudy.com/test.jsp" class="sina"></a>
											<div id="wb_connect_btn"></div>
                                            </fieldset>
                                        </td>
                                    </tr>
                                    
                                </table>
                    <%--登录表单结束--%>
                </td>
                <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_center_right.jpg'); width: 121px; height: 299px;"></td>
                </tr>
                        <tr>
                            <td colspan="3" style="background-image: url('${pageContext.request.contextPath}/style/login/login_copyright_left.jpg'); width: 605px; height: 59px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_copyright.jpg'); width: 264px; height: 59px;"></td>
                            <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_copyright_right.jpg'); width: 121px; height: 59px;"></td>
                        </tr>
                <tr>
                    <td colspan="5" style="background-image: url('${pageContext.request.contextPath}/style/login/login_bottom.jpg'); width: 990px; height: 44px; background-repeat: repeat-y"></td>
                </tr>
                </table>
                </td>
                <td style="background-image: url('${pageContext.request.contextPath}/style/login/login_right.jpg'); background-repeat: repeat-x; width: 25%"></td>
            </tr>
        </table>
    </form>

</body>
</html>
