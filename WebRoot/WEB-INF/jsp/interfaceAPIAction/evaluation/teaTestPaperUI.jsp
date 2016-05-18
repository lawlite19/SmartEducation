<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>老师课程的测试卷</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/api.css" />
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 接口文档 &gt; 接口信息</div>
<!-- 信息开始 -->
<s:form action="/App/testPaper_appTeaTestPaper.action" method="post">
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
										${pageContext.request.contextPath}/App/testPaper_appTeaTestPaper.action
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									老师工号：<span class="span_apiProperty">teaNum</span>
								</span>
								
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiWarning">noTestPaper</span><span class="span_apiNormal">-->没有找到测试卷</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							testPapers：&nbsp;<span class="span_apiNormal">测试卷信息</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="span_apiSuccess">endTime</span><span class="span_apiNormal">-->测试卷结束提交时间，过时不可提交
								
						</span>
						</span>
						</span>
					</div>
					<!-- 测试 -->
					<div class="addFont">
						<h2>测试</h2>
						<span class="span_apiInfo">
							teaNum: <s:textfield cssClass="inpu" name="teaNum"></s:textfield>eg:01001
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
