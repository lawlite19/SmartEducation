<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>right</title>
    <%@include file="/WEB-INF/jsp/public/list.jspf"%>
  </head>
<body>
<s:iterator value="#session.user">
<s:property value="userNum"/>
<br/>

</s:iterator>

</body>
</html>
