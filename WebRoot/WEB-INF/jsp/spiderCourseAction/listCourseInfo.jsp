<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程介绍</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<script>
	$(function() {
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
               	    		url: "spiderCourse_chapterBulkDelete.action", 
               	    		data: {
               	    			"chapterId" : value[0]
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
        	   //window.location.reload();
      });

	});

</script>
</head>
<body>
<s:form action="spiderCourse_list" method="post">

</s:form>

<h1>章节</h1>
<!-- 引入选择按钮 -->
<%@include file="/WEB-INF/jsp/public/list_button.jspf" %>
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">课程章节</span>
                    </td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
	<table  class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<!-- 表头-->
		<thead>
			<tr>
				<td align="center">选择</td>
				<td align="center">序号</td>
				<td align="center">章节编号</td>
				<td align="center">章节名字</td>
				<td align="center">对应课程</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="spiderChapterList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${chapterName}" />
					</td>
					<td align="center">${s.count}</td>
					<td align="center">
						${chapterNum}
					&nbsp;
					</td>
					<td align="center">
						<a href="${url}" target="_blank" >${chapterName}</a>
					&nbsp;
					</td>
					
					<td align="center">${courseName}&nbsp;</td>

					<td align="center">
						<s:a action="spiderCourse_deleteChapter?chapterId=%{id}&courseId=%{courseId}" onclick="return window.confirm('您确定要删除吗？')">
							<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
						</s:a>
						&nbsp;</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</div>
	
	
	<h1>参考教材</h1>
	<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">参考教材</span>
                    </td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<table   class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<!-- 表头-->
		<thead>
			<tr>
				<td align="center">选择</td>
				<td align="center">序号</td>
				<td align="center">封面</td>
				<td align="center">教材名</td>
				<td align="center">教材作者</td>
				<td align="center">出版社</td>
				<td align="center">出版日期</td>
				<td align="center">操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="spiderDocumentList" status="s">
				<tr class="TableDetail1 template">
					<td align="center">
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${name}" />
					</td>
					<td align="center">${s.count}</td>
					<td align="center">
						<img alt="" src="${imgUrl}"/>
					</td>
					<td align="center">
						<a href="${url}" target="_blank">${name}</a>
					&nbsp;
					</td>
					
					<td align="center">
						${author}
					&nbsp;
					</td>
					<td align="center">${publisher}&nbsp;</td>
					<td align="center">${publishDate}&nbsp;</td>
					<td align="center">
						<s:a action="spiderCourse_deleteDocument?documentId=%{id}&courseId=%{courseId}" onclick="return window.confirm('您确定要删除吗？')">
							<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
						</s:a>
						&nbsp;</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</div>
	
</body>
</html>
