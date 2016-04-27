<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>角色管理</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
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
        if (!MM_Empty('txt_roleName', '角色名称')) return false;
    }
	</script>
</head>
<body> 
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 角色管理 &gt; 
<s:if test="%{id == null}">
添加角色
</s:if>
<s:else>
修改角色
</s:else>
</div>
<!--显示表单内容-->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">部门信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="role_%{ id == null ? 'add' : 'edit' }" method="post">
<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
			        <tr>
                        <td class="addFont">角色名称</td>
                        <td>
                        	<s:textfield name="roleName" id="txt_roleName" cssClass="inpu" />
                        	<span class="span_note"> * </span>
                        </td>
                    </tr>
                    <tr>
                        <td class="addFont">角色说明</td>
                        <td>
                        	<s:textarea name="description" cssClass="inpu" cssStyle="height:80px;width:350px"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="提交" cssClass="ttn" onclick="return Check();"></s:submit>
                        	&nbsp;&nbsp;&nbsp;
                        	<input type="button" class="ttn" onclick="javascript:history.go(-1);" value="返回" />
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

