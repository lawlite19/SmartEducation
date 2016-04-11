<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人信息维护</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>

<body>
	<s:form action="userDetails_personalMaintain" method="post">
    性别：
   	<s:radio name="sex" list="#{'男':'男','女':'女'}"/>
   	<br/>
    邮箱：<s:textfield name="email"></s:textfield>
    <br/>
    生日：<s:textfield name="birthday"></s:textfield>
    <br/>
    手机号：<s:textfield name="telphone"></s:textfield>
    <br/>
    qq号：<s:textfield name="qqNum"></s:textfield>
    <br/>
    微信号：<s:textfield name="weChatNum"></s:textfield>
    <br/>
    备注：<s:textarea name="otherInfo"></s:textarea>
    <br/>
		<s:submit value="提交" />
	</s:form>
</body>
</html>
