<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>智慧教育后台登录界面</title>
  </head>
  <body>
   <form action="user_login.action">
    用户名：<input type="text" name="user.userName" id="userNameLogin"  />
    <br/>
    密码：<input type="password" name="user.password" id="userPassword"/>
    <br/>
    <input type="submit" id= "submitForm" value="登录"/>
    </form>
  </body>
</html>
