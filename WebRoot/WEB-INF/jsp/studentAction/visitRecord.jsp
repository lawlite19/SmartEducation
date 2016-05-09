<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
  	<%@include file="/WEB-INF/jsp/public/list.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的历史浏览记录</title>
  </head>
  
  <body>
  	<div align="center">
	<hr color="#000080">
	<img src='${pageContext.request.contextPath}/charts/getchart.jsp?${chart1URL}' usemap="#map1" border="0">
	<map name="map1">${imageMap1}</map>
	</div>
	<s:form name="student_visitRecord" method="post" >
	</s:form>
	<div class="pageView" style="font-size:20px;">
页次：${currentPage}/${pageCount}&nbsp; 每页：${pageSize}条&nbsp;
总记录条数：${recordCount}&nbsp;
<a href="javascript: gotoPage(1)" title="首页" style="cursor: hand;">首页</a>
<s:iterator begin="%{beginPageIndex}" end="%{endPageIndex}" var="num">
	<s:if test="#num == currentPage">
		<%-- 当前页 --%>
		<span class="PageSelectorSelected">${num}</span>
	</s:if>
	<s:else>
		<%-- 非当前页 --%>
		<span class="PageSelectorNum" onClick="gotoPage(${num});">${num}</span>
	</s:else>
</s:iterator>
<a href="javascript: gotoPage(${pageCount})" title="尾页"
	style="cursor: hand;">尾页</a>
转到：
<select onchange="gotoPage(this.value)" id="_pn">
	<s:iterator begin="1" end="%{pageCount}" var="num">
		<option value="${num}">${num}</option>
	</s:iterator>
</select>
<script type="text/javascript">
	$("#_pn").val("${currentPage}");
</script>

<script type="text/javascript">
	function gotoPage( pageNum ){
		// window.location.href = "forum_show.action?id=${id}&pageNum=" + pageNum;
		
		$(document.forms[0]).append("<input type='hidden' name='pageNum' value='" + pageNum +"'>");
		document.forms[0].submit();
	}
</script>
</div>
  </body>
</html>
