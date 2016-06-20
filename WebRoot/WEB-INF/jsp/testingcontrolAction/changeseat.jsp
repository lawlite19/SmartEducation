<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>显示所有用户</title>
<%@include file="/WEB-INF/jsp/public/list.jspf"%>
<script>
	function deleteip(id,computerid){
		  if(id.toString().trim()=="") 
		  {
		  	document.getElementById("text_"+id).blur();
		  	return false;
		  }
          if (confirm("确定要删除"+computerid+"号电脑吗?")) {
          $.ajax({ 
               	  type: "post",
               	  url: "computerip_delete.action", 
               	  data: {
               	   		"id" : id,
           	    		}, 
               	    	dataType : "json",
               	    	async : false,
                        success: function(data) { 
   							  if (data.name=="success") {
   		           					alert("删除成功");
   		           				  	layer.load();
   		           				 	window.location.reload();
   		           				  } else {
   		           	                 alert("删除失败");
   		           	              }
               	    		} 
               	       }); 
   					}
	 	}
</script>
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
	
    function locationchange(){
    	var id=$("#location").val();
    	$.ajax({ 
               	type: "post",
               	url: "testRoom_list.action", 
               	data: {
               	    	"location" : id
           	    	  }, 
               	dataType : "json",
               	async : false,
                success: function(data) {
                	$("#TestRoomID").empty();
                	$("#TestRoomID").append(
									"<option value=''>==请选择机房==</option>");
                	if(data.name=="success"){
		   		        var roomlist=data.data;
		   		        for(var i=0;i<roomlist.length;i++){
		   		        	$("#TestRoomID").append(
									"<option value='"+roomlist[i].testRoomID+"'>" +roomlist[i].testRoomName+ "</option>");
		   		        }
		   		    }
					else if(data.name!="null"){
						//正上方
			        	layer.msg('服务器错误', {
			        	  offset: 0,
			        	  shift: 6
			        	});
					}
		   		    
	   		        
               } 
         });  
    }
	
    function Check() {
		return MM_Empty('TestRoomID','请选择班级');
    }
    
    
	</script>
<script type="text/javascript">
	function change(ip,computerid){
		var testroomname=document.getElementById("testroomname").value.trim();
		var stunum=document.getElementById("stunum").value.trim();
		if(stunum==""){
			layer.msg('请输入考生学号', {
			        	  offset: 0,
			        	  shift: 6
			        	});
			return;
		}
		if (confirm("确定要将该考生调整到"+testroomname+"机房的"+computerid+"号电脑吗?")) {
			$.ajax({ 
               	type: "post",
               	url: "testingControl_change.action", 
               	data: {
               	    	"ip" : ip,
               	    	"stunum":stunum,
               	    	"testroomname":testroomname,
               	    	"computerid":computerid
           	    	  }, 
               	dataType : "json",
               	async : false,
                success: function(data) {
                	if(data.name=="success"){
                			layer.msg('考生座位调整成功', {
				        	  offset: 0,
				        	  shift: 6
				        	});
		   		        }
					else if(data.name=="taken"){
						//正上方
			        	layer.msg('该座位已被占用', {
			        	  offset: 0,
			        	  shift: 6
			        	});
					}else{
						layer.msg('服务器错误', {
				        	  offset: 0,
				        	  shift: 6
				        	});
					}
		   		    
	   		        
               } 
         });  
		}
	}
	
</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 监考流程 &gt; 调整考生座位</div>

<div>
<table width="90%" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td class="mm" colspan="3">
            <input type="hidden" name="testroomid" id="testroomid" value="${testroomid}">
			<s:form action="computerip_findcomputeriplisttochange" method="post">
			<table cellspacing="0" cellpadding="6"  border="0">
			<!-- 表头-->
			<tbody> 
					<tr><td  class="addFont"></td></tr>
					<tr><td  class="addFont"></td></tr>
					<tr>
			        <td colspan="2" align="center">
			        </td>
			        </tr>
					<tr>
					<td>
	               		<s:select name="location" id="location" list="#{0:'==请选择校区==',1:'本部', 2:'通灌',3:'东港'}" cssClass="ddl" onchange="locationchange()"/>
	               </td>
	               <td>
	                   <s:select name="TestRoomID" id="TestRoomID" cssClass="ddl" list="#{}" 
	                    headerKey="" headerValue="==请选择机房==" />
	   			   </td>
	               <td>
                        <s:submit value="确认" onclick="return check();" cssClass="ttn"></s:submit>
                   </td>
                   </tr>
				</tbody>
				</table>
				
				</s:form>
			</td>
			</tr>
			</tbody>
			</table>
<!-- 引入选择按钮 -->
<%-- <%@include file="/WEB-INF/jsp/public/list_button.jspf" %> --%>
<!-- 添加按钮 -->
<table width="90%" align="center" cellspacing="3" cellpadding="5">
	<tr>
		<td  class="addFont">请输入考生学号：<input type="text" id="stunum"></td>
	</tr>
</table>
</div>
<input type="hidden" name="testroomname" id="testroomname" value="${testroomname}">
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">${testroomname} 请单机机号选择电脑</span>
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
				<td align="center" id="point">机号</td>
				<td align="center">IP地址</td>
				<td align="center"></td>
				<td align="center">机号</td>
				<td align="center">IP地址</td>
				<td align="center"></td>
				<td align="center">机号</td>
				<td align="center">IP地址</td>
				<td align="center"></td>
				<td align="center">机号</td>
				<td align="center">IP地址</td>
				<td align="center"></td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>
			<s:iterator value="computeriplist" var="val">
				<tr>
				<s:iterator value="val" var="v">
					<td align="center" onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);" onclick="change('${v.IP}','${v.computerId}')">${v.computerId}</td>
					<td align="center" >
						<label id="laber_${v.ID}" >${v.IP}</label>
					</td>
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
</body>
</html>
