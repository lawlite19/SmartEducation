<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的课程测试分数</title>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
	<!--Layer插件弹出对话框-->
	<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
  </head>
  
  <body>
  <div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">
		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label" style="margin: 0;">
				<span id="span_courseName">${courseName}</span>
			</div>

<!-- 教学进程信息 -->
			<ul style="overflow:hidden;">
			<div class="mframe">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                    <span class="tt">测试成绩信息</span></td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                   
				<s:if test="courseId!=null">
					<hr color="#000080">
					<img src='${pageContext.request.contextPath}/charts/getchart.jsp?${chart1URL}'
    					usemap="#map1" border="0">
						<map name="map1">${imageMap1}</map>
				</s:if>
</td>
</tr>
</tbody>
</table>
</div>
			</ul>
		</div>
<!-- 我的课程信息 -->
		<ul class="category" style="width:115px;">
			<s:iterator value="classSelectCourseList" var="num">
				<s:if test="%{courseId==course.id}">
					<li  class="current">
						<a href="stuScoreRecord_stuCourseScoreInfo.action?courseId=${course.id}">${course.courseName}</a>
					</li>
					<div class="arrow" style="right: 7px;"></div>
				</s:if>
				<s:else>
					<li>
						<a href="stuScoreRecord_stuCourseScoreInfo.action?courseId=${course.id}">${course.courseName}</a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>

</div>
</div>
  
</body>
</html>

