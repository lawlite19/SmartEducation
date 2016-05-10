<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有用户</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<script>
	$(function() {
             $("#deleteSelected").click(function(){
            	 $("input:checked").each(function(){
            	 	 var value=$(this).val().split(",");
           	    	 if (confirm("确定要删除"+value[1]+"吗?")) {
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "student_bulkDelete.action", 
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
<!-- 查询输入框检查 -->
<script type="text/javascript">
    function MM_Empty(ctrl1Id, ctrl2Id) {
        var ctrl1 = document.getElementById(ctrl1Id);
        var ctrl2 = document.getElementById(ctrl2Id);
        if ((ctrl1.value.trim() == "")&&(ctrl2.value.trim() == "")) {
        	//正上方
        	layer.msg('请输入查询条件！', {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl1.focus();
            return false;
        }
        layer.load();
        return true;
    }
	function Check() {
		return MM_Empty('select_dept', 'txt_inputTerm');
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 用户管理 &gt; 学生管理</div>
<!-- 查找-->
<s:form action="student_list" method="post">
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
                                     按部门：
				</td>
               <td>
               		<s:select name="departmentId" id="select_dept" cssClass="ddl" list="#departmentList"
					listKey="id" listValue="deptName" headerKey="" headerValue="==请选择部门==" />
               </td>
               <td>
                   <s:select name="viewType" list="#{0:'姓名', 1:'账号'}" cssClass="ddl" />
   			   </td>
               <td>
                   <s:textfield name="inputTerm" id="txt_inputTerm" cssClass="inpu"></s:textfield>
               </td>
               <td>
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
			<s:a action="student_addUI" style="color:white;text-decoration: none;">添加学生</s:a>
		</td>
	</tr>
</table>
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
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
                    <div>
	<table class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<!-- 表头-->
		<thead>
			<tr>
				<td align="center">选择</td>
				<td align="center">序号</td>
				<td align="center">所属部门</td>
				<td align="center">所属班级</td>
				<td align="center">姓名</td>
				<td align="center">账号</td>
				<td align="center">年级</td>
				<td align="center">性别</td>
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
					<td align="center">${department.deptName}&nbsp;</td>
					<td align="center">${class_.className}&nbsp;</td>
					<td align="center">${stuName}&nbsp;</td>
					<td align="center">${stuNum}&nbsp;</td>
					<td align="center">${grade}&nbsp;</td>
					<td align="center">${sex}&nbsp;</td>
					<td align="center">
					<s:a action="student_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="student_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
					<s:a action="student_initPassword?id=%{id}" onclick="return window.confirm('初始化密码为123456')">
						初始化密码
					</s:a> &nbsp;</td>
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
