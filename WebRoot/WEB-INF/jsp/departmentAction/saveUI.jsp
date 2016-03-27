<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>部门设置</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
<!--显示表单内容-->
    <s:form action="department_%{id == null ? 'add' : 'edit'}">
        <s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">上级部门</td>
                        <td>
                        	<s:select name="parentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="deptName"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>
                        </td>
                    </tr>
                    <tr><td>部门名称</td>
                        <td><s:textfield  name="deptName" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr><td>部门编号</td>
                        <td><s:textfield  name="deptNum" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr><td>部门级别</td>
                        <td><s:textfield  name="deptLevel" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr><td>职能说明</td>
                        <td><s:textarea name="deptDescription" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="submit" value="提交"/>
            <a href="javascript:history.go(-1);">返回</a>
        </div>
    </s:form>
</body>
</html>
