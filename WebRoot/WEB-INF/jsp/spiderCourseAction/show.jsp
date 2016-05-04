<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有课程资源</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>

<script type="text/javascript">
	function ajaxRecord(courseId) {
		layer.load();
		$.ajax({ 
	    		type: "post",
	    		url: "spiderCourse_recordCount.action", 
	    		data: {
	    			"courseId" : courseId
    			}, 
	    		dataType : "json",
	    		async : false,
            	success: function(data) { 
            	 //var json = eval("(" + data + ")");
				 var str = data.name;
					  if (str=="favorOK") {
						layer.msg('收藏成功', {time: 2000, icon:6});
      				  } else {
      					layer.msg('服务器错误，收藏失败',{time: 2000, icon:5});
      				}
	    		} 
	       }); 
	}
</script>
</head>

<body>
<div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">

		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label">
			<s:if test="%{name!=null}">
				${name}
			</s:if>
			<s:else>
				全部
			</s:else>
			</div>
<!-- 课程信息 -->
			<ul style="overflow:hidden;">
			<s:iterator value="recordList" status="s">
				<li class="ans-slow-anim">
					<div class="picArea ans-slow-anim">
						<a href="spiderCourse_showCourseInfo.action?courseId=${id}" onclick="ajaxRecord('${id}');" > 
							<img src="${imgUrl}" width="178" height="109">
						</a>
					</div>
					<div class="introArea">
						<a href="spiderCourse_showCourseInfo.action?courseId=${id}" title="${name}">${name}</a>
					</div>
					<div class="introArea2" title="${info}">${info}</div>
				</li>
			</s:iterator>
			</ul>
<!-- 分页信息 -->
		<%@include file="/WEB-INF/jsp/public/pageView.jspf" %>

		</div>
<!-- 课程类型 -->
		<ul class="category">
			<s:iterator value="professionTypeList" var="num">
				<s:if test="%{professionId==id}">
					<li  class="current">
						<a href="spiderCourse_show.action?professionId=${id}">${name}</a>
					</li>
					<div class="arrow"></div>
				</s:if>
				<s:else>
					<li>
						<a href="spiderCourse_show.action?professionId=${id}">${name}</a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</div>
<!-- 分页提交信息 -->
<s:form action="spiderCourse_show" method="post">
<input type="hidden" name="professionId" value="${professionId}" />
</s:form>
</body>
</html>
