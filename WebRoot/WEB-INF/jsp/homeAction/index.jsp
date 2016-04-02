<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
</head>
<frameset rows="102,*" frameborder="0">
	<frame scrolling="no" marginwidth="0" marginheight="0" src="${pageContext.request.contextPath}/home_top.action"  id="frm_top"/>
<frameset id="frame" frameborder="0" cols="150,8,*">
	<frame name="left" scrolling="auto" marginwidth="0" marginheight="0" src="${pageContext.request.contextPath}/home_left.action" noresize="noresize"/>
	<frame name="leftSwitch" scrolling="no" marginwidth="0" marginheight="0" src="${pageContext.request.contextPath}/home_switchLeft.action" noresize="noresize"/>
<frameset id="fms2" frameborder="0" rows="*,8,0">                     
	<frame name="right" scrolling="auto" src="${pageContext.request.contextPath}/home_right.action" />
	<frame name="topSwitch" scrolling="no" marginwidth="0" marginheight="0" src="${pageContext.request.contextPath}/home_switchTop.action" noresize="noresize"/>
	<frame name="bottom" scrolling="auto" src="about:blank"/>
</frameset>
</frameset>
</html>
