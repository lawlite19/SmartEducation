<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有课程资源</title>
<%@include file="/WEB-INF/jsp/public/commons.jspf"%>
<!--Layer插件弹出对话框前台代码开始-->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
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
               	    		url: "spiderCourse_bulkDelete.action", 
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
<div>
<s:form action="spiderCourse_list" method="post">

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
				<td>选择</td>
				<td>序号</td>
				<td>课程名字</td>
				<td>课程地址</td>
				<td>课程类型</td>
				<td>课程信息</td>
				<td>相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr class="TableDetail1 template">
					<td>
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${name}" />
					</td>
					<td>${(currentPage-1)*10+s.count}</td>
					<td>${name}&nbsp;</td>
					<td>
					<a onclick="javascript:load('${url}');return false;" href="#">${url}</a>
					&nbsp;
					</td>
					<td>${professionType}&nbsp;</td>
					<td>${info}&nbsp;</td>
					
					<!--<td>${deptDescription}&nbsp;</td>-->
					<td><s:a action="spiderCourse_delete?id=%{id}"
							onclick="return window.confirm('您确定要删除吗？')">删除</s:a>
						&nbsp;</td>
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
	<script type="text/javascript">
	//iframe窗
		function load(url){
			layer.open({
			      type: 2,
			      title: '详细信息',
			      shadeClose: true,
			      shade: false,
			      maxmin: true, //开启最大化最小化按钮
			      area: ['893px', '600px'],
			      content: url
			    });
		}
</script>
</body>
</html>
