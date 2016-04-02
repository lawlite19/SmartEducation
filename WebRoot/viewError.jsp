<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>出错啦</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="表白墙" content="说出你的爱">
	<link href="${pageContext.request.contextPath}/style/images/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
	<!-- Bootstrap -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bootstrap.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
  </head>
  	<style>
  		body{
  			padding-top:100px;
  			background:#F3F9FD;
  		}
  	</style>
  <body>
    <div class="container">
    	<div class="row">
  			<div class="col-sm-4">	
  			</div>
  			<div class="col-sm-6">
  			<h4>页面出错啦！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您可以<a href="${pageContext.request.contextPath}/index.jsp">返回首页</a></h4>
  			</div>
  			<div class="col-sm-2"></div>
		</div>
    </div>
        <div class="container">
    	<div class="row">
  			<div class="col-sm-4">
  			</div>
  			<div class="col-sm-6">
  				<img alt="出错啦！" src="${pageContext.request.contextPath}/style/images/viewError.jpg" width="300px" height="300px"/>
  			</div>
  			<div class="col-sm-2"></div>
		</div>
    </div>
    
  </body>
</html>
