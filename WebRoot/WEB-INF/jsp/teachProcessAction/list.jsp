<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程进程</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<!--Layer插件弹出对话框-->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
<!--鼠标悬浮变色相关代码开始-->
    <script type="text/javascript">
        var _oldColor;
        function SetNewColor(source) {
            _oldColor = source.style.backgroundColor;
            source.style.backgroundColor = '#E8F5FE';
        }
        function SetOldColor(source) {
            source.style.backgroundColor = _oldColor;
        }
    </script>
<!--鼠标悬浮变色相关代码结束-->
<script>
        (function () {
            	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            	parent.layer.close(index); //再执行关闭 
            	//$("#form_closeLayer").submit();
        })();
</script>
</head>
<body>
<div class="main">
<div style="background:#fff;float:right;position:absolute;top:0;bottom:0;left:50%;right:0;"></div>
<div class="indexBox">
		<div class="picAreaDiv">
			<div style="height:550px;float:left;"></div>

			<div class="label" style="margin: 0;">
				<span id="span_courseName">${courseName}</span>
<!-- 添加教学进程 -->
<!-- 添加按钮 -->
<table align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<!--  <a href="#" onclick="AddTeachProcess('${courseId}')"  style="color:white;text-decoration: none;">添加</a>
			-->
			<s:a action="teachProcess_addUI?courseId=%{courseId}" style="color:white;text-decoration: none;">添加</s:a>
		</td>
	</tr>
</table>
			</div>

<!-- 教学进程信息 -->
			<ul style="overflow:hidden;">
			<div class="mframe">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                    <span class="tt">教学进程信息</span></td>
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
			<tr style="height:30px;">
				<th align="center">序号</th>
				<th align="center">周次</th>
				<th align="center">授课日期</th>
				<th align="center">教学内容</th>
				<th align="center">节次</th>
				<th align="center">教学方式</th>
				<th align="center">教学章节</th>
				<th align="center">操作</th>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="teachProcessList" status="s">
			<tr style="height:30px;" onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}</td>
					<td align="center">${weekCount}&nbsp;</td>
					<td align="center">${startData}&nbsp;</td>
					<td align="center">
					<s:if test="lessonContent.length()>15">
						<a class="lessonContentDetails" id="${lessonContent}"
						style="cursor:pointer;">
							${lessonContent.substring(0,15)}  .....
						</a>
					</s:if>
					<s:else>
						${lessonContent}&nbsp;
					</s:else>
					</td>
					<td align="center">${lessonCount}&nbsp;</td>
					<td align="center">${teachType.dictName}&nbsp;</td>
					<td align="center">${chapter}&nbsp;</td>
					<td  align="center">
					<s:a action="teachProcess_editUI?id=%{id}&courseId=%{courseId}">
						<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
					</s:a>
					|
					<s:a action="teachProcess_delete?id=%{id}&courseId=%{courseId}" onclick="return window.confirm('您确定要删除吗？')">
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
</div>
			</ul>
		</div>
<!-- 我的课程信息 -->
		<ul class="category" style="width:115px;">
			<s:iterator value="couseList" var="num">
				<s:if test="%{courseId==id}">
					<li  class="current">
						<s:a action="teachProcess_list?courseId=%{id}">${courseName}</s:a>
					</li>
					<div class="arrow" style="right: 7px;"></div>
				</s:if>
				<s:else>
					<li>
						<s:a action="teachProcess_list?courseId=%{id}">${courseName}</s:a>
					</li>
				</s:else>
			</s:iterator>
		</ul>
		<div class="clearfix" style="clear:both"></div>
</div>
</div>
<!-- 提交表单 -->
<s:form id="form_closeLayer" action="teachProcess_list" method="post">
	<s:hidden name="courseId" ></s:hidden>
</s:form>
<!-- layer弹出层 
	<script type="text/javascript">
	function AddTeachProcess(courseId) {
		var courseName = $("#span_courseName").html();
		//iframe层
        layer.open({
          type: 2,
          title: '课程名：'+courseName+'',
          shadeClose: true,
          shade: 0.1,
          area: ['600px', '90%'],
          content: 'teachProcess_addUI.action?courseId=' + courseId, //iframe的url
          yes:function(index){
          	  $("#form_closeLayer").submit();
        	  layer.close(index);
          }
        }); 
	}
    </script>-->
<!-- tips加载全部信息 -->
<script type="text/javascript">
(function(){
	//显示教学内容
	$('a.lessonContentDetails').on('click', function (event) {
	    var value = $(this).attr("id");
	  //tips层-左
		 index=layer.tips(value, this, {
		  tips: [1, '#78BA32'],
		  time:4000 //4s关闭
		});
	});

})();

</script>
</body>
</html>
