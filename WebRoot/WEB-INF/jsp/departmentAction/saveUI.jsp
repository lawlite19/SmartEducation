<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>部门设置</title>
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
        if (!MM_Empty('select_dept', '上级部门')) return false;
        if (!MM_Empty('txt_deptName', '部门名称')) return false;
        if (!MM_Empty('txt_deptLevel', '部门级别')) return false;
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 部门管理 &gt; 
<s:if test="%{id == null}">
添加部门
</s:if>
<s:else>
修改部门
</s:else>
</div>

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
<s:form action="department_%{id == null ? 'add' : 'edit'}">
<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
			<tr>
                    	<td class="addFont">上级部门</td>
                        <td>
                        	<s:select name="parentId" cssClass="ddl" id="select_dept"
                        		list="#departmentList" listKey="id" listValue="deptName"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>
                        	<span class="span_note"> * </span>
                        </td>
                    </tr>
                    <tr><td class="addFont">部门名称</td>
                        <td><s:textfield  name="deptName" cssClass="inpu" id="txt_deptName"/>
                         <span class="span_note"> * </span>
                         </td>
                    </tr>
                    
                    <tr><td class="addFont">部门级别</td>
                        <td><s:textfield  name="deptLevel" cssClass="inpu" id="txt_deptLevel"/>
							<span class="span_note"> * </span></td>
                    </tr>
                    <tr><td class="addFont">职能说明</td>
                        <td><s:textarea name="deptDescription" cssClass="inpu" cssStyle="height:80px;width:350px"></s:textarea></td>
                    </tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<input type="submit" onclick="return Check();" class="ttn" value="提交"/>
                        	<a href="javascript:history.go(-1);">返回</a>
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
