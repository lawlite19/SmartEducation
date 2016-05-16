<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示数据字典</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<script>
	$(function() {
             $("#deleteSelected").click(function(){
            	 $("input:checked").each(function(){
            	 	 var value=$(this).val().split(",");
           	    	 if (confirm("确定要删除"+value[1]+"吗?")) {
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "dataDict_bulkDelete.action", 
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
        	   //window.location.reload();
      });

	});

</script>
	<script type="text/javascript">
    function MM_Empty(ctrlId, msg) {
        var ctrl = document.getElementById(ctrlId);
        if (!ctrl) return true;
        if (ctrl.value.trim() == "") {
        	//正上方
        	layer.msg('请选择'+msg, {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl.focus();
            return false;
        }
        return true;
    }
	function Check() {
        if (!MM_Empty('dataTypeId', '数据字典类型')) return false;
        layer.load();
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 用户管理 &gt; 学生管理</div>
<!-- 查找-->
<s:form action="dataDict_list" method="post">
<table width="90%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tr"></td>
                </tr>
                <tr>
                    <td class="mm" colspan="3">
<table align="left" cellspacing="1" cellpadding="1">
     <tbody><tr>
               <td>
                                     按数据项类型：
				</td>
               <td>
               		<s:select id="dataTypeId" name="dataTypeId" cssClass="ddl" list="#dataTypeList"
					listKey="id" listValue="dataTypeName" headerKey="" headerValue="==请选择数据类型=="/>
               </td>
               <td colspan="4" align="center">
               		<s:submit onclick="return Check();" cssClass="ttn" value="查询" ></s:submit>
			   </td>
        </tr>
    </tbody>
</table>
</td>
</tr>
</tbody>
</table>

</s:form>
<!-- 引入选择按钮 -->
<%@include file="/WEB-INF/jsp/public/list_button.jspf" %>
<!-- 添加按钮 -->
<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="dataDict_addUI" style="color:white;text-decoration: none;">添加数据字典</s:a>
		</td>
	</tr>
</table>
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">数据字典管理</span>
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
				<td align="center">数据项类型名称</td>
				<td align="center">数据项编号</td>
				<td align="center">数据项名称</td>
				<td align="center">备注</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${stuName}" />
					</td>
					<td align="center">${(currentPage-1)*10+s.count}</td>
					<td align="center">${dataType.dataTypeName}&nbsp;</td>
					<td align="center">${dictNum}&nbsp;</td>
					<td align="center">${dictName}&nbsp;</td>
					<td align="center">${description}</td>
					<td align="center">
					<s:a action="dataDict_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="dataDict_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
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
	<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
	</div>
</body>
</html>
