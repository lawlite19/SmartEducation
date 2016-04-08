<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统功能管理</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
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
  </head>
  
  <body>
		系统功能树   
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
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
					</li>
				</s:iterator>
				</ul>
			</li>		
		</s:iterator>
		</ul>
	</li>
</s:iterator>
</ul>


							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        
        <script type="text/javascript">
        $(function(){
        	$("#tree").treeview();
        });
        </script>
<!-- 添加功能 -->
<s:form action="privilege_%{id == null ? 'add' : 'edit'}" method="post">
<s:hidden name="id" />
父级功能：
<s:select name="privilegeId" cssClass="SelectStyle" list="#selectPrivilegeList"
		listKey="id" listValue="privilegeName" headerKey="" headerValue="==请选择父级功能==" />
<br/>
功能名称： <s:textfield name="privilegeName" ></s:textfield>
<br/>
功能url： <s:textfield name="url"></s:textfield>
<br/>
功能描述：<s:textarea name="description"></s:textarea>
<br/>
<s:if test="%{id}!=null">
	<s:submit value="修改功能" />
</s:if>
<s:else>
	<s:submit value="添加功能" />
</s:else>
</s:form>
  </body>
</html>
