<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有用户</title>
    <%@include file="/WEB-INF/jsp/public/commons.jspf" %>
  </head>
  <body>
  <!--  
	<c:forEach items="${users}" var="ur" varStatus="st">
      ${ur.userNum}
      ${ur.password}
      <br/>
    </c:forEach>-->
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">部门名称</td>
            	<td width="150px">用户名</td>
				<td width="150px">账号</td>
				<td width="150px">角色</td>
				<td width="150px">账户状态</td>
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userDetailsList">
        
           <s:iterator value="#userDetailsList">
			<tr class="TableDetail1 template">
				<!-- <td>${department.deptName}&nbsp;</td>-->
			    <td>${userName}&nbsp;</td>
				<td>${userNum}&nbsp;</td>
				<td>${user.userNum}&nbsp;</td>
				<td>  
					<c:if test="${user.isUsable=='1'}">
						正在用
					</c:if>
					<c:if test="${user.isUsable!='1'}">
						已禁用 
					</c:if>
				</td>
				<!--<td>${deptDescription}&nbsp;</td>-->
				<td>
					<s:a action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
					<s:a action="department_editUI?id=%{id}">
						修改
					</s:a>
					<s:a action="department_editUI?id=%{id}">
						初始化密码
					</s:a>
					&nbsp;
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
 
  </body>
</html>
