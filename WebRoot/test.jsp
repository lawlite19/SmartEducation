<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:wb="http://open.weibo.com/wb">
  <head>
    <title>测试用</title>
    	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
<!-- 微博js -->
<script type="text/javascript" src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=2840992581"  charset="utf-8"></script>
<script type="text/javascript">
	$document.ready(function(){
		var n=1;
		$("#a").click(function(n){
			n+=2;
			alert(n);
		});
	});
</script>

  </head>
  
  <body>
    <!-- 微博登录开始 -->
	
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
	
	<input type="button" id="a" value="test" />
  </body>
</html>
