<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>保存或修改数据字典</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<!-- 时间选择控件 -->
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
        if (!MM_Empty('select_dataType', '数据字典类型')) return false;
        if (!MM_Empty('txt_dictNum', '数据字典编号')) return false;
        if (!MM_Empty('txt_dictName', '数据字典名称')) return false;
        layer.load();
    }
	</script>
	<script>
	function checkRepeat(){
		
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "dataDict_checkDictNum.action", 
               	    		data: {
               	    			"dictNum" : $("#txt_dictNum").val()
           	    			}, 
               	    		dataType : "json",
               	    		async : true,
                            success: function(data) { 
                            	 //var json = eval("(" + data + ")");
								 var str = data.name;
   								  if (str=="error") {
   		           					layer.msg('数据字典编号重复', {
   		                   	 	 	offset: 0,
   		                   	  		shift: 6
   		                   			});
   		           					$("#txt_dictNum").focus();
   		           					return false;
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
添加数据字典信息
</s:if>
<s:else>
修改数据字典信息
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
<s:form action="dataDict_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id" id="hidden_id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td class="addFont">数据项类型</td>
                        <td>
                        	<s:select id="select_dataType" name="dataTypeId" cssClass="ddl" list="#dataTypeList"
								listKey="id" listValue="dataTypeName" />
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr>
                    	<td class="addFont">数据项编号</td>
                        <td>
                        	<s:textfield name="dictNum" id="txt_dictNum" cssClass="inpu"></s:textfield>
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">数据项名称</td>
                        <td><s:textfield name="dictName" cssClass="inpu" id="txt_dictName" />
                        <span class="span_note">*</span>
						</td>
                    </tr>
                    <tr><td  class="addFont">数据项描述</td>
                        <td>
                        <s:textfield name="description" id="txt_desc" cssClass="inpu"></s:textfield>
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
