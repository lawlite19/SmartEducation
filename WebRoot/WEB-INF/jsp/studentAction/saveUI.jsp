<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>学生管理</title>
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
<div class="crumd"><a href="" id="A1">首页</a> &gt; 学生管理 &gt; 
<s:if test="%{id == null}">
添加学生信息
</s:if>
<s:else>
修改学生信息
</s:else>
</div>
<!-- 信息开始 -->
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
<s:form action="student_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td class="addFont">所属部门</td>
                        <td>
                        	<s:select name="departmentId" cssClass="ddl"
                        		list="#departmentList" listKey="id" listValue="deptName"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr>
                    	<td class="addFont">所属班级</td>
                        <td>
                        
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">登录名</td>
                        <td><s:textfield name="stuNum" cssClass="InputStyle"/>
                        <span class="span_note">*</span>
							（登录名要唯一，设置为学号或工号）
						</td>
                    </tr>
                    <tr><td  class="addFont">姓名</td>
                        <td><s:textfield name="stuName" cssClass="InputStyle"/>
                        	<span class="span_note">*</span>
                        </td>
                        
                    </tr>
					<tr><td  class="addFont">性别</td>
                        <td>
                        	<%--
							<s:radio name="gender" list="%{ #{'男':'男', '女':'女'} }"></s:radio>
							<s:radio name="gender" list="#{'男':'男', '女':'女'}"></s:radio>
                        	 --%>
							<s:radio name="sex" list="{'男', '女'}"></s:radio>
						</td>
                    </tr>
                    <tr><td  class="addFont">生日</td>
                        <td><s:textfield name="birthday" cssClass="InputStyle"/></td>
                    </tr>

					<tr><td  class="addFont">年级</td>
                        <td><s:textfield name="grade" cssClass="InputStyle"/>
                        <span class="span_note">*</span>
                        </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="提交" onclick="return Check();" cssClass="ttn"></s:submit>
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
