<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色列表</title>
  </head>
  
  <body>
<table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">角色名称</td>
                <td width="300px">角色说明</td>
                <td width="300px">创建时间</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="#roleList">
			<tr class="TableDetail1 template">
				<td>${roleName}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>${addDate}&nbsp;</td>
				<td>
					<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					<s:a action="role_editUI?id=%{id}">修改</s:a>
					<s:a action="role_setPrivilegeUI?id=%{id}">设置权限</s:a>
				</td>
			</tr>  
        </s:iterator>

        </tbody>
    </table>
  </body>
</html>
