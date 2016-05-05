<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人信息维护</title>
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
        if (!MM_Empty('txt_stuName', '姓名')) return false;
        layer.load();
    }
	</script>
<script type="text/javascript">
	function uploadPhoto() {
		$("#file_picture").click();	
	}
</script>

<script>
//得到图片的真实路径--IE，Firefox兼容
function getPath(obj) {
 if (obj) {
  if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
   obj.select();
   return document.selection.createRange().text;
  } else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
   if (obj.files) {
    return obj.files.item(0).getAsDataURL();
   }
   return obj.value;
  }
  return obj.value;
 }
}
//检查图片大小
function CheckFileSize(photoUrl,fileObj)       
{     
	var FileMaxSize = 1*1024*1024;//限制上传的文件大小 1M
    if(photoUrl==" ")return false;       
    var   img   =   new   Image();       
    img.src   =   photoUrl;       
    //alert("高   =   "+   img.height   +   "\n宽   =   "+   img.width);  
    var fileSize = img.fileSize || fileObj.files[0].fileSize || fileObj.files[0].size; 
    if(fileSize>FileMaxSize){
    	//正上方
    	layer.msg('图片大小大于1M！请重新选择！', {
    	  offset: 0,
    	  shift: 6
    	});
    	return false;
    }       
    return   true;       
} 
    $(function(){
    	var FileReader = window.FileReader;
    	$("#file_picture").change(function(){
    		if (!/\.((jpg)|(bmp)|(gif)|(png))$/ig.test($("#file_picture").val())) {
    	      //正上方
            	layer.msg('只能上传jpg,bmp,gif,png格式图片!', {
            	  offset: 0,
            	  shift: 6
            	});
    	        return;
    	    }
    		if (FileReader) {//chrome浏览器处理
				var reader = new FileReader(),
				file = this.files[0];
				reader.onload = function(e) {
					var realImgUrl=e.target.result;
					var fileObj=document.getElementById("file_picture");
					if(!CheckFileSize(realImgUrl,fileObj)){
						return;
					}
					
					//这里是把图片转成64位数据存入<img>中的src里
					layer.confirm("<img id='img_photo' style='cursor: pointer;' src='"+realImgUrl+"' width='220px' height='200px' onclick='uploadPhoto();' />", {
						skin: 'layui-layer-rim', //加上边框
						area: ['370px', '58%'], //宽高
						btn: ['确定','取消'] //按钮
	                  }, function(){
	                  	//确定
	                  	$("#img_photo").remove();
	                  	$("#td_img").append("<img id='img_photo' style='cursor: pointer;' src='"+realImgUrl+"' width='220px' height='200px' onclick='uploadPhoto();' />");
	                  	layer.msg('完成', {icon: 1});
	                  }, function(){
	                  	//取消
	                    
	                  });
				};
				reader.readAsDataURL(file);
				//这里需要延迟一下，否则无法放入文本框内
				//setTimeout("showchange()",1000);
			}
    		else{//其余浏览器
        		var fileObj=document.getElementById("file_picture");
        		var realImgUrl=getPath(fileObj);
        		if(!CheckFileSize(realImgUrl,fileObj)){
					return;
				}
        		layer.confirm("<img id='img_photo' style='cursor: pointer;' src='"+realImgUrl+"' width='220px' height='200px' onclick='uploadPhoto();' />", {
					skin: 'layui-layer-rim', //加上边框
					area: ['370px', '58%'], //宽高
					btn: ['确定','取消'] //按钮
                  }, function(){
                  	//确定
                  	$("#img_photo").remove();
                  	$("#td_img").append("<img id='img_photo' style='cursor: pointer;' src='"+realImgUrl+"' width='220px' height='200px' onclick='uploadPhoto();' />");
                  	layer.msg('完成', {icon: 1});
                  }, function(){
                  	//取消
                    
                  });
    		}
    	});
    });
      
 </script>

</head>

<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt;个人中心 &gt;个人信息维护</div>
<!-- 信息开始 -->
<div class="mframe">

	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
          <tbody>
          	<tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">个人信息维护</span>
                    </td>
                    <td class="tr"></td>
            </tr>
            <tr>
                <td class="tm" id="td_img">
                	<s:if test="photo!=null">
                		<img id="img_photo" style="cursor: pointer;" alt="头像" src="studentImgs/${photo}" width="200px" height="200px" onclick="uploadPhoto();"  />
                	</s:if>
                	<s:else>
                		<img id="img_photo" style="cursor: pointer;" alt="未上传头像" src="${pageContext.request.contextPath}/style/images/default.gif" width="200px" height="200px" onclick="uploadPhoto();" />
                	</s:else>
                        
                    </td>
                    <td class="mm">
                    <div>
<s:form action="student_personalMaintain" method="post" enctype="multipart/form-data">
<table cellspacing="0" cellpadding="6"  align="center" border="0">
		<!-- 表头-->
		<tbody> 
					<tr>
						<td class="addFont">照片</td>
                        <td>
                        	<input type="button" onclick="uploadPhoto();" value="上传图片" id="btn_photo" class="bbtn btn-success" />
                        	<span class="span_info" id="span_info"></span>
                        	<input type="file" accept="image/*" name="picture" style="display:none;" id="file_picture" />
                        </td>
                    </tr>
                    <tr>
                    	<td  class="addFont">姓名</td>
                        <td>
                        	<s:textfield name="stuName" cssClass="inpu" id="txt_stuName"/>
                        	<span class="span_note">*</span>
                        </td>
                        
                    </tr>
					<tr><td  class="addFont">性别</td>
                        <td>
                        	
							<s:radio name="sex" list="{'男', '女'}"></s:radio>
						</td>
                    </tr>
                    <tr><td  class="addFont">生日</td>
                        <td><s:textfield name="birthday" cssClass="inpu" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',startDate:'1993-01-01'})"/></td>
                    </tr>

					
                    <tr>
                    	<td>
                    		
                    	</td>
                        <td>
                        	<s:submit value="提交" onclick="return Check();" cssClass="ttn"></s:submit>
                        	
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
