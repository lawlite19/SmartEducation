<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码界面</title>
    <%@include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
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
        if (!MM_Empty('txt_oldPassword', '原始密码')) return false;
        if (!MM_Empty('txt_newPassword', '新密码')) return false;
        if (!MM_Empty('txt_confirm', '确认密码')) return false;
        var newPassword=$("#txt_newPassword").val();
        var confirm=$("#txt_confirm").val();
        if(newPassword!=confirm){
        	//正上方
        	layer.msg('新密码不一致！', {
        	  offset: 0,
        	  shift: 6
        	});
            return false;
        }
        layer.load();
    }
	</script>
  </head>
  
  <body>
    <!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 密码修改</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">修改密码</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="user_modifyPassword" method="post"> 
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td colspan="2" class="addFont">
						<font color="red"><s:fielderror fieldName="information"  /></font>
						</td>
                        
                    </tr>
                    <tr>
                    	<td class="addFont">原始密码：</td>
                        <td>
                        	<input type="password" name="oldPassword" class="inpu" id="txt_oldPassword" />
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">新密码：</td>
                        <td>
                           	<input type="password" name="newPassword" class="inpu" id="txt_newPassword" />
                        <span class="span_note">*</span>
						</td>
                    </tr>
                    <tr><td  class="addFont">再次输入</td>
                        <td>
                       		  <input type="password" name="confirmPassword" class="inpu" id="txt_confirm"/>
                        	<span class="span_note">*</span>
                        </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="提交" onclick="return Check();" cssClass="ttn"></s:submit>
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
