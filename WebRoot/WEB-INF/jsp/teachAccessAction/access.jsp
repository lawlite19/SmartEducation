<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>评价列表</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
	<script>
		function Grade(count){
			var grade=document.getElementsByName(accessgrade[count]);
			for(var i=0;i<=grade.length;i++)
			{
				if(grade[i].checked)
					var s=grade[i].value;
			}
			var access=document.getElementById(access[count]);
			access.value=s;
		}
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 我的评价 &gt; 评价自己</div>

<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">评价列表</span>
                    </td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
     <div>
      <s:form action="teachaccess_success" method="post" name="access">
		<table class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
			<!-- 表头-->
			<thead>
				<tr>
					<td align="center">序号</td>
					<td align="center">评价项</td>
					<td align="center">我的评价</td>
				</tr>
			</thead>
			<!--显示数据列表-->
		
		<tbody>
			<s:iterator value="teachaccoutList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">${description}&nbsp;</td>
					<td align="center">  
						<input type="radio" onclick="Grade(${s.index})" value="1" name="accessgrade[${s.index}]" checked="checked"  >${descriptionA}&nbsp;
						<input type="radio" onclick="Grade(${s.index})" value="2" name="accessgrade[${s.index}]" >${descriptionB}&nbsp;
						<input type="radio" onclick="Grade(${s.index})" value="3" name="accessgrade[${s.index}]" >${descriptionC}&nbsp;
						<input type="radio" onclick="Grade(${s.index})" value="4" name="accessgrade[${s.index}]"  >${descriptionD}&nbsp; 
   					 	<input type="hidden" name="accessgrade" id="access[${s.index}]">
   					 <br>				
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		<tr>
		<td align="center">
    			<s:hidden name="id"></s:hidden>
    			<s:submit value="提交"  cssClass="ttn" onclik="Grade()"></s:submit>
		</td>
		</tr>
    </s:form>
	</div>
</td>
</tr>
</tbody>
</table>

	</div>
</body>
</html>
 