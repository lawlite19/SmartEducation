<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有用户</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/ckform.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/commons.js"></script>
<script>
	$(function() {
             $("#selectAll").click(function(){
            	 $(":checkbox").each(function(){
           		    this.checked=true;
           	   });
             });
             $("#selectNone").click(function(){
            	 $(":checkbox").each(function(){
           		    this.checked=false;
           	   });
           });
             $("#selectOthers").click(function(){
          	     $(":checkbox").each(function(){
          		    this.checked=!this.checked;
          	   });
           });
             $("#deleteSelected").click(function(){
            	 $("input:checked").each(function(){
            	 	 var value=$(this).val().split(",");
           	    	 //alert(value[0]);
           	    	 //alert(value[1]);
           	    	 if (confirm("确定要删除"+value[1]+"吗?")) {
           	    		//if(value[1]=="admin"){
           				//	alert("该用户为超级管理员,不能删除！");
           				//	return;
           				//}
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "userDetails_bulkDelete.action", 
               	    		data: {
               	    			"id" : value[0]
           	    			}, 
               	    		dataType : "json",
               	    		async : false,
                            success: function(data) { 
                            	 var json = eval("(" + data + ")");
								 var str = json.name;
   								  if (str=="ok") {
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
<div>
<s:form action="userDetails_list" method="post">
	按部门：
	<s:select name="departmentId" cssClass="SelectStyle" list="#departmentList"
		listKey="id" listValue="deptName" headerKey="" headerValue="==请选择部门==" />
	按角色：
	<s:select name="roleId" cssClass="SelectStyle" list="#roleList"
		listKey="id" listValue="roleName" headerKey="" headerValue="==请选择角色==" />
	<s:select name="viewType" list="#{0:'姓名', 1:'账号'}"/>
	<s:textfield name="inputTerm"></s:textfield>
	<input type="submit" value="查询" >
</s:form>
</div>
<div>
<input type="button" id="selectAll" value="全选" />
<input type="button" id="selectNone" value="全不选" />
<input type="button" id="selectOthers" value="反选" />
<input type="button" id="deleteSelected" value="删除" />
</div>
	<table>
		<!-- 表头-->
		<thead>
			<tr>
				<td width="150px">部门名称</td>
				<td width="150px">姓名</td>
				<td width="150px">账号</td>
				<td width="150px">角色</td>
				<td width="150px">账户状态</td>
				<td width="150px">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td>
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${userName}" />
					</td>
					<td>${department.deptName}&nbsp;</td>
					<td>${userName}&nbsp;</td>
					<td>${userNum}&nbsp;</td>
					<td><s:iterator value="roles">
                		${roleName}
                	</s:iterator></td>
					<td><s:if test="%{user.isUsable==1}">
						正在用
					</s:if> <s:if test="%{user.isUsable!=1}">
							<font color="red">已禁用 </font>
						</s:if></td>
					<!--<td>${deptDescription}&nbsp;</td>-->
					<td><s:a action="userDetails_delete?id=%{id}"
							onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
						<s:if test="%{user.isUsable==1}">
							<s:a action="userDetails_stopUser?id=%{id}">禁用</s:a>
						</s:if> <s:else>
							<s:a action="userDetails_enableUser?id=%{id}">启用</s:a>
						</s:else> <s:a action="userDetails_editUI?id=%{id}">
						修改
					</s:a> <s:a action="userDetails_initPassword?id=%{id}">
						初始化密码
					</s:a> &nbsp;</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>

	<!-- 分页页码 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
	

	<!-- 其他功能超链接 -->
	<div id="TableTail">
		<div id="TableTail_inside">
			<s:a action="userDetails_addUI">添加用户</s:a>
		</div>
	</div>
</body>
</html>
