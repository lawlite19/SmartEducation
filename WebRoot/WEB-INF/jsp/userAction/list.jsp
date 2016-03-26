<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.hhit.entity.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有用户</title>
  </head>
  <body>
	<c:forEach items="${users}" var="ur" varStatus="st">
      ${ur.userNum}
      ${ur.password}
      <br/>
    </c:forEach>
  </body>
</html>
