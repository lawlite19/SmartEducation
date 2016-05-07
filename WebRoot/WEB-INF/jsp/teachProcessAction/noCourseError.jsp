<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程进程</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />

</head>
<body>
<!-- 顶层 -->
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学管理 &gt;教学进程</div>
	<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          <tr>
                    	<td colspan="2" align="center">
                    		<h1>您还没有课程，请先添加课程！</h1>
                    	</td>
          </tr>
                    <tr>
                    	
                        <td colspan="2" align="center">
                        	<s:form action="course_addMyCourseUI" method="post">
                        	<s:submit value="添加课程"  cssClass="ttn"></s:submit>
                        	</s:form>
                        </td>
                    </tr>
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
