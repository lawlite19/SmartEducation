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
        if (!MM_Empty('select_class', '所属班级')) return false;
        if (!MM_Empty('txt_stuNum', '登录名')) return false;
        if (!MM_Empty('txt_stuName', '姓名')) return false;
        if (!MM_Empty('txt_grade', '年级')) return false;
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
				if(data.name=="success"){
					for (var i = 0; i < data.classes.length; i++)
						$("#select_class").append(
								"<option value='"+data.classes[i].id+"'>" + data.classes[i].className+ "</option>");
				}
				else if(data.name=="noClass"){
					//正上方
		        	layer.msg('该部门没有班级', {
		        	  offset: 0,
		        	  shift: 6
		        	});
				}
				else{
					//正上方
		        	layer.msg('服务器错误', {
		        	  offset: 0,
		        	  shift: 6
		        	});
				}
			}
		});
	};
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
                        <span class="tt">学生信息</span>
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
                        	<s:select name="departmentId" cssClass="ddl" id="select_dept"
                        		list="#departmentList" listKey="id" listValue="deptName"
                        		headerKey="" headerValue="==请选择部门=="
                        		onchange="classChange();"
                        	/>
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr>
                    	<td class="addFont">所属班级</td>
                        <td>
                        	<s:select name="classId" cssClass="ddl" id="select_class"
                        		list="#classList" listKey="id" listValue="className"
                        		headerKey="" headerValue="">
                        	</s:select>
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">登录名</td>
                        <td><s:textfield name="stuNum" cssClass="inpu" id="txt_stuNum" />
                        <span class="span_note">*</span>
							（登录名要唯一，设置为学号或工号）
						</td>
                    </tr>
                    <tr><td  class="addFont">姓名</td>
                        <td><s:textfield name="stuName" cssClass="inpu" id="txt_stuName"/>
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
                        <td><s:textfield name="birthday" cssClass="inpu" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',startDate:'1993-01-01'})"/></td>
                    </tr>

					<tr><td  class="addFont">年级</td>
                        <td><s:textfield name="grade" cssClass="inpu" id="txt_grade"/>
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
