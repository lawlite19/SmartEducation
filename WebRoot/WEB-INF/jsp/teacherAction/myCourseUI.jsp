<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程对应班级</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!-- 时间选择控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>

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
        if (!MM_Empty('select_chapter', '章节')) return false;
        if (!MM_Empty('select_class', '班级')) return false;
		//点击之后禁止再次点击
		//loading层
		layer.load(0,{
  			shade: [0.8,'#000'] //0.1透明度的白色背景
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

			<div class="label" style="margin: 0;">
				<span id="span_courseName">${courseName}&nbsp;&nbsp;&nbsp;
				<s:if test="doInfo==1">
					课前预习
				</s:if>
				<s:elseif test="doInfo==2">
					课堂测试
				</s:elseif>
				<s:else>
					课后复习
				</s:else>
				</span>
<!-- 生成题目操作 -->
<s:form action="teacher_autoMakeQuestion" method="post">
<s:hidden name="doInfo"></s:hidden>
<s:hidden name="courseId"></s:hidden>
<table width="90%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tr"></td>
                </tr>
                <tr>
                    <td class="mm" colspan="3">
<table align="left" cellspacing="1" cellpadding="1">
     <tbody><tr>
               <td align="right">
                    	 判断题：
				</td>
               <td>
               		<s:select name="judgementCount" list="#{10:'10道',15:'15道'}" cssClass="ddl" />
               </td>
               <td  align="right">
                  		 单选题：
   			   </td>
               <td>
                   <s:select name="singleChoiceCount" list="#{10:'10道',15:'15道'}" cssClass="ddl" />
               </td>
               
               <td  align="right">
                  		章节：
   			   </td>
               <td>
                   <s:select id="select_chapter" name="chapterId" list="chapterList" cssClass="ddl" headerKey="" headerValue="==请选择章节=="
                   listKey="id" listValue="chapterName"/>
               </td>
              </tr>
              <tr>
               <td  align="right">
                  		班级：
   			   </td>
   			   <td>
                   <s:select id="select_class" name="classId" list="classList"  cssClass="ddl"  headerKey="" headerValue="==请选择章节=="
                    listKey="id" listValue="className"/>
               </td>
               <td  align="right">
                  		学生提交结束时间：
   			   </td>
   			   <td>
                   <s:textfield name="endTime" cssClass="inpu" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
               </td>
               <td>
               </td> 
               <td colspan="4" align="center">
               	<s:submit value="生成测试" cssClass="ttn" onclick="return Check();" ></s:submit>
               	<span style="color:red;"><s:fielderror name="errorInfo"></s:fielderror></span>
			   </td>
			   
        </tr>
    </tbody>
</table>
</td>
</tr>
</tbody>
</table>

</s:form>
<!-- 生成测试列表 -->
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">测试题目信息</span>
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
			<tr style="height:30px;font-size:14px;">
				<td align="center">序号</td>
				<td align="center">问题数量</td>
				<td align="center">学生提交数</td>
				<td align="center">结束提交时间</td>
				<td align="center">班级</td>
				<td align="center">章节</td>
				<td align="center">操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="recordList" status="s">
			
			
			<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);" style="height:30px;">
					<td  align="center">
						${(currentPage-1)*10+s.count}
					</td>
					<td  align="center">
						${questionCount}
					&nbsp;
					</td>
					<td  align="center">${submitCount}&nbsp;</td>
					<td  align="center">${endTime}&nbsp;</td>
					<td  align="center">${class_.className}&nbsp;</td>
					<td  align="center">${chapter.chapterName}&nbsp;</td>
					<td  align="center">
					<s:a action="teacher_deleteTestPaper?testPaperId=%{id}&courseId=%{courseId}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
					|
					<s:a action="teacher_seeTestPaperUI?testPaperId=%{id}">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/see.png"  />
					</s:a>
						&nbsp;</td>
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




			</div>
</div>
<!-- 我的课程信息 -->
		<div id="Menu">
			<ul id="MenuUl" class="category" style="width:115px;margin-top:0px;">
			<s:iterator value="couseList" var="num">
				<s:if test="%{courseId==id}">
					<li  class="current" style="margin-top:100px;">
						${courseName}
						<ul style="" class="MenuLevel2" id="aa"  style="text-align:right;">
							<%-- 显示二级菜单 --%>
								<li style="padding-right:0;">
									<div class="level2Style">
										<a href="${pageContext.request.contextPath}/teacher_myCourseUI.action?courseId=${id}&doInfo=1"> 课前预习</a>
									</div>
								</li>
								<li style="padding-right:0;">
									<div class="level2Style">
										<a href="${pageContext.request.contextPath}/teacher_myCourseUI.action?courseId=${id}&doInfo=2"> 课堂测试</a>
									</div>
								</li>
								<li style="padding-right:0;">
									<div class="level2Style">
										<a href="${pageContext.request.contextPath}/teacher_myCourseUI.action?courseId=${id}&doInfo=3"> 课后复习</a>
									</div>
								</li>
						</ul>
					</li>
					
					<div class="arrow" style="right: 7px;"></div>
				</s:if>
				<s:else>
					<li  style="margin-top:100px;">
						${courseName}
						<ul style="" class="MenuLevel2" id="aa" style="text-align:right;">
							<%-- 显示二级菜单 --%>
								<li style="padding-right:0;">
									<div class="level2Style">
										<a href="${pageContext.request.contextPath}/teacher_myCourseUI.action?courseId=${id}&doInfo=1"> 课前预习</a>
									</div>
								</li>
								<li style="padding-right:0;">
									<div class="level2Style">
										<a href="${pageContext.request.contextPath}/teacher_myCourseUI.action?courseId=${id}&doInfo=2"> 课堂测试</a>
									</div>
								</li>
								<li style="padding-right:0;">
									<div class="level2Style">
										<a href="${pageContext.request.contextPath}/teacher_myCourseUI.action?courseId=${id}&doInfo=3"> 课后复习</a>
									</div>
								</li>
						</ul>
					</li>
				</s:else>
			</s:iterator>
			</ul>

		</div>
</div>
</div>

</body>
</html>
