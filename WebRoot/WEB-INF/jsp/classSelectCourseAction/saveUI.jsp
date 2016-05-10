<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>教学进程信息</title>
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
        if (!MM_Empty('select_class', '班级')) return false;
        if (!MM_Empty('teacherNum', '老师工号')) return false;
        if (!MM_Empty('select_course', '课程')) return false;
        layer.load();
    }
	</script>
	<!-- 根据部门动态加载班级select -->
<script>
	function loadCourse() {
		var teacherNum=$("#txt_teacherNum").val().trim();
		if(teacherNum==""){
			//正上方
        	layer.msg('请输入老师工号', {
        	  offset: 0,
        	  shift: 6
        	});
			return;
		}
		$("#select_course").empty();
		$.ajax({
			type : "post",
			url : "teacher_findTeacherCourse.action",
			data : {
				"teacherNum" : teacherNum
			},
			dataType : "json",
			async : true,
			success : function(data) {
				if(data.name=="success"){
					for (var i = 0; i < data.courses.length; i++)
						$("#select_course").append(
								"<option value='"+data.courses[i].id+"'>" + data.courses[i].courseName+ "</option>");
				}
				else if(data.name=="noTeacher"){
					//正上方
		        	layer.msg('没有此老师', {
		        	  offset: 0,
		        	  shift: 6
		        	});
				}
				else if(data.name=="noCourse"){
					//正上方
		        	layer.msg('此老师没有课程', {
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
<div class="crumd">班级选课管理 &gt; 
<s:if test="%{id == null}">
添加班级选课
</s:if>
<s:else>
修改班级选课
</s:else>
</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">班级选课信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="classSelectCourse_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
			<tr>
						<td class="addFont">班级</td>
                        <td>
                        <s:select name="classId" list="classList" cssClass="ddl" id="select_class"
                        	listKey="id" listValue="className"
                       	 headerKey="" headerValue="==选择班级=="
                        ></s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>	
			<tr>
						<td class="addFont">老师工号</td>
                        <td>
                        	<s:textfield id="txt_teacherNum" name="teacherNum" cssClass="inpu" />
                        	<span class="span_note">*</span>
                        	<input type="button" value="加载课程" class="bbtn btn-success" onclick="loadCourse();" />
                        </td>
			</tr>
			
			<tr>
						<td class="addFont">课程</td>
                        <td>
                        <s:select id="select_course" name="courseId" list="courseList" headerKey="" headerValue=""
                        listKey="id" listValue="courseName"
                         cssClass="ddl" >
                        </s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="提交" onclick="return Check();" cssClass="ttn"></s:submit>
                        	&nbsp;&nbsp;&nbsp;
                        	<input id="btnCancel" type="button" class="ttn" onclick="javascript:history.go(-1);" value="返回" />
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
