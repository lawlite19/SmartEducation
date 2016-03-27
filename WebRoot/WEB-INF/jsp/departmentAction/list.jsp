<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>部门列表</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
     
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">部门编号</td>
            	<td width="150px">部门名称</td>
				<td width="150px">上级部门名称</td>
				<td width="150px">部门级别</td>
				<td width="150px">是否可用</td>
				<td width="200px">职能说明</td>
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        
        <s:iterator value="#departmentList">
			<tr class="TableDetail1 template">
				<td>${deptNum}&nbsp;</td>
				<td><s:a action="department_list?parentId=%{id}">${deptName}</s:a>&nbsp;</td>
				<td>${parent.deptName}&nbsp;</td>
				<td>${deptLevel}&nbsp;</td>
				<td>
					<c:if test="${isUsable==1}">
						是
					</c:if>
					<c:if test="${isUsable!=1}">
						否
					</c:if>
				</td>
				<td>${deptDescription}&nbsp;</td>
				<td>
					<s:a action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
					<s:a action="department_editUI?id=%{id}">
						修改
					</s:a>
					&nbsp;
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="department_addUI?parentId=%{parentId}">增加部门</s:a>
			<s:a action="department_list?parentId=%{#parent.parent.id}">返回上级</s:a>
        </div>
    </div>
</div>
</body>
</html>
