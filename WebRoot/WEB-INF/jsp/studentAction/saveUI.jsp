<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>增加或修改学生</title>
   	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
    <s:form action="student_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
                <table>
                    <tr><td>所属部门</td>
                        <td>
                        	<s:select name="departmentId" cssClass="ddl"
                        		list="#departmentList" listKey="id" listValue="deptName"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>
                        </td>
                    </tr>
                    <tr><td>所属班级</td>
                        <td>
                        <!--  
                        	<s:select name="classId" cssClass="ddl"
                        		list="#classList" listKey="id" listValue="className"
                        		headerKey="" headerValue="==请选择班级==" 
                        	/>-->
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><s:textfield name="stuNum" cssClass="InputStyle"/> *
							（登录名要唯一，设置为学号或工号）
						</td>
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield name="stuName" cssClass="InputStyle"/> *</td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                        	<%--
							<s:radio name="gender" list="%{ #{'男':'男', '女':'女'} }"></s:radio>
							<s:radio name="gender" list="#{'男':'男', '女':'女'}"></s:radio>
                        	 --%>
							<s:radio name="sex" list="{'男', '女'}"></s:radio>
						</td>
                    </tr>
                    <tr><td>生日</td>
                        <td><s:textfield name="birthday" cssClass="InputStyle"/> *</td>
                    </tr>

					<tr><td>年级</td>
                        <td><s:textfield name="grade" cssClass="InputStyle"/></td>
                    </tr>
                    
                </table>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
      
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="submit" value="提交"/>
            <a href="javascript:history.go(-1);">返回上级</a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"账号"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
	6，修改用户信息时，登录名不可修改。
</div>

</body>
</html>
