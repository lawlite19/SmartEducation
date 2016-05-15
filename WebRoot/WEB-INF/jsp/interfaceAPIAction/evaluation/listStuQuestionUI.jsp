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
<s:form action="/App/stuQuestion_appListStuQuestion.action" method="post">
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
										${pageContext.request.contextPath}/App/stuQuestion_appListStuQuestion.action
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									学号：<span class="span_apiProperty">stuNum</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									页号：<span class="span_apiProperty">pageNum</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									每页大小：<span class="span_apiProperty">pageSize</span>
								</span>
								
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiWarning">noRecord</span><span class="span_apiNormal">-->没有提问问题</span>
								
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							stuQuestions：&nbsp;<span class="span_apiNormal">学生提问的问题信息</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="span_apiSuccess">done</span><span class="span_apiNormal">-->该问题老师是否回答，<span class="span_apiSuccess">1</span>-->已回答，<span class="span_apiWarning">0</span>-->没有回答</span>
								
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							count：&nbsp;<span class="span_apiNormal">总记录数</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							currentPage：&nbsp;<span class="span_apiNormal">当前页</span>
						</span>
						</span>
					</div>
					<!-- 测试 -->
					<div class="addFont">
						<h2>测试</h2>
						<span class="span_apiInfo">
							stuNum: <s:textfield cssClass="inpu" name="stuNum"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							pageNum: <s:textfield cssClass="inpu" name="pageNum"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
							pageSize: <s:textfield cssClass="inpu" name="pageSize"></s:textfield>
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
