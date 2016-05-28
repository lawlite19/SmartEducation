<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>评价列表</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/style/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/script/star-rating.js" type="text/javascript"></script>

	<script>
		$(document).on('ready', function(){
   		 $('#input-2').rating({
      	  step: 1,
        starCaptions: {1: '60', 2: '70', 3: '80', 4: '90', 5: '100'},
        starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
    });
});
</script>

	
	
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 我的评价 &gt; 评价教师</div>

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
      <s:form action="stuaccess_success">
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
			<s:iterator value="stuaccoutList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">${description}&nbsp;</td>
					<td align="center">  
					  <input id="#s.count" value="4" type="number" class="rating" 
					  min=0 max=5 step=0.5 data-size="lg"  name="accessgrade">
   					 <br>				
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		<tr>
		<td align="center">
		<div>
    	<s:hidden name="id"></s:hidden>
    	<s:submit value="提交" onclick="return Check();" cssClass="ttn" ></s:submit>
		</div>
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
