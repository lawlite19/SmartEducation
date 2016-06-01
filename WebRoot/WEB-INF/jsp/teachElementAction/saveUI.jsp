<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>教学要素管理</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />

</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 教学要素管理 &gt; 
<s:if test="%{id == null}">
添加教学要素信息
</s:if>
<s:else>
修改教学要素信息
</s:else>
</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">教学要素信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="teachElement_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
                    <tr>
                    	<td class="addFont">数据项编号</td>
                        <td>
                        	<s:textfield name="dictNum" cssClass="inpu" id="txt_description" />
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">教学要素名称</td>
                        <td><s:textfield name="dictName" cssClass="inpu" id="txt_allPoint" />
                        <span class="span_note">*</span>
						</td>
                    </tr>
                    <tr>
                    	<td class="addFont">描述</td>
                        <td>
                        	<s:textfield name="description" cssClass="inpu" id="txt_descriptionA" />
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
