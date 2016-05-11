<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>学期设置</title>
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
        if (!MM_Empty('txt_termName', '学期名')) return false;
        if (!MM_Empty('txt_startTime', '评价开始时间')) return false;
        if (!MM_Empty('txt_endTime', '评价结束时间')) return false;
        layer.load();
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学质量管理 &gt; 
<s:if test="%{id == null}">
添加评价基本信息
</s:if>
<s:else>
修改评价基本信息
</s:else>
</div>

<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">评价基本信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="term_%{id == null ? 'add' : 'edit'}">
<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
                   <tr><td class="addFont">学期名称</td>
                        <td><s:textfield  name="name" cssClass="inpu" id="txt_termName"/>
                         <span class="span_note"> * </span>
                         </td>
                    </tr>
                    
                    <tr><td class="addFont">评价开始时间</td>
                        <td><s:textfield  name="startTime" cssClass="inpu" id="txt_startTime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',startDate:'1993-01-01'})"/>
							<span class="span_note"> * </span></td>
                    </tr>
                    <tr><td class="addFont">评价结束时间</td>
                        <td><s:textfield name="endTime" cssClass="inpu" cssStyle="inpu" id="txt_endTime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',startDate:'1993-01-01'})"></s:textfield>
                   		<span class="span_note"> * </span></td>
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
