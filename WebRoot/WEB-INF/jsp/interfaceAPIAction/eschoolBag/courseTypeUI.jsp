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
<s:form action="/App/spiderCourse_appCourseType.action" method="post">
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
									<a href="${pageContext.request.contextPath}/App/spiderCourse_appCourseType.action">
										${pageContext.request.contextPath}/App/spiderCourse_appCourseType.action
									</a>
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									无
								</span>
					</div>
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="span_apiSuccess">noProfessionType</span><span class="span_apiNormal">-->没有专业类型</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							spiderProfessionTypes：&nbsp;<span class="span_apiNormal">专业类型信息</span>
						</span>
						</span>
					</div>
					<!-- 测试 -->
					<div class="addFont">
						<h2>测试</h2>
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
