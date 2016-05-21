<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
  	<%@include file="/WEB-INF/jsp/public/list.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的在线时间记录</title>
  </head>
  
  <body>
<script type="text/javascript">
function popMsg(msg) {
    alert("" + msg + ".");
}
</script>
<div align="center">
<hr style="border:solid 1px #000080" />
<img src='${pageContext.request.contextPath}/charts/getchart.jsp?${chart1URL}' usemap="#map1" border="0">
<map name="map1">${imageMap1}
${chartImageMap}
${legendImageMap}
<area ${titleCoor}  href='javascript:popMsg("${titleCoor}");' title='标题可以点击' />
</map>
</div>
</body>
</html>
