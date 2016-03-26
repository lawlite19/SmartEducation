<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
   <form action="user_login.action" method="post">
    用户名：<input type="text" name="user.userNum" id="userNameLogin"  />
    <br/> 
    密码：<input type="password" name="user.password" id="userPassword"/>
    <br/>
    <input type="radio" value="管理员" name="user.userType" checked="checked" />管理员
    <br/>
    <input type="radio" value="负责人" name="user.userType" />负责人
    
    <br/>
        <input type="radio" value="学生" name="user.userType" />学生
    <br/>
        <input type="radio" value="老师" name="user.userType" />老师
    <br/>
    <input type="submit" id= "submitForm" value="登录"/>
    </form>
  </body>
</html>
