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
	function show(source,ctrid){
		document.getElementById(source).style.display='none';
		var ip=document.getElementById(source).innerHTML;
		document.getElementById(ctrid).value=ip.toString().trim();
		document.getElementById(ctrid).style.display='block';
		document.getElementById(ctrid).focus();
	}
	
	function modify(source,ctrid,id){
		source.style.display='none';
		
		var ip=source.value.trim();
		var testroomid=$("#testroomid").val();
    	$.ajax({ 
               	type: "post",
               	url: "computerip_modify.action", 
               	data: {
               	    	"id" : id,
               	    	"testroomid":testroomid,
               	    	"ip":ip
           	    	  }, 
               	dataType : "json",
               	async : false,
                success: function(data) {
                	if(data.name=="success"){
	                	document.getElementById(ctrid).innerHTML=ip;
						//正上方
			        	layer.msg('IP修改成功', {
			        	  offset: 0,
			        	  shift: 6
			        	});
					}
					else {
					source.value=$("#"+ctrid).val();
					if(data.name=="ipexist"){
						layer.msg('该IP已存在', {
			        	  offset: 0,
			        	  shift: 6
			        	});
					}
					else if(data.name!="nochange"){
						layer.msg('服务器错误', {
			        	  offset: 0,
			        	  shift: 6
			        	});
					}
		   		   }
	   		   document.getElementById(ctrid).style.display='block';
               } 
         });  
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
								"<option value='"+roomlist[i].testRoomID+
								"'>" +roomlist[i].testRoomName+ "</option>");
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
	function showaddview(){
		layer.confirm("请输入机号<input type='text' id='add_computerid'/><br/><br/>请输入IP:&nbsp;&nbsp;<input type='text' id='add_ip'/><br/><br/><input class='bbtn btn-primary' align='left' onclick='add()' value='确认添加'><br/>", 
        {
            skin: 'layui-layer-molv',
            shift: 2,//动画类型
            offset: 0,
			btn:0
         });
	}
	
	function checkippart(part){//检测IP的每一个段是否有效
		if(part>=0&&part<=255) return true;
		else return false;
	}
	
	function add(){
		var computerid=document.getElementById("add_computerid").value.trim();
		var ip=document.getElementById("add_ip").value.trim();
		var testroomid=document.getElementById("testroomid").value.trim();
		if(testroomid==""){
			alert("请选择机房"); return false;
		}else if(ip==""){
			alert("ip地址不能为空"); return false;
		}else if(testroomid==""){
			alert("电脑编号不能为空"); return false;
		}
		var patrs=ip.split(".");
		if(checkippart(patrs[0])&&checkippart(patrs[1])&&checkippart(patrs[2])&&checkippart(patrs[3])){
		$.ajax({ 
               	  type: "post",
               	  url: "computerip_add.action", 
               	  data: {
               	   		"computerid" : computerid,
               	   		"ip" : ip,
               	   		"testroomid" : testroomid
           	    		}, 
               	    	dataType : "json",
               	    	async : false,
                        success: function(data) { 
   							  if (data.name=="success") {
   		           					alert("添加成功");
   		           				  	layer.load();
   		           				 	window.location.reload();
   		           				  } else if (data.name=="ipexist"){
   		           	                 alert("该IP已存在");
   		           	              } else if (data.name=="computeridexist"){
   		           	                 alert("该电脑已存在");
   		           	              }else{
   		           	              	alert("服务器错误");
   		           	              }
               	    		} 
               	       }); 
       }
       else
       	alert("IP格式非法");
	}
</script>
</head>
<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 考务管理 &gt; 考场IP管理</div>

<div>
<table width="90%" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td class="mm" colspan="3">
            <s:hidden name="testroomid" id="testroomid"/>
			<s:form action="computerip_findcomputeriplist" method="post">
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
		<td class="bbtn btn-primary" align="right" onclick="showaddview()">
			<p style="color:white;text-decoration: none;">添加电脑</p>
		</td>
	</tr>
</table>
</div>
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">${testroomname}电脑IP管理</span>
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
					<td align="center">${v.computerId}</td>
					<td align="center" onmouseover="SetNewColor(this);"
					 onmouseout="SetOldColor(this);" onclick="show('laber_${v.ID}','text_${v.ID}')" 
					ondblclick="deleteip('${v.ID}','${v.computerId}')">
						<label id="laber_${v.ID}" 
						onclick="show('laber_${v.ID}','text_${v.ID}')">${v.IP}</label>
						<input id="text_${v.ID}" type="text" style="display:none; " 
						onblur="modify(this,'laber_${v.ID}','${v.ID}')">
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
