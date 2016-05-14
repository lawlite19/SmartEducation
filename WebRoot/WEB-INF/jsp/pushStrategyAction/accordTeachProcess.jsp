<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>根据课程进程</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!--鼠标悬浮变色相关代码开始-->
    <script type="text/javascript">
        var _oldColor;
        function SetNewColor(source) {
            _oldColor = source.style.backgroundColor;
            source.style.backgroundColor = '#E8F5FE';
        }
        function SetOldColor(source) {
            source.style.backgroundColor = _oldColor;
        }
    </script>
<!--鼠标悬浮变色相关代码结束-->
</head>
<body>
<div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">
		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label" style="margin: 0;">
				<span id="span_courseName">${courseName}</span>
<!-- 添加教学进程 -->
<!-- 添加按钮 -->
<s:form action="pushStrategy_updateTeachPorcess" method="post">
<s:hidden name="courseId"></s:hidden>
<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td align="center">
			章节:<s:select list="chapterList" name="chapterId" id="select_chapter" listKey="id" listValue="chapterName" headerKey=""
			   headerValue="==请选择章节==" cssClass="ddl">
			   </s:select>
		</td>		
		<td align="center">
			章节:<s:select list="#{1:'1天',2:'2天',3:'3天',4:'4天',5:'5天',6:'6天',7:'7天'}" name="dayId" id="select_day" 
			     headerKey="" headerValue="==请选择提前天数==" cssClass="ddl">
			   </s:select>
		</td>
		<td  align="center">
			<s:submit value="提交" cssClass="ttn"></s:submit>
		</td>
	</tr>
</table>
</s:form>
			</div>

<!-- 教学进程信息 -->
			<ul style="overflow:hidden;">
			<div class="mframe">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                    <span class="tt">教学进程信息</span></td>
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
			<tr style="height:30px;">
				<th align="center">序号</th>
				<th align="center">周次</th>
				<th align="center">授课日期</th>
				<th align="center">推送日期</th>
				<th align="center">教学章节</th>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
		<s:iterator value="teachProcessList" status="s">
			<tr style="height:30px;" onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">${weekCount}&nbsp;</td>
					<td align="center">${startData}&nbsp;</td>
					<td align="center">${pushTime}&nbsp;</td>
					<td align="center">${chapter.chapterName}&nbsp;</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
</td>
</tr>
</tbody>
</table>
</div>
			</ul>
		</div>
<!-- 我的课程信息 -->
		<ul class="category" style="width:115px;">
			<s:iterator value="couseList" var="num">
				<s:if test="%{courseId==id}">
					<li  class="current">
						<s:a action="pushStrategy_accordTeachProcess?courseId=%{id}">${courseName}</s:a>
					</li>
					<div class="arrow" style="right: 7px;"></div>
				</s:if>
				<s:else>
					<li>
						<s:a action="pushStrategy_accordTeachProcess?courseId=%{id}">${courseName}</s:a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</div>
</body>
</html>
