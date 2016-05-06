<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有课程资源</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<!-- layer弹窗插件 -->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/search.css" />

<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>

<!-- 检测搜索框是否输入 -->
<script type="text/javascript">
    function MM_Empty(ctrlId, msg) {
        var ctrl = document.getElementById(ctrlId);
        if (!ctrl) return true;
        if (ctrl.value.trim() == "") {
        	//正上方
        	layer.msg('请填写'+msg, {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl.focus();
            return false;
        }
        return true;
    }
	function Check() {
        if (!MM_Empty('txt_search', '搜索内容')) return false;
        layer.load();
    }
	function Check2() {
        if (!MM_Empty('txt_search2', '搜索内容')) return false;
        layer.load();
    }
	</script>
<!-- 记录用户操作 -->
<script type="text/javascript">
	function ajaxRecord(courseId) {
		//点击之后禁止再次点击
		//loading层
		layer.load(0,{
  			shade: [0.8,'#000'] //0.1透明度的白色背景
		});
		$.ajax({ 
	    		type: "post",
	    		url: "spiderCourse_recordCount.action", 
	    		data: {
	    			"courseId" : courseId
    			}, 
	    		dataType : "json",
	    		async : false,
            	success: function(data) { 
            	 //不做事
            	}
	       }); 
	}
</script>
<!-- 键盘事件--按下ctrl+enter弹出搜索 -->
<script type="text/javascript" language=JavaScript charset="UTF-8">
      document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==32 && e.ctrlKey){ // 按 Space
            	layer.confirm("<form action='spiderCourse_show.action' method='post'><input type='text' name='searchInfo' id='txt_search'> <input class='button' onclick='return Check();' type='submit' value='搜索'></form><br/>", 
            	{
            		  skin: 'layui-layer-molv',
            		  shift: 2,//动画类型
            		  offset: 0,
					  btn:0
            	});
        };
      }
</script>

</head>

<body>
<div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">

		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label" style="margin: 0;">
			<s:if test="%{name!=null}">
				${name}
			</s:if>
			<s:else>
				全部
			</s:else>
<!-- 搜索框 -->
<div class="container">
<s:form action="spiderCourse_show" method="post">
<input type="text" name="searchInfo" id="txt_search2">
<input class="button" onclick="return Check2();" type="submit" value="搜索">
</s:form>
</div>
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
<input type="hidden" name="searchInfo" value="${searchInfo}">
</s:form>
</body>
</html>
