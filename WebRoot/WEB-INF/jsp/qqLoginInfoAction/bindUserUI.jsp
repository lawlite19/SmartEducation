<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>绑定用户界面</title>
	<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
	 <!-- qq登录js -->
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
 data-appid="101304940" data-redirecturi="http://www.soeasystudy.com/back.jsp" charset="utf-8"></script>
 
	<!-- 验证是否输入 -->
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
        if (!MM_Empty('txt_userNum', '用户名')) return false;
        if (!MM_Empty('txt_password', '密码')) return false;
    }
	</script>
	<!-- 返回按钮 -->
	<script type="text/javascript">
	function goBack(){
		//如果qq登录，就退出
		if(QC.Login.check()){
			QC.Login.signOut();
		}
		history.go(-1);
	}
	</script>
</head>

<body>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">绑定用户</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="qqLoginInfo_bindUser.action" method="post">
<s:hidden name="openId"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
			<td colspan="2" class="addFont">
				<font color="red"><s:fielderror fieldName="bindInfo"/></font>
			</td>
		</tr>
		<tr>
			<td class="addFont" align="right">用户名</td>
			<td>
				<s:textfield cssClass="inpu" name="userNum" id="txt_userNum" ></s:textfield>
				<span class="span_note">*</span>
			</td>
		</tr>
		<tr>
			<td class="addFont" align="right">密&nbsp;码</td>
			<td>
				<s:password cssClass="inpu" name="password" id="txt_password"></s:password>
				<span class="span_note">*</span>
			</td>
		</tr>
		<tr>
		<td>
		</td>
			<td><input id="manager" type="radio" value="管理员" name="userType" checked="checked" />
			    	<label for="manager">管理员</label>
				<input id="student" type="radio" value="学生" name="userType" />
					<label for="student">学生</label> <br />
				<input id="responsible" type="radio" value="负责人" name="userType" />
					<label for="responsible">负责人</label> 
				<input id="teacher" type="radio" value="老师" name="userType" /> 
					<label for="teacher">老师</label></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<s:submit value="确定" cssClass="ttn" onclick="return Check();"></s:submit>
				<input type="button" class="ttn" value="返回" onclick="goBack();"/>
			</td>
		</tr>
		</tbody>
	</table>
		
    	
	</s:form>
	</div>
	</td>
	</tr>
	</tbody>
     </table>
</div>

</body>
</html>
