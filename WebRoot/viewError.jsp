<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出错啦</title>   
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/viewError.css"/>
  </head>
  <body>
	<div class="img-404" id="scene"
		style="transform: translate3d(0px, 0px, 0px); transform-style: preserve-3d; backface-visibility: hidden;">
		<!--  
		<img src="${pageContext.request.contextPath}/style/images/logo.png" class="logo">
		-->
		<img src="${pageContext.request.contextPath}/style/images/feidie.png"
			class="layer feidie" data-depth="0.30"
			style="transform: translate3d(-3.61522840409956px, -4.40744275862069px, 0px); transform-style: preserve-3d; backface-visibility: hidden; position: relative; display: block; left: 0px; top: 0px;">
		<img src="${pageContext.request.contextPath}/style/images/img-404.png"
			class="img404"> <a href="${pageContext.request.contextPath}/home_index.action">返回首页</a>
	</div>
</body>
</html>
