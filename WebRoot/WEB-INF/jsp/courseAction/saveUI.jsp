<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加课程</title>
	<%@include file="/WEB-INF/jsp/public/commons.jspf" %>
  </head>
  <s:form action="course_%{id==null ? 'add' : 'edit'}">
  <s:hidden name="id"></s:hidden>
    课程名：<s:textfield name="courseName"></s:textfield>
    <br/>
    课程级别：<s:textfield name="courseLevel"></s:textfield>
    <br/>
    课程描述：<s:textfield name="description"></s:textfield>
    <br/>
    <s:submit value="提交"></s:submit>
    </s:form>
  </body>
</html>
