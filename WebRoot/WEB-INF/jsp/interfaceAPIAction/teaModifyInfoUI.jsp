<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>老师修改个人信息</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/api.css" />
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 接口文档 &gt; 接口信息</div>
<!-- 信息开始 -->
<s:form action="/App/teacher_appTeaModifyInfo.action" method="post">
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
										${pageContext.request.contextPath}/App/teacher_appTeaModifyInfo.action
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									老师工号：<span class="span_apiProperty">teaNum</span>
								</span>
					</div>
					<div>
								<h2>可以修改的信息</h2>
								<br/>
								<span class="span_apiInfo">
									性别：<span class="span_apiProperty">sex</span><span class="span_apiNormal">-->男或女</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									手机号码：<span class="span_apiProperty">telphone</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									QQ号码：<span class="span_apiProperty">qqNum</span>
								</span>
								
					</div>
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->保存成功</span>
							&nbsp;&nbsp;
							<span class="span_apiWarning">noTeacher</span><span class="span_apiNormal">-->没有找到老师</span>
						</span>
						<br/>
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
						sex:<s:textfield cssClass="inpu" name="sex"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
						telphone:<s:textfield cssClass="inpu" name="telphone"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
						qqNum:<s:textfield cssClass="inpu" name="qqNum"></s:textfield>
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
