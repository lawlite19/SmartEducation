<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>个人评价信息</title>
    <%@ include file="/WEB-INF/jsp/public/list.jspf" %>

</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 个人评价信息 &gt; 年度评价信息</div>


<!-- 添加和返回 -->
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="teachergrade_highchar" style="color:white;text-decoration: none;">图表显示</s:a>
		</td>
	</tr>
</table>
 <div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">个人年度评价信息</span>
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
			<s:iterator value="teagrade" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					
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
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div name="stugrade" id="stugrade" >
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">个人学生评价信息详情</span>
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
				<td align="center">班级</td>
				<td align="center">课程名</td>
				<td align="center">学生人数</td>
				<td align="center">学生评价均分</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="classgrade" id="object" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					
						<td align="center"><s:property value="#object[0]"/></td>
                      <td align="center"><s:property value="#object[1]"/></td>
                      <td align="center"><s:property value="#object[2]"/></td>
                      <td align="center"><s:property value="#object[3]"/></td>
                    
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
	</div>

	
</tr>
</tbody>
</table>
	</div>
</body>
</html>
