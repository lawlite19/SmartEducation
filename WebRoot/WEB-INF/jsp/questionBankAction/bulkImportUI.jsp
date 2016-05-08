<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>题库批量导入</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
    
    <!-- 改变下载链接 -->
    <script type="text/javascript">
    	function ChangeA() {
    		// 1-->判断题；2-->单选题
			var num=$("#select_questionType").val();
    		if(num=="1"){
    			$("#a_download").attr("href","123"); 
    		}
    		else if(num=="2"){
    			$("#a_download").attr("href","345"); 
    		}
    		else{
    			$("#a_download").attr("href","#"); 
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
<s:form action="" method="post">
<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
			        <tr>
                        <td class="addFont">选择信息来源：</td>
                        <td>
                        	<s:select list="#{'1':'判断题题库','2':'单选题题库'}" onchange="ChangeA();" name="questionType" id="select_questionType" cssClass="ddl"></s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="addFont">模&nbsp;板&nbsp;下&nbsp;载&nbsp;：</td>
                        <td>
                        	<s:a href="#" id="a_download">
                        		<span class="blue" style="text-decoration:underline; cursor:pointer">下载模板</span>
                        	</s:a>
                        </td>
                    </tr>
                     <tr>
                        <td class="addFont">选择导入文件：</td>
                        <td>
                     		 <input type="file" accept=".xls,.xlsx" name="questionFile" id="file_xlsFile" />

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
