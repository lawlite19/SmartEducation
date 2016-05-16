<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>学生提问列表</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/api.css" />
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 接口文档 &gt; 接口信息</div>
<!-- 信息开始 -->
<s:form action="/App/testPaper_appAutoMakeQuestion.action" method="post">
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">说明信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                    </td>
                    <td class="mm">
					</td>
			</tr>
			<tr>
				<td colspan="2">
							<!-- 说明 -->
					<div>
							<h2>接口地址</h2>
									<p class="p_apiInfo">
										${pageContext.request.contextPath}/App/testPaper_appAutoMakeQuestion.action
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									老师工号：<span class="span_apiProperty">teaNum</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									课程id：<span class="span_apiProperty">courseId</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									课程章节id：<span class="span_apiProperty">chapterId</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									作业类型：<span class="span_apiProperty">testType</span>
										<span class="span_apiInfo">(课前预习、课堂测试、课后复习)</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									班级id：<span class="span_apiProperty">classId</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									单选题道数：<span class="span_apiProperty">singleChoiceCount</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									判断题道数：<span class="span_apiProperty">judgementCount</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									答题结束时间：<span class="span_apiProperty">endTime</span>-->
											<span class="span_apiWarning">Timestamp类型</span>
								</span>
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiWarning">noCourse</span><span class="span_apiNormal">-->没有找到课程</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiWarning">noChapter</span><span class="span_apiNormal">-->没有找到章节</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiWarning">judgementNotEnough</span><span class="span_apiNormal">-->题库中此章节判断题数量不足</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiWarning">singleChoiceNotEnough</span><span class="span_apiNormal">-->题库中此章节单选题数量不足</span>
						</span>
					</div>
					<!-- 测试 -->
					<div class="addFont">
						<h2>测试</h2>
						<span class="span_apiInfo">
							teaNum: <s:textfield cssClass="inpu" name="teaNum"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							courseId: <s:textfield cssClass="inpu" name="courseId"></s:textfield>eg:9
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							chapterId: <s:textfield cssClass="inpu" name="chapterId"></s:textfield>eg:1
						</span>
						<br/><br/>
						
						<span class="span_apiInfo">
							testType: <s:textfield cssClass="inpu" name="testType"></s:textfield>eg:课堂测试
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							classId: <s:textfield cssClass="inpu" name="classId"></s:textfield>eg:1
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							singleChoiceCount: <s:textfield cssClass="inpu" name="singleChoiceCount"></s:textfield>eg:10
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							judgementCount: <s:textfield cssClass="inpu" name="judgementCount"></s:textfield>eg:10
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							endTime: <s:textfield cssClass="inpu" name="endTime"></s:textfield>eg:2016-06-09 16:17:28
						</span>
						<br/><br/>
                        <span class="span_apiButton">
                        	<s:submit value="提交"   cssClass="ttn"></s:submit>
                        </span>
					</div>

				</td>
			</tr>
	</tbody>
     </table>
</div>
</s:form>
</body>
</html>
