<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>学期列表</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
	<script>
	$(function() {
             $("#deleteSelected").click(function(){
            	 $("input:checked").each(function(){
            	 	 var value=$(this).val().split(",");
           	    	 if (confirm("确定要删除"+value[1]+"吗?")) {
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "mclass_bulkDelete.action", 
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
   		           				  	layer.load();
   		           				 	window.location.reload();
   		           				  } else {
   		           	                 alert("删除失败");  
   		           				}
               	    		} 
               	       }); 
   					}
        	    	 
        	     });
      });

	});

</script>

</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学质量管理 &gt; 基础信息管理管理</div>
<!-- 查找-->
<s:form action="term_list" method="post">
<table width="90%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tr"></td>
                </tr>
                <tr>
                    <td class="mm" colspan="3">
<table align="left" cellspacing="1" cellpadding="1">
   
</table>
</td>
</tr>
</tbody>
</table>

</s:form>
<!-- 引入选择按钮 -->
<%@include file="/WEB-INF/jsp/public/list_button.jspf" %>
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="term_addUI?parentId=%{parentId}" style="color:white;text-decoration: none;">增加学期</s:a>
		</td>
		<td class="bbtn btn-primary" align="center">
			<s:a action="term_list?parentId=%{#parent.parent.id}" style="color:white;text-decoration: none;">返回上级</s:a>
		</td>
	</tr>
</table>
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">基础信息管理</span>
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
				<td align="center">选择</td>
				<td align="center">学期名</td>
				<td align="center">评价开始时间</td>
				<td align="center">评价结束时间</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
		<s:iterator value="#termList" status="s">
			<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
				<td align="center">
					<input type="checkbox" name="checkbox" class="checkbox" value="${id},${name}" />
				&nbsp;
				</td>
				<td align="center">${name}&nbsp;</td>
				<td align="center">${startTime}&nbsp;</td>
				<td align="center">${endTime}&nbsp;</td>
				<td align="center">
					<s:a action="term_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="term_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('您确定要删除吗？')">
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
	<!-- 分页页码 -->


	</div>
</body>
</html>
