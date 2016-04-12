<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>布置作业管理</title>
	<%@include file="/WEB-INF/jsp/public/commons.jspf" %>
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
  <s:form action="" method="post">
  课程：<s:select name="courseId" cssClass="SelectStyle"
			list="#courseList" listKey="id" listValue="courseName" headerKey=""
			headerValue="==请选择课程==" />
		
	</s:form>
    <div>
<s:a href="task_assignTaskUI">布置作业</s:a>
<input type="button" id="selectAll" value="全选" />
<input type="button" id="selectNone" value="全不选" />
<input type="button" id="selectOthers" value="反选" />
<input type="button" id="deleteSelected" value="删除" />
</div>

<!-- 显示区域 -->
		<table>
		<!-- 表头-->
		<thead>
			<tr>
				<td>选择</td>
				<td>序号</td>
				<td>课程</td>
				<td>作业名称</td>
				<td>发布时间</td>
				<td>结束时间</td>
				<td>说明</td>
				<td>相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr>
					<td>
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${taskName}" />
					</td>
					<td>${(currentPage-1)*10+s.count}</td>
					<td>${taskName}&nbsp;</td>
					<td>${releaseTime}&nbsp;</td>
					<td>${endDate}&nbsp;</td>
					<td>${description}</td>
					
					<td><s:a action="task_delete?id=%{id}"
							onclick="return window.confirm('您确定要删除吗？')">删除</s:a>
					<s:a action="task_editUI?id=%{id}">
						修改
					</s:a></td>
				</tr>
			</s:iterator>

		</tbody>
	</table>

	<!-- 分页页码 -->
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
  </body>
</html>
