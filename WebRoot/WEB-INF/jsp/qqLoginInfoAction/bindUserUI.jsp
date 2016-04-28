<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>绑定用户界面</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
</head>

<body>
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
		<tbody>
		<tr>
		</tr>
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

	<font color="red"><s:fielderror fieldName="bindInfo"/></font>
	<form action="qqLoginInfo_bindUser.action" method="post">
	<s:hidden name="openId"></s:hidden>
		用户名<input name="userNum" /><br/>
		密码<input name="password" /><br/>
    <input type="radio" value="管理员" name="userType" checked="checked" />管理员
    <br/>
    <input type="radio" value="负责人" name="userType" />负责人
    
    <br/>
        <input type="radio" value="学生" name="userType" />学生
    <br/>
        <input type="radio" value="老师" name="userType" />老师
    <br/>
		<input type="submit" value="提交" />
	</form>

</body>
</html>
