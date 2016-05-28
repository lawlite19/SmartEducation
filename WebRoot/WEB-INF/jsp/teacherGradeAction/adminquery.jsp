<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>教师年度评价列表</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
	
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
        return MM_Empty('select_term', 'txt_inputTerm');
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 个人评价信息关系 &gt; 教师评价信息管理</div>
<!-- 查找-->
<s:form action="teachergrade_adminquery" method="post">
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
                                     按学期：
				</td>
               <td>
               		<s:select name="termId" id="select_term" cssClass="ddl" list="#termList"
					listKey="id" listValue="name" headerKey="" headerValue="==请选择学期==" />
               </td>
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

<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">教师年度评价</span>
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
				<td align="center">序号</td>
				<td align="center">教师名</td>
				<td align="center">学生评价</td>
				<td align="center">同行评价</td>
				<td align="center">自我评价</td>
				<td align="center">教学要素评价</td>
				<td align="center">总评分</td>
				<td align="center">学期</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${(currentPage-1)*10+s.count}</td>
					<td align="center">${Teacher.teaName}</td>
					<td align="center">${stugrade}&nbsp;</td>
					<td align="center" >${peergrade}&nbsp;</td>
					<td align="center" >${selfgrade}&nbsp;</td>
					<td align="center" >${teachgrade}&nbsp;</td>
					<td align="center" >${allgrade}&nbsp;</td>
					<td align="center" >${ATerm.name}&nbsp;</td>
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
