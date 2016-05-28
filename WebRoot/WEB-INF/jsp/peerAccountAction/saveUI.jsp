<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>同行评价指标管理</title>
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
        if (!MM_Empty('select_term', '所属学期')) return false;
        if (!MM_Empty('txt_description', '评价描述')) return false;
        if (!MM_Empty('txt_point', '评价占分')) return false;
        layer.load();
    }
	</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 班级管理 &gt; 
<s:if test="%{id == null}">
添加同行评价指标信息
</s:if>
<s:else>
修改同行评价指标信息
</s:else>
</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">同行评价指标信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="peerAccount_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td class="addFont">所属学期</td>
                        <td>
                        	<s:select name="termID" cssClass="ddl" id="select_term"
                        		list="#termList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择学期=="
                        	/>
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr>
                    	<td class="addFont">评价描述</td>
                        <td>
                        	<s:textfield name="description" cssClass="inpu" id="txt_description" />
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">评价占分</td>
                        <td><s:textfield name="points" cssClass="inpu" id="txt_points" />
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
