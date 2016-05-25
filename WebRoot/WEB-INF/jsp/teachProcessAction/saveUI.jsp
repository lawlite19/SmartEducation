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
        if (!MM_Empty('select_weekCount', '周次')) return false;
        if (!MM_Empty('txt_startData', '授课日期')) return false;
        if (!MM_Empty('select_lessonCount', '上课节次')) return false;
        if (!MM_Empty('select_teachType', '教学方式')) return false;
        layer.load();
    }
	</script>
	<!-- 根据部门动态加载班级select -->
<script>
	function classChange() {
		$("#select_class").empty();
		$.ajax({
			type : "post",
			url : "mclass_findByDeptId.action",
			data : {
				"departmentId" : $("#select_dept").val()
			},
			dataType : "json",
			async : false,
			success : function(data) {
				//alert(data[0].id);
				//alert(data[0].className);
				//alert(data.length);
				//var json = eval("(" + data + ")");
				for (var i = 0; i < data.length; i++)
					$("#select_class").append(
							"<option value='"+data[i].id+"'>" + data[i].className+ "</option>");
			}
		});
	};
</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd">教学管理 &gt; 
<s:if test="%{id == null}">
添加教学进程
</s:if>
<s:else>
修改教学进程
</s:else>
</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">教学进程信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="teachProcess_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
    	<s:hidden name="courseId"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
				<tr>
						<td class="addFont">选择系别</td>
                        <td>
                        	<s:select name="departmentId" list="departmentList" cssClass="ddl" id="select_department" headerKey=""
                        	headerValue="==请选择系别==" listKey="id" listValue="deptName">
                        	</s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>	
			<tr>
						<td class="addFont">周次</td>
                        <td>
                        <s:select name="weekCount" list="#{1:'第1周',2:'第2周',3:'第3周',4:'第4周',5:'第5周',
                        								   6:'第6周',7:'第7周',8:'第8周',9:'第9周',10:'第10周',11:'第11周',
                        								   12:'第12周',13:'第13周',14:'第14周',15:'第15周',16:'第16周',
                        								   17:'第17周',18:'第18周',19:'第19周',19:'第19周'}" cssClass="ddl" id="select_weekCount"></s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>	
			<tr>
						<td class="addFont">授课日期</td>
                        <td>
                        	<s:textfield id="txt_startData" name="startData" cssClass="inpu" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
                        	<span class="span_note">*</span>
                        </td>
			</tr>
			
			<tr>
						<td class="addFont">上课节次</td>
                        <td>
                        <s:select name="lessonCount" list="#{1:'1节',2:'2节',3:'3节',4:'4节',5:'5节',
                        								   6:'6节',7:'7节',8:'8节',9:'9节',10:'10节'}" cssClass="ddl" id="select_weekCount">
                        </s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>
			<tr>
						<td class="addFont">教学方式</td>
                        <td>
                        	<s:select name="teachTypeId" cssClass="ddl" id="select_teachType"
                        		list="#teachTypeList" listKey="id" listValue="dictName"
                        		headerKey="" headerValue="==请选择教学方式==">
                        	</s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>
			<tr>
						<td class="addFont">教学章节</td>
                        <td>
                        	<s:select name="chapterId" cssClass="ddl" id="select_chapter"
                        		list="#chapterList" listKey="id" listValue="chapterName"
                        		headerKey="" headerValue="==请选择课程章节==">
                        	</s:select>
                        	<span class="span_note">*</span>
                        </td>
			</tr>
			<tr>
						<td class="addFont">教学内容</td>
                        <td>
                        	<s:textfield name="lessonContent" id="txt_lessonCount" cssStyle="display:none"></s:textfield>
                        	<iframe id="eWebEditor1" src="${pageContext.request.contextPath}/ewebeditor/ewebeditor.htm?id=txt_lessonCount&style=standard"  frameborder="0"  scrolling="no"  width="340" height="200"></iframe>
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
        <script>
            (function () {
                $('#btnCancel').click(function () {
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                	parent.layer.close(index); //再执行关闭 
                });
            })();
        </script>
</body>
</html>
