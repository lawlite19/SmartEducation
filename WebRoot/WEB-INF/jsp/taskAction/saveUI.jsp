<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>教师布置作业界面</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>

<body>
	<s:form action="task_%{id==null ? 'add' : 'edit'}" method="post">
	<s:hidden name="id"></s:hidden>
	课程：<s:select name="courseId" cssClass="SelectStyle"
			list="#courseList" listKey="id" listValue="courseName" headerKey=""
			headerValue="==请选择课程==" />
		<br/>
	专业：
	<br />
    作业名称：<s:textfield name="taskName"></s:textfield>
		<br />
    作业结束时间：<s:textfield name="endDate"></s:textfield>
		<br />
    作业描述：<s:textarea name="description"></s:textarea>
		<br />
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>
