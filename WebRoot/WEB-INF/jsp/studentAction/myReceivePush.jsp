<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的推送内容</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!--鼠标悬浮变色相关代码开始-->
    <script type="text/javascript">
        var _oldColor;
        function SetNewColor(source) {
            _oldColor = source.style.backgroundColor;
            source.style.backgroundColor = '#E8F5FE';
        }
        function SetOldColor(source) {
            source.style.backgroundColor = _oldColor;
        }
    </script>
<!--鼠标悬浮变色相关代码结束-->
</head>
<body>
<div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">
		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label" style="margin: 0;">
				<span id="span_courseName">${courseName}</span>
<!-- 添加教学进程 -->
<!-- 添加按钮 -->
<s:form action="student_myReceivePush" method="post">
<s:hidden name="courseId"></s:hidden>
<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td align="center">
		</td>		
		<td align="center">
		</td>
		<td  align="center">
			<s:submit value="提交" cssClass="ttn"></s:submit>
		</td>
	</tr>
</table>
</s:form>
			</div>

<!-- 教学进程信息 -->
			<ul style="overflow:hidden;">
			<div class="mframe">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                    <span class="tt">教学进程信息</span></td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
                   
	<table class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<!-- 表头-->
		<thead>
			<tr style="height:30px;">
				<th align="center">章节编号</th>
				<th align="center">教学章节</th>
				<th align="center">课程名</th>
				<th align="center">操作</th>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="chapterList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);" style="height:30px;">
					<td align="center">
						${chapterNum}
					&nbsp;
					</td>
					<td align="center">
						${chapterName}
					&nbsp;
					</td>
					
					<td align="center">${courseName}&nbsp;</td>
				</tr>
				<s:iterator value="children">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);"  style="height:30px;">
					<td align="center">
						${chapterNum}
					&nbsp;
					</td>
					<td align="center">
						<a href="${url}" target="_blank" >${chapterName}</a>
					&nbsp;
					</td>
					
					<td align="center">${courseName}&nbsp;</td>
					<td align="center">
						<a href="${url}" target="_blank">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/see.png"  />
					</a>
					&nbsp;</td>

				</tr>
			</s:iterator>
		</s:iterator>
		</tbody>
	</table>
	</div>
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
						<a href="student_myReceivePush.action?courseId=${course.id}&teacherNum=${teacherNum}">${course.courseName}</a>
					</li>
					<div class="arrow" style="right: 7px;"></div>
				</s:if>
				<s:else>
					<li>
						<a href="student_myReceivePush.action?courseId=${course.id}&teacherNum=${teacherNum}">${course.courseName}</a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</div>
</body>
</html>
