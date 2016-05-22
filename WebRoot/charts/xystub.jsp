<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
  	<%@include file="/WEB-INF/jsp/public/list.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的在线时长详细信息</title>
  </head>
  
  <body>
<div align="center">
<hr style="border:solid 1px #000080" />
<div style="font-size:16pt;">
<b>详情信息:</b><br />
<ul style="width:250px">
    <!--  <li>在线信息 : <%=request.getParameter("dataSetName")%></li>-->
    <li>点击的位置 : <%=request.getParameter("x")+1%></li>
    <li>日期 : <%=request.getParameter("xLabel")%></li>
    <li>浏览时长 : <%=request.getParameter("value")%></li>
</ul>
</div>
</div>
</body>
</html>
