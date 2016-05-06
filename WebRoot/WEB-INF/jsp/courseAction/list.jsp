<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>课程信息列表</title>
	<%@include file="/WEB-INF/jsp/public/list.jspf" %>
	<!-- 查询输入框检查 -->
<script type="text/javascript">
function MM_Empty(ctrlId, msg) {
    var ctrl = document.getElementById(ctrlId);
    if (!ctrl) return true;
    if (ctrl.value.trim() == "") {
    	//正上方
    	layer.msg('请输入查询条件！', {
    	  offset: 0,
    	  shift: 6
    	});
        ctrl.focus();
        return false;
    }
    layer.load();
    return true;
}
function Check() {
    if (!MM_Empty('txt_inputTerm', '课程名称')) return false;
}
	</script>
  </head>
  
  <body>
  <!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学管理 &gt; 课程管理</div>
<!-- 查找-->
<s:form action="course_list" method="post">
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
                  	 按名称：
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
			<s:a action="course_addUI" style="color:white;text-decoration: none;">添加课程</s:a>
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
				<td align="center">课程名</td>
				<td align="center">课程介绍</td>
				<td align="center">学分</td>
				<td align="center">所属部门</td>
				<td align="center">添加时间</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${courseName}" />
					</td>
					<td align="center">${(currentPage-1)*10+s.count}</td>
					<td align="center">${courseName}&nbsp;</td>
					<td align="center">${description}&nbsp;</td>
					<td align="center">${credit}&nbsp;</td>
					<td align="center">
						<s:iterator value="departments" status="s">
							${s.count}、${deptName}
						</s:iterator>
						&nbsp;
					</td>
					<td align="center">${addTime}&nbsp;</td>
					<td align="center">
					<s:a action="course_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="course_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">
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
