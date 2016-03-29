<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码界面</title>
    <%@include file="/WEB-INF/jsp/public/commons.jspf" %>
  </head>
  
  <body>

  <s:form action="user_modifyPassword" method="post"> 
    	<font color="red"><s:fielderror fieldName="information"  /></font>
    	原始密码：<input name="oldPassword"  /><br/>
   		 新密码：<input name="newPassword" /><br/>
   		 再次输入：<input name="confirmPassword"/><br/>
    	<input type="submit" value="确认"/>
    </s:form>
  </body>
</html>
