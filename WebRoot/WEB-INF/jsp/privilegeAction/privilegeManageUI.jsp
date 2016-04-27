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
                        <span class="tt">系统 </span>
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


							</td>
							<td>
<!-- 添加功能 -->
<s:form action="privilege_%{id == null ? 'add' : 'edit'}" method="post">
<s:hidden name="id" />
父级功能：
<s:select name="privilegeId" cssClass="ddl" list="#selectPrivilegeList"
		listKey="id" listValue="privilegeName" headerKey="" headerValue="==请选择父级功能==" />
<br/>
功能名称： <s:textfield name="privilegeName" cssClass="inpu" ></s:textfield>
<br/>
功能url： <s:textfield name="url" cssClass="inpu"></s:textfield>
<br/>
功能描述：<s:textarea name="description" cssClass="inpu"></s:textarea>
<br/>
<s:if test="%{id!=null}">
	<s:submit cssClass="ttn" value="修改功能"  />
</s:if>
<s:else>
	<s:submit cssClass="ttn" value="添加功能" />
</s:else>
</s:form>
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
