<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设置监考密码</title>
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
    function MM_checklen(ctrlId, msg,len) {
        var ctrl = document.getElementById(ctrlId);
        if (!ctrl) return true;
        if (ctrl.value.trim().length<len) {
        	//正上方
        	layer.msg(msg+'至少为'+len+'位', {
        	  offset: 0,
        	  shift: 6
        	});
            ctrl.focus();
            return false;
        }
        return true;
    }
    function check2password(ctrlId1,ctrlId2){
    	var ctr1 = document.getElementById(ctrlId1).value.trim();
    	var ctr2 = document.getElementById(ctrlId2).value.trim();
    	if(ctr1==ctr2){
    	return true;
    	}
    	layer.msg('两次输入的密码不同！', {
        	  offset: 0,
        	  shift: 6
        	});
    	return false;
    	
    }
    function check(){
    	if(!MM_checklen('input_password','监考密码',4)) return false;
    	if(!check2password('input_password','input_password2')) return false;
    	if(!MM_Empty('TestRoomID','请选择班级')) return false;
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
</script>
</head>

<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt;考试监控  &gt;设置监考密码</div>
<!-- 信息开始 -->
<div class="mframe">
	
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">设置监考密码</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
			<s:form action="testRoom_setpassword" method="post">
			<table cellspacing="0" cellpadding="6"  align="center" border="0">
			<!-- 表头-->
			<tbody> 
					<tr><td  class="addFont"></td></tr>
					<tr><td  class="addFont"></td></tr>
					<tr>
			        <td colspan="2" align="center">
			            <font style="color:red;font-size:16px;">  
			              <s:fielderror fieldName="setPasswordInfo"/>
						</font>
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
	               </tr>
					<tr>
						<td class="addFont">请输入监考密码</td>
                        <td>
                        	<input type="password" name="TeacherPwd"  id="input_password" />
                        </td>
                    </tr>
                    <tr>
                    	<td class="addFont">请确认监考密码</td>
                        <td>
                        	<input type="password" id="input_password2" />
                        </td>
                    </tr>
					<tr><td  class="addFont"></td></tr>
                    <tr><td  class="addFont"></td></tr>
                    <tr>
                    	<td  class="addFont"></td>
                        <td>
                        	<s:submit value="确认设置" onclick="return check();" cssClass="ttn"></s:submit>
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
