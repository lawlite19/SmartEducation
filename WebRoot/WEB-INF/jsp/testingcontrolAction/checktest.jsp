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
    
	</script>
<script type="text/javascript">
	function showaddview(){
		layer.confirm("考试延时<input type='text' id='delay'/>分钟<br/><br/><input class='bbtn btn-primary' align='right' onclick='delay()' value='确认延时'><br/>", 
        {
            skin: 'layui-layer-molv',
            shift: 2,//动画类型
            offset: 0,
			btn:0
         });
	}
	
	function delay(){
		var delaytime=document.getElementById("delay").value.trim();
		if(delaytime==""){
			alert("请输入延迟时间"); return false;
		}
		$.ajax({
               	  type: "post",
               	  url: "testingControl_delay.action", 
               	  data: {
               	   		"delaytime" : delaytime
           	    		}, 
               	    	dataType : "json",
               	    	async : false,
                        success: function(data) { 
   							  if (data.name=="success") {
   		           					alert("延时成功");
   		           				  } else if (data.name=="testpaperidisnotexist"){
   		           	                 alert("请先选择安排考试座位");
   		           	              } else{
   		           	              	alert("服务器错误");
   		           	              }
               	    		} 
               	       }); 
	}
	
	function testover(){
		if (confirm("确定收卷结束吗?")) {
			$.ajax({ 
               	  type: "post",
               	  url: "testingControl_testover.action", 
               	    	dataType : "json",
               	    	async : false,
                        success: function(data) { 
   							  if (data.name=="success") {
   							  		alert("收卷成功");
   							  		window.location="testRoom_first.action";
   		           				  } else{
   		           	              	alert("服务器错误");
   		           	              }
               	    		} 
               	       }); 
		}
	}
</script>
</head>

<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt;考试监控 &gt;收卷检测</div>


<table width="90%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tr"></td>
                </tr>
                <tr>
                    <td class="mm" colspan="3">
			<table align="left" cellspacing="1" cellpadding="1">
			     <tbody>
			     	 <tr>
					   <td width="1000px">&nbsp;</td>
                       <td class="bbtn btn-primary"  colspan="7" onclick="testover()">
							<p style="color:white;text-decoration: none;">完成收卷</p>
					   </td>
                   </tr>
		    </tbody>
		</table>
		</td>
		</tr>
		</tbody>
		</table>
		
<!-- 信息开始 -->
<div class="mframe">
<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">交卷失败的考生</span>
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
				<td align="center">机号</td>
				<td align="center">照片</td>
				<td align="center">姓名</td>
				<td align="center">状态</td>
				<td align="center"></td>
				<td align="center">机号</td>
				<td align="center">照片</td>
				<td align="center">姓名</td>
				<td align="center">状态</td>
				<td align="center"></td>
				<td align="center">机号</td>
				<td align="center">照片</td>
				<td align="center">姓名</td>
				<td align="center">状态</td>
				<td align="center"></td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="testers" var="val">
				<tr><!-- onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);" -->
				<s:iterator value="val" var="v">
					<td align="center">${v.computernum}</td>
					<td align="center" >
					<s:if test="#v.photo!=''">
						<img id="img_photo"  alt="头像" src="studentImgs/${v.photo}" width="84px" height="84px" />
					</s:if>
					<s:elseif test="#v.photo==''">
						<img id="img_photo"  alt="未上传头像" src="${pageContext.request.contextPath}/style/images/default.gif" width="84px" height="84px" />
					</s:elseif>
					</td>
					<td align="center" >${v.name }</td>
					<s:if test="#v.state=='在考'">
						<td align="center" id="${v.stunum }" style="color: red;">交卷异常</td>
					</s:if>
					<s:elseif test="#v.state=='交卷失败'">
						<td align="center" id="${v.stunum }" style="color: red;">交卷异常</td>
					</s:elseif>
					<td align="center" ></td>
				</s:iterator>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
</td>
</tr>
</tbody>
</table>
	<!-- 分页页码 -->
	<%-- <%@include file="/WEB-INF/jsp/public/pageView.jspf"%> --%>

	</div>

</body>
</html>
