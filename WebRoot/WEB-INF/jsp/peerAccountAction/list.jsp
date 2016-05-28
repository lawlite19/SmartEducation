<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title></title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
	<script>
	$(function() {
             $("#deleteSelected").click(function(){
            	 $("input:checked").each(function(){
            	 	 var value=$(this).val().split(",");
           	    	 if (confirm("确定要删除"+value[1]+"吗?")) {
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "department_bulkDelete.action", 
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
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学质量管理 &gt; 同行评价指标管理</div>
<!-- 引入选择按钮 -->
<%@include file="/WEB-INF/jsp/public/list_button.jspf" %>

<!-- 添加和返回 -->
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="peerAccount_addUI?parentId=%{parentId}" style="color:white;text-decoration: none;">增加指标</s:a>
		</td>
		<td class="bbtn btn-primary" align="center">
			<s:a action="peerAccount_list?parentId=%{#parent.parent.id}" style="color:white;text-decoration: none;">返回上级</s:a>
		</td>
	</tr>
</table>
 <div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">同行评价管理</span>
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
				<td align="center">序号</td>
				<td align="center">评价描述</td>
				<td align="center">评价占分</td>
				<td align="center">学期</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="peerAccountList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${description}" />
					</td>
					<td align="center">${(currentPage-1)*10+s.count}</td>
					<td align="center">${description}&nbsp;</td>
					<td align="center" >${points}&nbsp;</td>
					<td align="center" >${ATerm.name}&nbsp;</td>
					<td align="center">
					<s:a action="peerAccount_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="peerAccount_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
					&nbsp;
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
</td>
</tr>
</tbody>
</table>
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
	</div>
</body>
</html>
