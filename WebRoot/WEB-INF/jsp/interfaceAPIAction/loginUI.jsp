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
<s:form action="/App/user_appLogin.action" method="post">
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
										${pageContext.request.contextPath}/App/user_appLogin.action
									</p>
								<br/>
								<h2>需要传递的数据</h2>
								<br/>
								<span class="span_apiInfo">
									账号：<span class="span_apiProperty">userNum</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									密码：<span class="span_apiProperty">password</span>
								</span>
								<br/>
								<span class="span_apiInfo">
									用户类型：<span class="span_apiProperty">userType</span>
									,对应的数据为
									<span class="span_apiPropertyInfo">
									老师
									</span>
									和
									<span class="span_apiPropertyInfo">
									学生
									</span>
								</span>
					</div>
					<!-- 返回json数据说明-->
					<div  class="addFont">
						<h2>返回json数据说明</h2>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							name：&nbsp;<span class="span_apiSuccess">success</span><span class="span_apiNormal">-->查询成功</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							teacher：&nbsp;<span class="span_apiNormal">老师信息</span>
						</span>
						</span>
						<br/>
						<span class="span_apiInfo">
						<span class="span_apiProperty">
							student：&nbsp;<span class="span_apiNormal">学生信息</span>
						</span>
						</span>
						</span>
					</div>
					<!-- 测试 -->
					<div class="addFont">
						<h2>测试</h2>
						<span class="span_apiInfo">
							userNum: <s:textfield cssClass="inpu" name="userNum"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
						password:<s:textfield cssClass="inpu" name="password"></s:textfield>
						</span>
						<br/><br/>
						<span class="span_apiInfo">
						userType: <input id="student" type="radio" value="学生" name="userType" />
    						   <label for="student">学生</label>
        					   <input id="teacher" type="radio" value="老师" name="userType" />
                               <label for="teacher">老师</label>
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
