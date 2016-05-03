<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>学生管理</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<!-- 时间选择控件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
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
        if (!MM_Empty('select_dept', '所属部门')) return false;
        if (!MM_Empty('txt_teaNum', '登录名')) return false;
        if (!MM_Empty('txt_teaName', '姓名')) return false;
        if (!MM_Empty('select_role', '角色')) return false;
        layer.load();
    }
	</script>
	<!-- 根据部门动态加载班级select -->
<script>
	function classChange() {
		$("#select_class").empty();
		$.ajax({
			type : "post",
			url : "mclass_findByDeptId.action",
			data : {
				"departmentId" : $("#select_dept").val()
			},
			dataType : "json",
			async : false,
			success : function(data) {
				//alert(data[0].id);
				//alert(data[0].className);
				//alert(data.length);
				//var json = eval("(" + data + ")");
				for (var i = 0; i < data.length; i++)
					$("#select_class").append(
							"<option value='"+data[i].id+"'>" + data[i].className+ "</option>");
			}
		});
	};
</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 学生管理 &gt; 
<s:if test="%{id == null}">
添加老师信息
</s:if>
<s:else>
修改老师信息
</s:else>
</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">老师信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="teacher_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td class="addFont">所属部门</td>
                        <td>
                        	<s:select name="departmentId" cssClass="ddl" id="select_dept"
                        		list="#departmentList" listKey="id" listValue="deptName"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    
                    <tr><td  class="addFont">登录名</td>
                        <td><s:textfield name="teaNum" cssClass="inpu" id="txt_teaNum" />
                        <span class="span_note">*</span>
							（登录名要唯一，设置为学号或工号）
						</td>
                    </tr>
                    <tr><td  class="addFont">姓名</td>
                        <td><s:textfield name="teaName" cssClass="inpu" id="txt_teaName"/>
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
                    <tr><td  class="addFont">职称</td>
                    <td><s:textfield name="title" cssClass="inpu" id="txt_title"/>
                        </td>
                    </tr>
                    <tr><td  class="addFont">生日</td>
                        <td><s:textfield name="birthday" cssClass="inpu" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',startDate:'1993-01-01'})"/></td>
                    </tr>

					<tr><td  class="addFont">来校时间</td>
                        <td><s:textfield name="inTime" cssClass="inpu" id="txt_inTime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',startDate:'1993-01-01'})"/>
                        </td>
                        
                    </tr>
                    <tr><td  class="addFont">最终学历</td>
                        <td><s:textfield name="finalEdu" cssClass="inpu" id="txt_finalEdu"/>
                        </td>
                        
                    </tr>
                    <tr>
					<td class="addFont">角色</td>
                        <td>
                        	<s:select name="roleIds" cssClass="ddl" id="select_role"
                        		multiple="true" size="10" cssStyle="height:100px;"
                        		list="#roleList" listKey="id" listValue="roleName"
                        	/>
                        	<span class="span_note">*</span>
							(按住Ctrl键可以多选或取消选择)
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
