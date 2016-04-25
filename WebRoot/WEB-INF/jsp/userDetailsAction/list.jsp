<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有用户</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />

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
                            	 //var json = eval("(" + data + ")");
								 var str = data.name;
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
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 用户管理 &gt; 学生管理</div>

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
<div class="mframe">
	<table width="90%" align="center" cellspacing="0" cellpadding="0">
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
	<table class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<!-- 表头-->
		<thead>
			<tr>
				<td>选择</td>
				<td>序号</td>
				<td>部门名称</td>
				<td>姓名</td>
				<td>账号</td>
				<td>角色</td>
				<td>账户状态</td>
				<td>相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr class="TableDetail1 template">
					<td>
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${userName}" />
					</td>
					<td>${(currentPage-1)*10+s.count}</td>
					<td>${department.deptName}&nbsp;</td>
					<td>${userName}&nbsp;</td>
					<td>${userNum}&nbsp;</td>
					<td><s:iterator value="roles">
                		${roleName}
                	</s:iterator></td>
					<td><s:if test="%{user.isUsable}">
						正在用
					</s:if>
						<s:else>
							<font color="red">已禁用 </font>
						</s:else></td>
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
</td>
</tr>
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
</div>
</body>
</html>
