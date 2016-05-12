<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>测试卷题目信息</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 课程管理 &gt; 题目查看</div>

<div>

<!-- 添加按钮 
<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="teacher_addUI" style="color:white;text-decoration: none;">添加题目</s:a>
		</td>
	</tr>
</table>-->

<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">判断题管理</span>
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
				<td align="center">序号</td>
				<td align="center">所属课程</td>
				<td align="center">所属章节</td>
				<td align="center">知识点</td>
				<td align="center">问题</td>
				<td align="center">答案</td>
				<td align="center">难易指数</td>
				<td align="center">添加时间</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="judgementList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">${course.courseName}&nbsp;</td>
					<td align="center">${chapter.chapterName}&nbsp;</td>
					<td align="center">${knowledgeName}&nbsp;</td>
					<td align="center">
				    <s:if test="question.length()>10">
						<a class="questionDetails" id="${question}" style="cursor:pointer;">
							${question.substring(0,10)} .....
						</a>
					</s:if>
					<s:else>
							${question}
					</s:else>
					&nbsp;</td>
					<td align="center">${answer}&nbsp;</td>
					<td align="center">${difExponent}&nbsp;</td>
					<td align="center">${addTime}&nbsp;</td>
					<td align="center">
					<s:a action="judgement_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="judgement_deleteJudgement?judgementId=%{id}&testPaperId=%{testPaperId}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
					</td>
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
<!-- 单选题 -->
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">单选题管理</span>
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
				<td align="center">序号</td>
				<td align="center">所属课程</td>
				<td align="center">所属章节</td>
				<td align="center">知识点</td>
				<td align="center">问题</td>
				<td align="center">正确答案</td>
				<td align="center">答案</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="singleChoiceList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">${course.courseName}&nbsp;</td>
					<td align="center">${chapter.chapterName}&nbsp;</td>
					<td align="center">${knowledgeName}&nbsp;</td>
					<td align="center">
					<s:if test="question.length()>10">
						<a class="questionDetails" id="${question}" style="cursor:pointer;">
							${question.substring(0,10)} .....
						</a>
					</s:if>
					<s:else>
							${question}
					</s:else>
					&nbsp;</td>
					<td align="center">${answer}&nbsp;</td>
					<td align="center">
					<s:if test="answerA.length()>10">
						<a class="answerDetails" id="A:${answerA}<br/>B:${answerB}<br/>C:${answerC}<br/>D:${answerD}"
						style="cursor:pointer;">
							A：${answerA.substring(0,10)} .....
						</a>
					</s:if>
					<s:else>
						<a class="answerDetails" id="A:${answerA}<br/>B:${answerB}<br/>C:${answerC}<br/>D:${answerD}"
						style="cursor:pointer;">
							A：${answerA} .....
						</a>
					</s:else>
					</td>
					<td align="center">
					<s:a action="singleChoice_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="teacher_deleteSingleChoice?singleChoiceId=%{id}&testPaperId=%{testPaperId}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
					</td>
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
	</div>
		<!-- tips加载全部信息 -->
<script type="text/javascript">
(function(){
	//显示答案描述
	$('a.answerDetails').on('click', function (event) {
	    var value = $(this).attr("id");
	  //tips层-左
		 index=layer.tips(value, this, {
		  tips: [1, '#78BA32'],
		  time:4000 //4s关闭
		});
	});
	$('a.questionDetails').on('click',function(event){
		var value = $(this).attr("id");
		  //tips层-左
		 index=layer.tips(value, this, {
		  tips: [1, '#78BA32'],
		  time:4000 //4s关闭
		});
	});
})();

</script>
</body>
</html>
