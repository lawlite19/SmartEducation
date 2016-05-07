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
        	layer.msg('请填写'+msg, {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl.focus();
            return false;
        }
        return true;
    }
    function MM_Float(ctrlId, msg){
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
        var reg=/^(-?\d+)(\.\d+)?$/;
        if(!reg.test(ctrl.value.trim())){
        	//正上方
        	layer.msg('请填写数字', {
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
        if (!MM_Empty('txt_courseName', '课程名称')) return false;
		if (!MM_Float('txt_credit', '学分')) return false;
       
        layer.load();
    }
	function CheckSelect() {
        if (!MM_Empty('select_course', '课程')) return false;
        layer.load();
    }
	
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学管理 &gt; 添加我的课程</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">课程信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<!-- 从已有课程选择 -->
<fieldset>
	<legend>从已有课程选择</legend>
<s:form action="course_addMyCourse" method="post">
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					
                    <tr><td  class="addFont">课程描述</td>
                        <td>
                        	<s:select id="select_course" name="courseIds" list="#courseList" cssClass="ddl" multiple="true"
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
</fieldset>

<!-- 添加新的课程 -->
<fieldset>
<legend>添加新的课程</legend>
<s:form action="course_addNewMyCourse" method="post">
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td class="addFont">所属部门</td>
                        <td>
                        	<s:select name="departmentIds" cssClass="ddl" id="select_dept"
                        		list="#departmentList" multiple="true" size="10" listKey="id" listValue="deptName"
                        		cssStyle="height:100px;"
                        	/>
                        	<span class="span_note">*</span>
                        	(按住Ctrl键可以多选或取消选择)
                        </td>
                    </tr>

                    <tr><td  class="addFont">课程名称</td>
                        <td><s:textfield name="courseName" cssClass="inpu" id="txt_courseName" />
                        <span class="span_note">*</span>
						</td>
                    </tr>
                    <tr><td  class="addFont">学分</td>
                        <td><s:textfield name="credit" cssClass="inpu" id="txt_credit" />
                        <span class="span_note">*</span>
						</td>
                    </tr>
                    <tr><td  class="addFont">课程描述</td>
                        <td><s:textarea name="description" cssClass="inpu" id="txt_description" cssStyle="height:80px;width:350px"/>
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
</fieldset>

	</div>
	</td>
	</tr>
	</tbody>
     </table>
</div>

</body>
</html>
