<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>添加我的课程</title>
	<%@include file="/WEB-INF/jsp/public/commons.jspf" %>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
	<script type="text/javascript">
    function MM_Empty(ctrlId, msg) {
        var ctrl = document.getElementById(ctrlId);
        if (!ctrl) return true;
        if (ctrl.value.trim() == "") {
        	//正上方
        	layer.msg('请选择'+msg, {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl.focus();
            return false;
        }
        return true;
    }

	function CheckSelect() {
        if (!MM_Empty('select_class', '班级')) return false;
        layer.load();
    }
	
	</script>
<!-- 根据部门动态加载班级 -->
<script>
	function deptChange() {
		$("#select_class").empty();
		$.ajax({
			type : "post",
			url : "mclass_findByDeptId.action",
			data : {
				"departmentId" : $("#select_dept").val()
			},
			dataType : "json",
			async : true,
			success : function(data) {
				if(data.name=="noClass"){
					//正上方
		        	layer.msg('该系没有班级', {
		        	  offset: 0,
		        	  shift: 6
		        	});
				}
				else if(data.name=="success")
				for (var i = 0; i < data.classes.length; i++)
					$("#select_class").append(
							"<option value='"+data.classes[i].id+"'>" + data.classes[i].className+ "</option>");
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
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学管理 &gt; 添加课程对应班级</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">选课信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<!-- 从已有课程选择 -->
<s:form action="teacher_addCourseClass" method="post">
<s:hidden name="classSelectCourseId"></s:hidden>
<s:hidden  name="courseId"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
                    	<td  class="addFont">
                    		课程
						</td>
						<td>
							${courseName}	
						</td>
					</tr>
                    <tr>
                    	<td  class="addFont">
                    		部门
						</td>
						<td>
							<s:select id="select_dept" name="departmentId" list="#departmentList" cssClass="ddl"
                        		 listKey="id" listValue="deptName" headerKey="" headerValue="" onchange="deptChange();"/>
						</td>
					</tr>
					<tr>
						<td  class="addFont">
							班级
						</td>
                        <td>
                        	<s:select id="select_class" name="classIds" list="#classList" cssClass="ddl" multiple="true"
                        		size="20" listKey="id" listValue="courseName" style="height:200px;"/>
                        	<span class="span_note">*</span>
                        	(按住ctrl键可以多选)
                        </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="提交" onclick="return CheckSelect();" cssClass="ttn"></s:submit>
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
