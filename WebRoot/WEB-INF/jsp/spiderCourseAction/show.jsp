<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有课程资源</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/c.css" />
<!--Layer插件弹出对话框前台代码开始-->
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<style>
.main{
	margin-bottom:0;
	position:relative;
}

.indexBox .picAreaDiv{
	width:auto;
}

.indexBox{
	margin:0 auto !important;
	position:relative;	
}

.category{
	width:95px;
	text-align:right;
	margin-top:65px;	
	font-size:14px;
	float:left;
	position:absolute;
	top:0;
	left:0;
}

.category li{
	height:30px;
	line-height:30px;
	padding-right:30px;
}

.category li.current{
	position:relative;
}

.arrow{
	background:url(../style/images/l_icons.png) left no-repeat;
	width:13px;
	height:19px;
	position:absolute;
	right:-13px;
	top:5px;
}

.category li.current a{	
	color:#2674ff
}

.picAreaDiv{
	min-height:490px;
	margin-left:95px;
	border-left:#ddd 1px solid;
	padding-top:25px;
	padding-left:45px;
	padding-bottom:105px;
	background:#fff;
	position:relative;
}

.indexBox .picAreaDiv ul li{
	
}

.label {
	margin-bottom:15px;
	background:url(../style/images/l_icons.png) -13px 0 no-repeat;
	padding-left:20px;
	margin-left:-5px;
}
.page{position:absolute;left:50%;padding-top:30px;}
.page a{display:inline-block;padding:0 5px;height:25px;line-height:25px;border:1px solid #ddd;margin-right:5px;color:#555555;}
.page em{list-style:none;display:inline-block;padding:0 5px;height:25px;line-height:25px;margin-right:5px;border:1px solid #898a8a;background:#898a8a;color:#fff;}
</style>
</head>

<body>
<div class="indexBox">
		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label">经济学</div>
<!-- 课程信息 -->
			<ul style="overflow:hidden;">
			<s:iterator value="recordList" status="s">
				<li class="ans-slow-anim">
					<div class="picArea ans-slow-anim">
						<a href="spiderCourse_xxx?id=${id}"
							target="_blank"> 
							<img
							src="${imgUrl}" width="178"
							height="109"></a>
					</div>
					<div class="introArea">
						<a href="spiderCourse_xxx?id=${id}"
							target="_blank" title="${name}">${name}</a>
					</div>
					<div class="introArea2" title="${info}">${info}</div>
				</li>
			</s:iterator>
			</ul>
<!-- 分页信息 -->
<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>
	
		</div>
<!-- 课程类型 -->
		<ul class="category">
			<s:iterator value="professionTypeList" var="num">
				<s:if test="num == id">
					<li  class="current">
						<a href="spiderCourse_show?professionId=${id}">${name}</a>
					</li>
					<div class="arrow"></div>
				</s:if>
				<s:else>
					<li>
						<a href="spiderCourse_show.action?professionId=${id}">${name}</a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</body>
</html>
