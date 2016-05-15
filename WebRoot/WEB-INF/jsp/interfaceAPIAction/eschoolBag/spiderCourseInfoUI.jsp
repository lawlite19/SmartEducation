<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/api.css" />

<!-- 查询输入框检查 -->
<script type="text/javascript">
    function MM_Empty(ctrl1Id, ctrl2Id) {
        var ctrl1 = document.getElementById(ctrl1Id);
        var ctrl2 = document.getElementById(ctrl2Id);
        if ((ctrl1.value.trim() == "")&&(ctrl2.value.trim() == "")) {
        	//正上方
        	layer.msg('请输入查询条件！', {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl1.focus();
            return false;
        }
        layer.load();
        return true;
    }
	function Check() {
		return MM_Empty('select_dept', 'txt_inputTerm');
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 接口文档 &gt; 接口信息</div>
<!-- 信息开始 -->
<s:form action="/App/spiderCourse_appListCourseInfo.action" method="post">
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
										${pageContext.request.contextPath}/App/spiderCourse_appListCourseInfo.action
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									课程id：<span class="span_apiProperty">courseId</span>
								</span>
					</div>
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="span_apiWarning">noCourseInfo</span><span class="span_apiNormal">-->没有此课程</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							scoreName：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="span_apiWarning">noCourseInfo</span><span class="span_apiNormal">-->没有此课程</span>
						</span>
						<br/>
						
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							spiderCourseInfo：&nbsp;<span class="span_apiNormal">课程介绍信息</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							spiderChapters：&nbsp;<span class="span_apiNormal">课程章节</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="span_apiSuccess">chapterName：</span><span class="span_apiNormal">-->章节名称</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="span_apiSuccess">children：</span><span class="span_apiNormal">-->子章节</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							spiderDocuments：&nbsp;<span class="span_apiNormal">课程参考教材</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							scoreName：&nbsp;<span class="span_apiSuccess">noScore</span><span class="span_apiNormal">-->还没有评分</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							courseScore：&nbsp;<span class="span_apiNormal">-->课程评分</span>
						</span>
						</span>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							discussName：&nbsp;<span class="span_apiSuccess">noDiscuss</span><span class="span_apiNormal">-->还没有讨论</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							courseDiscusses：&nbsp;<span class="span_apiNormal">-->课程讨论信息</span>
						</span>
						</span>
						</span>
					</div>
					<!-- 测试 -->
					<div class="addFont">
						<h2>测试</h2>
						<span class="span_apiInfo">
							courseId: <s:textfield cssClass="inpu" name="courseId"></s:textfield>
						</span>
						<br/><br/>
						
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
