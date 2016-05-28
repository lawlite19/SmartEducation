<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>课程列表</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 我的评价 &gt; 教学要素评价</div>

<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">教学要素评价</span>
                    </td>
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
			<tr>
				<td align="center">序号</td>
				<td align="center">课程名</td>
				<s:if test="%{usertype == '学生'}">
					<td align="center">教师名</td>
				</s:if>
				<s:else>
					<td align="center">班级名</td>
				</s:else>
				<td align="center">学期</td>
				<td align="center">评价状态</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>
		<!--显示数据列表-->
		<tbody>
			<s:iterator value="teachingList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${(currentPage-1)*10+s.count}</td>
				    <td align="center">${Course.courseName}&nbsp;</td>
					<s:if test="%{usertype == '学生'}">
						<td align="center" >${teacherName}&nbsp;</td>
					</s:if>
					<s:else>
						<td align="center" >${class_.className}&nbsp;</td>
					</s:else>
					<td align="center">${ATerm.name}&nbsp;</td>
					<td align="center">
						<s:if test="%{teachAccess == NULL}">
							否&nbsp;
						</s:if>
						<s:else>
							是&nbsp;
						</s:else>
					</td>
					<td align="center">
					<s:a action="teachaccess_access?id=%{id}" onclik="">
						评价
					</s:a>
					&nbsp;
					</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	</div>
</td>
</tr>
</tbody>
</table>
	<!-- 分页页码 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>

	</div>
</body>
</html>