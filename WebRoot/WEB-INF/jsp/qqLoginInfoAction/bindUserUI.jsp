<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>绑定用户界面</title>
</head>

<body>
	<font color="red"><s:fielderror fieldName="bindInfo"/></font>
	<form action="qqLoginInfo_bindUser.action" method="post">
	<s:hidden name="openId"></s:hidden>
		用户名<input name="userNum" /><br/>
		密码<input name="password" /><br/>
    <input type="radio" value="管理员" name="userType" checked="checked" />管理员
    <br/>
    <input type="radio" value="负责人" name="userType" />负责人
    
    <br/>
        <input type="radio" value="学生" name="userType" />学生
    <br/>
        <input type="radio" value="老师" name="userType" />老师
    <br/>
		<input type="submit" value="提交" />
	</form>

</body>
</html>
