<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>考试信息导入</title>
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
		//验证上传文件格式
		var filePath=$("#file_xsl").val();
		var testpaperid=$("#testpaperid").val().trim();
		if(testpaperid==""){
			//正上方
        	layer.msg('请输入考试编号!', {
        	  offset: 0,
        	  shift: 6
        	});
			return false;
		}
		if(filePath==""){
			//正上方
        	layer.msg('请选择上传文件!', {
        	  offset: 0,
        	  shift: 6
        	});
			return false;
		}
		var index = filePath.lastIndexOf(".");
		//alert(filePath);
		if(index < 0) {
			//正上方
        	layer.msg('上传的文件格式不正确!', {
        	  offset: 0,
        	  shift: 6
        	});
			return false;
		}
		else {
			var ext = filePath.substring(index + 1, filePath.length);
			//alert(ext);
			if((ext!="xls")&&(ext!="xlsx")) {
				//正上方
	        	layer.msg('只能上传xls和xlsx的文件', {
	        	  offset: 0,
	        	  shift: 6
	        	});
				return false;
			}
		}
		$("#fielderror").value="文件正在导入，请稍候...";
		//loading层
		layer.load(0,{
  			shade: [0.5,'#000'] //0.1透明度的白色背景
		});
		return true;
    }
	</script>
<script type="text/javascript">
	function uploadxsl() {
		$("#file_xsl").click();	
	}
</script>
</head>

<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt;考务管理  &gt;考试信息导入 </div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">考试信息导入</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
			<s:form action="testingControl_exceltosql.action" method="post" enctype="multipart/form-data">
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
					<tbody> 
					<tr><td  class="addFont"></td></tr>
					<tr><td  class="addFont"></td></tr>
					<tr><td  class="addFont"></td></tr>
					<tr>
			        <td colspan="2" style="text-align: center;color:red;">
                          <s:fielderror fieldName="exceltosqlInfo"/>
                          <p id="fielderror"></p>
                    </td>
        			</tr>
        			<tr>
        			<td  class="addFont">请输入考试编号:</td>
        			<td  class="addFont"><input type="text" id="testpaperid" name="testpaperid"/></td>
        			</tr>
        			<tr>
                        <td class="addFont">模&nbsp;板&nbsp;下&nbsp;载&nbsp;：</td>
                        <td>
                        	<a href="${pageContext.request.contextPath}/exceltosqlUpload/ExceltosqlTemplate.xls" id="a_download">
                        		<span class="blue" style="text-decoration:underline; cursor:pointer">下载模板</span>
                        	</a>
                        </td>
                    </tr>
					<tr>
						<td class="addFont">选择导入文件：</td>
                        <td>
                        	<input type="file" accept=".xls,.xlsx" name="file"  id="file_xsl" />
                        </td>
                         
                    </tr>
                   <tr>
			        <td colspan="2" style="text-align: center;color:red;">
                          <p>注意：请将不同考试的考生分开导入！</p>
                    </td>
        			</tr>
					<tr><td  class="addFont"></td></tr>
                    <tr><td  class="addFont"></td></tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="导入数据" onclick="return Check();" cssClass="ttn"></s:submit>
                        	
                        </td>
                    </tr>
		</tbody>
	</table>
	</s:form>
	</tr>
	</tbody>
     </table>
</div>

</body>
</html>
