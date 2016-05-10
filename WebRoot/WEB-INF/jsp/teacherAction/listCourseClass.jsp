<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程进程</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<!--Layer插件弹出对话框-->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
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
<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="teacher_addCourseClassUI?courseId=%{courseId}" style="color:white;text-decoration: none;">添加班级</s:a>
		</td>
	</tr>
</table>
			</div>

<!-- 教学进程信息 -->
			<ul style="overflow:hidden;">
			<div class="mframe">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                    <span class="tt">课程对应班级信息</span></td>
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
				<th align="center">序号</th>
				<th align="center">课程名</th>
				<th align="center">班级名</th>
				<th align="center">操作</th>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="classSelectCourseList" status="s">
			<tr style="height:30px;" onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">
						${course.courseName}
					</td>
					<td align="center">${class_.className}&nbsp;</td>
					<td  align="center">
					<s:a action="teacher_deleteCourseClass?classSelectCourseId=%{id}&courseId=%{courseId}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
						&nbsp;</td>
				</tr>
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
			<s:iterator value="couseList" var="num">
				<s:if test="%{courseId==id}">
					<li  class="current">
						<s:a action="teacher_listCourseClass?courseId=%{id}">${courseName}</s:a>
					</li>
					<div class="arrow" style="right: 7px;"></div>
				</s:if>
				<s:else>
					<li>
						<s:a action="teacher_listCourseClass?courseId=%{id}">${courseName}</s:a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</div>
</body>
</html>
