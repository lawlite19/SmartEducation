<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>单选题信息</title>
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
		if (!MM_Empty('txt_teacherNum', '老师工号')) return false;
        if (!MM_Empty('select_course', '所属课程')) return false;
        if (!MM_Empty('txt_knowledgeName', '知识点')) return false;
        if (!MM_Empty('txt_answerA', '答案A')) return false;
        if (!MM_Empty('txt_answerB', '答案B')) return false;
        if (!MM_Empty('txt_answerC', '答案C')) return false;
        if (!MM_Empty('txt_answerD', '答案D')) return false;
        if (!MM_Empty('radio_answer', '正确答案')) return false;
        
        layer.load();
    }
	</script>
	<!-- 根据课程动态加载章节 -->
<script>
	function chapterChange() {
		$("#select_chapter").empty();
		$.ajax({
			type : "post",
			url : "chapter_findByCourseId.action",
			data : {
				"courseId" : $("#select_course").val()
			},
			dataType : "json",
			async : true,
			success : function(data) {
				if(data.name=='success'){
					for (var i = 0; i < data.length; i++)
						$("#select_chapter").append(
								"<option value='"+data.chapters[i].id+"'>" + data.chapters[i].className+ "</option>");
				}
				else if(data.name=='noChapter'){
					//正上方
		        	layer.msg('该课程没有章节信息（章节非必填）', {
		        	  offset: 0,
		        	  shift: 6
		        	});
				}
				else{
					//正上方
		        	layer.msg('未知错误', {
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
<div class="crumd"><a href="" id="A1">首页</a> &gt; 题库管理 &gt; 单选题管理 &gt;
<s:if test="%{id == null}">
添加单选题信息
</s:if>
<s:else>
修改单选题信息
</s:else>
</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">单选题信息</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="singleChoice_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr><td  class="addFont">老师工号</td>
                        <td>
                        <s:textfield name="teacherNum" cssClass="inpu" id="txt_teacherNum"/>
                        	<span class="span_note">*</span>
						</td>
                    </tr>
					<tr>
						<td class="addFont">所属课程</td>
                        <td>
                        
                        	<s:select name="courseId" cssClass="ddl" id="select_course"
                        		list="#courseList" listKey="id" listValue="courseName"
                        		headerKey="" headerValue="==请选择课程=="
                        		onchange="chapterChange();"
                        	/> 
                        	<span class="span_note">*</span>
                        </td>
                    </tr>
                    <tr><td  class="addFont">题目</td>
                        <td><s:textfield name="question" cssClass="inpu" id="txt_question" cssStyle="display:none;"/>
                        	<iframe id="eWebEditor1" src="${pageContext.request.contextPath}/ewebeditor/ewebeditor.htm?id=txt_question&style=standard"  frameborder="0"  scrolling="no"  width="540" height="300"></iframe>
                        	<span class="span_note">*</span>
                        </td>
                        
                    </tr>
                    <tr>
                    	<td class="addFont">章节</td>
                        <td>
                        	<s:select name="chapterId" cssClass="ddl" id="select_chapter"
                        		list="#chapterList" listKey="id" listValue="chapterName"
                        		headerKey="" headerValue="">
                        	</s:select>
                        </td>
                    </tr>
                    <tr><td  class="addFont">知识点</td>
                        <td><s:textfield name="knowledgeName" cssClass="inpu" id="txt_knowledgeName" />
                        <span class="span_note">*</span>
						</td>
                    </tr>
                   <tr><td  class="addFont">答案A</td>
                        <td>
                       		<s:textarea name="answerA" id="txt_answerA" cssClass="inpu" cssStyle="height:100px;width:400px;"></s:textarea>
							<span class="span_note">*</span>
						</td>
                    </tr>
                     <tr><td  class="addFont">答案B</td>
                        <td>
                       		<s:textarea name="answerB" id="txt_answerB" cssClass="inpu" cssStyle="height:100px;width:400px;"></s:textarea>
							<span class="span_note">*</span>
						</td>
                    </tr>
                     <tr><td  class="addFont">答案C</td>
                        <td>
                       		<s:textarea name="answerC" id="txt_answerC" cssClass="inpu" cssStyle="height:100px;width:400px;"></s:textarea>
							<span class="span_note">*</span>
						</td>
                    </tr>
                     <tr><td  class="addFont">答案D</td>
                        <td>
                       		<s:textarea name="answerD" id="txt_answerD" cssClass="inpu" cssStyle="height:100px;width:400px;"></s:textarea>
							<span class="span_note">*</span>
						</td>
                    </tr>
					<tr><td  class="addFont">正确答案</td>
                        <td>
							<s:radio name="answer" list="#{'A' : 'A', 'B' : 'B','C':'C','D':'D'}" id="radio_answer"></s:radio>
							<span class="span_note">*</span>
						</td>
                    </tr>
                    <tr><td  class="addFont">难度指数</td>
                        <td>
                       		<s:select name="difExponent" list="#{1:'1级',2:'2级',3:'3级',4:'4级',5:'5级'}" cssClass="ddl"></s:select>
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
