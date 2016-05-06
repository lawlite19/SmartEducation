<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程进程</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<!-- layer弹窗插件 -->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/search.css" />

<script>
	$(function() {
             $("#deleteSelected").click(function(){
            	 $("input:checked").each(function(){
            	 	 var value=$(this).val().split(",");
           	    	 //alert(value[0]);
           	    	 //alert(value[1]);
           	    	 if (confirm("确定要删除"+value[1]+"吗?")) {
           	    		//if(value[1]=="admin"){
           				//	alert("该用户为超级管理员,不能删除！");
           				//	return;
           				//}
           	    		$.ajax({ 
               	    		type: "post",
               	    		url: "student_bulkDelete.action", 
               	    		data: {
               	    			"id" : value[0]
           	    			}, 
               	    		dataType : "json",
               	    		async : false,
                            success: function(data) { 
                            	 //var json = eval("(" + data + ")");
								 var str = data.name;
   								  if (str=="ok") {
   		           				  	alert("删除成功");
   		           				  	layer.load();
   		           				 	window.location.reload();
   		           				  } else {
   		           	                 alert("删除失败");  
   		           				}
               	    		} 
               	       }); 
   					}
        	    	 
        	     });
        	   //window.location.reload();
      });

	});

</script>
<!-- 查询输入框检查 -->
<script type="text/javascript">
    function MM_Empty(ctrl1Id, ctrl2Id) {
        var ctrl1 = document.getElementById(ctrl1Id);
        var ctrl2 = document.getElementById(ctrl2Id);
        if ((ctrl1.value.trim() == "")&&(ctrl2.value.trim() == "")) {
        	//正上方
        	layer.msg('请输入查询条件！', {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl1.focus();
            return false;
        }
        layer.load();
        return true;
    }
	function Check() {
		return MM_Empty('select_dept', 'txt_inputTerm');
    }
	</script>
</head>
<body>
<div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">

		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label" style="margin: 0;">
			<s:if test="%{name!=null}">
				${name}
			</s:if>
			<s:else>
				全部
			</s:else>
<!-- 搜索框 -->
<div class="container">
<s:form action="spiderCourse_show" method="post">
<input type="text" name="searchInfo" id="txt_search2">
<input class="button" onclick="return Check2();" type="submit" value="搜索">
</s:form>
</div>
			</div>
<!-- 教学进程信息 -->
			<ul style="overflow:hidden;">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">用户账号管理</span>
                    </td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
	<table class="grid" cellspacing="0" cellpadding="6" rules="all" itemstyle-cssclass="tdbg" align="center" border="1" id="gvUserInfo">
		<!-- 表头-->
		<thead>
			<tr>
				<td align="center">选择</td>
				<td align="center">序号</td>
				<td align="center">章节名称</td>
				<td align="center">开始日期</td>
				<td align="center">周次</td>
				<td align="center">节次</td>
				<td align="center">教学内容</td>
				<td align="center">教学方式</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="teachProcessList" status="s">
			<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td  align="center">
						<input type="checkbox" name="checkbox" class="checkbox" value="${id},${chapter}" />
					</td>
					<td align="center">${s.count}</td>
					<td align="center">${startData}&nbsp;</td>
					<td align="center">${weekCount}&nbsp;</td>
					<td align="center">${lessonCount}&nbsp;</td>
					<td align="center">${lessonContent}&nbsp;</td>
					<td align="center">${teachType.dictName}&nbsp;</td>
					<td  align="center">
					<s:a action="teachProcess_editUI?id=%{id}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="teachProcess_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">
						<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
					</s:a>
						&nbsp;</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	</div>
</td>
</tr>
</tbody>
</table>
			</ul>
		</div>
<!-- 我的课程信息 -->
		<ul class="category">
			<s:iterator value="couseList" var="num">
				<s:if test="%{courseId==id}">
					<li  class="current">
						<s:a action="teachProcess_list?courseId=${id}">${name}</s:a>
					</li>
					<div class="arrow"></div>
				</s:if>
				<s:else>
					<li>
						<s:a action="teachProcess_list?courseId=${id}">${name}</s:a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</div>
</body>
</html>
