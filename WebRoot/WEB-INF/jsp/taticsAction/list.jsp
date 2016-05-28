<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>评价策略列表</title>
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
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学质量管理 &gt; 评价策略管理</div>
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

<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">评价策略历史</span>
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
				<td align="center">学生评价比重</td>
				<td align="center">同行评价比重</td>
				<td align="center">自我评价比重</td>
				<td align="center">教学环境及模式比重</td>
				<td align="center">学期</td>
			</tr>
		</thead>
		<!--显示数据列表-->
		<tbody>
		<s:iterator value="#taticsList" status="s">
			<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
				<td align="center">${studentProportion*100}%&nbsp;</td>
				<td align="center">${peerProportion*100}%&nbsp;</td>
				<td align="center">${selfProportion*100}%&nbsp;</td>
				<td align="center">${teachingProportion*100}%&nbsp;</td>
				<td align="center">${ATerm.name}&nbsp;</td>
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
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">当前评价策略</span>
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
				<td align="center">学生评价比重</td>
				<td align="center">同行评价比重</td>
				<td align="center">自我评价比重</td>
				<td align="center">教学环境及模式比重</td>
				<td align="center">学期</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>
		<!--显示数据列表-->
		<tbody>
		    <s:iterator value="#tatics" status="s">
			<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
				<td align="center">${studentProportion*100}%&nbsp;</td>
				<td align="center">${peerProportion*100}%&nbsp;</td>
				<td align="center">${selfProportion*100}%&nbsp;</td>
				<td align="center">${teachingProportion*100}%&nbsp;</td>
				<td align="center">${ATerm.name}&nbsp;</td>
				<td align="center">
				<s:a action="tatics_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
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

	</div>
</body>
</html>
