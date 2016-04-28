<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统功能管理</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/baseSE.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
	
    <script type="text/javascript">
		$(function(){
			// 指定事件处理函数
			$("[name=privilegeIds]").click(function(){
				
				// 当选中或取消一个权限时，也同时选中或取消所有的下级权限
				$(this).siblings("ul").find("input").attr("checked", this.checked);
				
				// 当选中一个权限时，也要选中所有的直接上级权限
				if(this.checked == true){
					$(this).parents("li").children("input").attr("checked", true);
				}
				
			});
		});
	</script>
	<!-- 验证输入 -->
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
	function CheckAdd() {
        if (!MM_Empty('select_parent', '父级功能')) return false;
        if (!MM_Empty('txt_privilegeName', '功能名称')) return false;
    }
	function CheckEdit(){
        if (!MM_Empty('select_edit_parent', '父级功能')) return false;
        if (!MM_Empty('txt_edit_privilegeName', '功能名称')) return false;
	}
	</script>
  </head>
  
  
  <body>
  <!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 系统管理 &gt; 系统功能管理</div>
        <!-- 表单内容显示 -->
         <div class="mframe">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
            	<table width="90%" align="left" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">系统功能 </span>
                    </td>
                    <td class="tr"></td>
                </tr>
                <tr>
                <td class="tm">
                        
                    </td>
                    <td class="mm">
                    <div>
                <table width="91.8%" align="left" cellspacing="0" cellpadding="0">
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td rowspan="20">
							<fieldset>
	<legend>系统功能树</legend>
	

<!-- 显示树状结构内容 -->
<ul id="tree">
<%-- 显示一级菜单 --%>
<s:iterator value="#application.topPrivilegeList">
	<li>
		<s:a href="privilege_editUI.action?id=%{id}">
			<span class="folder">${privilegeName}</span>
		</s:a>
		<ul>
		<%-- 显示二级菜单 --%>
		<s:iterator value="children">
			<li>
			<s:a href="privilege_editUI.action?id=%{id}">
				<span class="folder">${privilegeName}</span>
			</s:a>
				<ul>
				<%-- 显示三级菜单 --%>
				<s:iterator value="children">
					<li>
					<s:a href="privilege_editUI.action?id=%{id}">
						<span class="folder">${privilegeName}</span>
					</s:a>
					<ul>
					<%--显示四级菜单 --%>
						<s:iterator value="children">
						<li>
							<s:a href="privilege_editUI.action?id=%{id}">
								<span class="folder">${privilegeName}</span>
							</s:a>
						</li>
				</s:iterator>
				</ul>
					</li>
				</s:iterator>
				</ul>
			</li>		
		</s:iterator>
		</ul>
	</li>
</s:iterator>
</ul>
</fieldset>

							</td>

							<td valign="top">
<fieldset>
	<legend>添加功能</legend>
		<!-- 添加功能 -->
<s:form action="privilege_add" method="post">
	<table>
		<tbody>
			<tr>
				<td><span class="addFont">父级功能：</span></td>
				<td>
					<s:select name="privilegeId" id="select_parent" cssClass="ddl" list="#selectPrivilegeList"
						listKey="id" listValue="privilegeName" headerKey="" headerValue="==请选择父级功能==" />
					<span class="span_note">*</span>
				</td>
			</tr>
			<tr>
				<td><span class="addFont">功能名称：</span></td>
				<td>
					<s:textfield name="privilegeName" id="txt_privilegeName" cssClass="inpu" ></s:textfield>
					<span class="span_note">*</span>
				</td>
			</tr>
			<tr>
				<td><span class="addFont">功能url：</span></td>
				<td><s:textfield name="url" cssClass="inpu"></s:textfield></td>
			</tr>
			<tr>
				<td><span class="addFont">功能描述：</span> </td>
				<td><s:textarea name="description" cssClass="inpu" cssStyle="height:60px;width:240px"></s:textarea>
				</td>
			</tr>
			<tr>
				<td> </td>
				<td>
					<s:submit onclick="return CheckAdd();" cssClass="ttn" value="添加功能" />
				</td>
			</tr>
		</tbody>
	</table>


</s:form>
	
</fieldset>
<fieldset>
	<legend>修改功能</legend>
<!-- 修改功能 -->
<s:form action="privilege_edit" method="post">
<s:hidden name="id" />
	<table>
		<tbody>
			<tr>
				<td><span class="addFont">父级功能：</span></td>
				<td>
					<s:select name="privilegeId" id="select_edit_parent" cssClass="ddl" list="#selectPrivilegeList"
						listKey="id" listValue="privilegeName" headerKey="" headerValue="==请选择父级功能==" />
					<span class="span_note">*</span>
				</td>
			</tr>
			<tr>
				<td><span class="addFont">功能名称：</span></td>
				<td>
					<s:textfield name="privilegeName" id="txt_edit_privilegeName" cssClass="inpu" ></s:textfield>
					<span class="span_note">*</span>
				</td>
			</tr>
			<tr>
				<td><span class="addFont">功能url：</span></td>
				<td><s:textfield name="url" cssClass="inpu"></s:textfield></td>
			</tr>
			<tr>
				<td><span class="addFont">功能描述：</span> </td>
				<td><s:textarea name="description" cssClass="inpu" cssStyle="height:60px;width:240px"></s:textarea>
				</td>
			</tr>
			<tr>
				<td> </td>
				<td>
					<s:submit onclick="return CheckEdit();" cssClass="ttn" value="修改功能"  />
				</td>
			</tr>
		</tbody>
	</table>


</s:form>
</fieldset>

							</td>
						</tr>
					</tbody>
                </table>
                </div>
                </td>
                </tr>
                </tbody>
                </table>
            </div>
        </div>
       </div> 
        <script type="text/javascript">
        $(function(){
        	$("#tree").treeview();
        });
        </script>

  </body>
</html>
