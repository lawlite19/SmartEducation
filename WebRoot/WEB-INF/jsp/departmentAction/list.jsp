<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>部门列表</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
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
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 用户管理 &gt; 学生管理</div>
<!-- 引入选择按钮 -->
<%@include file="/WEB-INF/jsp/public/list_button.jspf" %>
<!-- 添加和返回 -->
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="sdepartment_addUI?parentId=%{parentId}" style="color:white;text-decoration: none;">增加部门</s:a>
		</td>
		<td class="bbtn btn-primary" align="center">
			<s:a action="department_list?parentId=%{#parent.parent.id}" style="color:white;text-decoration: none;">返回上级</s:a>
		</td>
	</tr>
</table>
 <div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">用户账号管理</span>
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
				<td align="center">选择</td>
				<td align="center">序号</td>
				<td align="center">部门名称</td>
				<td align="center">上级部门名称</td>
				<td align="center">部门级别</td>
				<td align="center">职能说明</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
		<s:iterator value="#departmentList" status="s">
			<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
				<td align="center">
					<input type="checkbox" name="checkbox" class="checkbox" value="${id},${deptName}" />
				&nbsp;
				</td>
				<td align="center">${s.count}&nbsp;</td>
				<td align="center"><s:a action="department_list?parentId=%{id}">${deptName}</s:a>&nbsp;</td>
				<td align="center">${parent.deptName}&nbsp;</td>
				<td align="center">${deptLevel}&nbsp;</td>
				<td align="center">${deptDescription}&nbsp;</td>
				<td align="center">
					<s:a action="department_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
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
	</div>

</body>
</html>
