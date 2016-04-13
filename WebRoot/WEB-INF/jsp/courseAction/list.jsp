<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>课程信息列表</title>
	<%@include file="/WEB-INF/jsp/public/commons.jspf" %>

  </head>
  
  <body>
 <s:a action="course_addUI">添加课程</s:a>
  <br/>
    	<table>

		<!-- 表头-->
		<thead>
			<tr>
				<td>序号</td>
				<td>课程名</td>
				<td>课程级别</td>
				<td>课程介绍</td>
				<td>相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>

			<s:iterator value="#courseList" status="s">
				<tr>
					<td>${s.count}&nbsp;</td>
					<td>${courseName}&nbsp;</td>
					<td>${courseLevel}&nbsp;</td>
					<td>${description}&nbsp;</td>
					<td>
						<s:a action="course_delete?id=%{id}"
							onclick="return confirm('确定要删除吗？')">删除</s:a>
					 <s:a action="course_editUI?id=%{id}">修改</s:a>
					</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
  </body>
</html>
