<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>题库批量导入</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
    
<!-- 检查上传文件与选择信息是否符合 -->
<script type="text/javascript">
	String.prototype.endWith=function(endStr){
	  var d=this.length-endStr.length;
	  return (d>=0&&this.lastIndexOf(endStr)==d);
	};
	function Check() {
		//验证上传文件格式
		var filePath=$("#file_xlsFile").val();
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
		//验证上传文件名
		var num=$("#select_questionType").val();
		var index1 = filePath.lastIndexOf("\\");
		var fileName = filePath.substring(index1 + 1, filePath.length);
		//alert(fileName);
		//return false;
		if(num=="1"){
			if(fileName!="JudgementTemplate.xlsx"){
				//正上方
	        	layer.msg('您选择的信息来源与上传的文件不符！', {
	        	  offset: 0,
	        	  shift: 6
	        	});
	            return false;
			}
		}
		else{
			if(fileName!="SingleChoiceTemplate.xlsx"){
				//正上方
	        	layer.msg('您选择的信息来源与上传的文件不符！', {
	        	  offset: 0,
	        	  shift: 6
	        	});
	            return false;
			}
		}
		return true;
		//loading层
		layer.load(0,{
  			shade: [0.5,'#000'] //0.1透明度的白色背景
		});
    }
	</script>
    
    <!-- 改变下载链接 -->
    <script type="text/javascript">
    	function ChangeA(path) {
    		// 1-->判断题；2-->单选题
			var num=$("#select_questionType").val();
    		if(num=="1"){
    			$("#a_download").attr("href",path+"/questionBankTemplate/JudgementTemplate.xlsx"); 
    			$("#form_questionBank").attr("action","judgement_bulkImport.action");
    		}
    		else if(num=="2"){
    			$("#a_download").attr("href",path+"/questionBankTemplate/SingleChoiceTemplate.xlsx"); 
    			$("#form_questionBank").attr("action","singleChoice_bulkImport.action");
    		}
    		else{
    			$("#a_download").attr("href","#"); 
    			$("#form_questionBank").attr("action","");
    		}
		}
    </script>
  </head>
  
  <body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 题库管理 &gt; 题库批量导入</div>
<!--显示表单内容-->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">导入题库信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="judgement_bulkImport" method="post" id="form_questionBank" enctype="multipart/form-data">
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
			        <tr>
                        <td class="addFont">选择信息来源：</td>
                        <td>
                        	<select onchange="ChangeA('${pageContext.request.contextPath}');" name="questionType" id="select_questionType" class="ddl">
                        		<option value="1">判断题题库</option>
                        		<option value="2">单选题题库</option>
                        	</select>
                        </td>
                    </tr>
                    <tr>
                        <td class="addFont">模&nbsp;板&nbsp;下&nbsp;载&nbsp;：</td>
                        <td>
                        	<a href="${pageContext.request.contextPath}/questionBankTemplate/JudgementTemplate.xlsx" id="a_download">
                        		<span class="blue" style="text-decoration:underline; cursor:pointer">下载模板</span>
                        	</a>
                        </td>
                    </tr>
                     <tr>
                        <td class="addFont">选择导入文件：</td>
                        <td>
                     		 <input type="file" accept=".xls,.xlsx" name="questionBank" id="file_xlsFile" />
                        </td>
                    </tr>
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="导入数据" cssClass="ttn" onclick="return Check();"></s:submit>
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
