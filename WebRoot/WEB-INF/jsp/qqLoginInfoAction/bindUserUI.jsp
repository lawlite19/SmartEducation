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
                        <span class="tt">用户绑定</span>
                    </td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="qqLoginInfo_bindUser.action" method="post">
<s:hidden name="openId"></s:hidden>
	<table class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<tbody>
		<tr>
			<td colspan="2" class="addFont">
				<font color="red"><s:fielderror fieldName="bindInfo"/></font>
			</td>
		</tr>
		<tr>
			<td class="addFont">用户名</td>
			<td>
				<s:textfield cssClass="inpu" name="userNum" ></s:textfield>
				<span class="span_note">*</span>
			</td>
		</tr>
		<tr>
			<td class="addFont">密&nbsp;码</td>
			<td>
				<s:textfield cssClass="inpu" name="password"></s:textfield>
			</td>
		</tr>
		<tr>
			<td><input id="manager" type="radio" value="管理员" name="userType" checked="checked" />
			    	<label for="manager">管理员</label>
				<input id="student" type="radio" value="学生" name="userType" />
					<label for="student">学生</label> <br />
				<input id="responsible" type="radio" value="负责人" name="userType" />
					<label for="responsible">负责人</label> 
				<input id="teacher" type="radio" value="老师" name="userType" /> 
					<label for="teacher">老师</label></td>
			<td></td>
		</tr>
		</tbody>
	</table>
	</s:form>
	</div>
</td>
</tr>
</tbody>
</table>
	</div>
	
</body>
</html>
