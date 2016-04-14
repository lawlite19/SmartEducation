<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>作业列表</title>
	<%@include file="/WEB-INF/jsp/public/commons.jspf" %>
  </head>
  <s:form action="submitTask_list" method="post">
  课程：<s:select name="courseId" cssClass="SelectStyle"
			list="#courseList" listKey="id" listValue="courseName" headerKey=""
			headerValue="==请选择课程==" />
		<s:submit value="查找"></s:submit>
	</s:form>
  <body>
    <!-- 显示区域 -->
		<table>
		<!-- 表头-->
		<thead>
			<tr>
				<td>序号</td>
				<td>课程</td>
				<td>作业名称</td>
				<td>发布时间</td>
				<td>结束时间</td>
				<td>说明</td>
				<td>相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr>
					<td>${(currentPage-1)*10+s.count}</td>
					<td>${course.courseName}</td>
					<td>${taskName}&nbsp;</td>
					<td>${releaseTime}&nbsp;</td>
					<td>${endDate}&nbsp;</td>
					<td>${description}</td>
					
					<td>
					<s:a action="submitTask_submitTaskUI?id=%{id}">
						提交作业
					</s:a></td>
				</tr>
			</s:iterator>

		</tbody>
	</table>

	<!-- 分页页码 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
  </body>
</html>
