<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>角色设置</title>
</head>
<body> 
<!--显示表单内容-->
    <s:form action="role_%{ id == null ? 'add' : 'edit' }">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">角色名称</td>
                        <td><s:textfield name="role.roleName" cssClass="InputStyle" /> *</td>
                    </tr>
                    <tr>
                        <td>角色说明</td>
                        <td><s:textarea name="role.description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
       <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="submit" value="保存" name="btnSave" />
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>

</body>
</html>

