<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示教学要素</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
</head>
<body>
	<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="teachElement_addUI" style="color:white;text-decoration: none;">添加教学要素</s:a>
		</td>
	</tr>
</table>

<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学质量管理 &gt; 教学要素管理</div>


<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">教学要素管理</span>
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
				<td width="150px">选择</td>
				<td width="150px">数据项类型名称</td>
				<td width="150px">数据项编号</td>
				<td width="150px">数据项名称</td>
				<td width="150px">备注</td>
				<td width="150px">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="teachList">
				<tr>
					<td><input type="checkbox" name="checkbox" class="checkbox"
						value="${id},${dictName}" /></td>
					<td>${dataType.dataTypeName}&nbsp;</td>
					<td>${dictNum}&nbsp;</td>
					<td>${dictName}&nbsp;</td>
					<td>${description}</td>
					<td>	<s:a action="teachElement_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="teachElement_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">
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


</body>
</html>