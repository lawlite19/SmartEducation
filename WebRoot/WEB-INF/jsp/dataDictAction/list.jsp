<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示数据字典</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
<script>
	$(function() {
		$("#selectAll").click(function() {
			$(":checkbox").each(function() {
				this.checked = true;
			});
		});
		$("#selectNone").click(function() {
			$(":checkbox").each(function() {
				this.checked = false;
			});
		});
		$("#selectOthers").click(function() {
			$(":checkbox").each(function() {
				this.checked = !this.checked;
			});
		});
		$("#deleteSelected").click(function() {
			$("input:checked").each(function() {
				var value = $(this).val().split(",");
				//alert(value[0]);
				//alert(value[1]);
				if (confirm("确定要删除" + value[1] + "吗?")) {
					//if(value[1]=="admin"){
					//	alert("该用户为超级管理员,不能删除！");
					//	return;
					//}
					$.ajax({
						type : "post",
						url : "dataDict_bulkDelete.action",
						data : {
							"id" : value[0]
						},
						dataType : "json",
						async : false,
						success : function(data) {
							//var json = eval("(" + data + ")");
							var str = data.name;
							if (str == "ok") {
								alert("删除成功");
								window.location.reload();
							} else {
								alert("删除失败");
							}
						}
					});
				}

			});
			//window.location.reload();
		});

	});
</script>
</head>
<body>
	<!-- 编辑区域 -->
	系统字典管理
	<br />
	<s:form action="dataDict_%{id == null ? 'add' : 'edit'}" method="post">
	<s:hidden name="id"></s:hidden>
	数据项类型（*）：
	<s:select name="dataTypeId" cssClass="SelectStyle" list="#dataTypeList"
			listKey="id" listValue="dataTypeName" headerKey=""
			headerValue="==请选择数据项类型==" />
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

	<!-- 显示区域 -->
	<div>
		<input type="button" id="selectAll" value="全选" /> <input
			type="button" id="selectNone" value="全不选" /> <input type="button"
			id="selectOthers" value="反选" /> <input type="button"
			id="deleteSelected" value="删除" />
	</div>
	<table>
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
			<s:iterator value="recordList">
				<tr>
					<td><input type="checkbox" name="checkbox" class="checkbox"
						value="${id},${dictName}" /></td>
					<td>${dataType.dataTypeName}&nbsp;</td>
					<td>${dictNum}&nbsp;</td>
					<td>${dictName}&nbsp;</td>
					<td>${description}</td>
					<td><s:a action="dataDict_delete?id=%{id}"
							onclick="return window.confirm('您确定要删除吗？')">删除</s:a>
					 <s:a action="dataDict_editUI?id=%{id}">
						修改
					</s:a> &nbsp;</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>

	<!-- 分页页码 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
</body>
</html>
