<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>保存或修改数据字典</title>
   	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
	<!-- 编辑区域 -->
	系统字典管理
	<br />
	<s:form action="dataDict_%{id == null ? 'add' : 'edit'}" method="post">
	<s:hidden name="id"></s:hidden>
	数据项类型（*）：
	<s:select id="dataTypeId" name="dataTypeId" cssClass="SelectStyle" list="#dataTypeList"
			listKey="id" listValue="dataTypeName" />
	数据项编号（*）：
	<s:textfield name="dictNum"></s:textfield>
		<br />
	数据项名称（*）：
	<s:textfield name="dictName"></s:textfield>
	数据项描述：
	<s:textfield name="description"></s:textfield>
		<br />
		<s:if test="%{id==null}">
			<s:submit value="添加"></s:submit>
		</s:if>
		<s:else>
			<s:submit value="修改"></s:submit>	
		</s:else>
	</s:form>
</body>
</html>
