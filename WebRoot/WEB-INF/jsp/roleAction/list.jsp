<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色列表</title>
<%@ include file="/WEB-INF/jsp/public/list.jspf" %>
<script>
        function LayerAlert(type, msg) {   //0:叹号   1:对号  2：错号 3：禁止号  4：问号  5：减号  6：棒  7：锁  8：委屈  9：笑脸  10：对号
            $.layer({
                dialog: { type: type, msg: msg }
            });
        }
        (function () {
            	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            	parent.layer.close(index); //再执行关闭 
        })();
    </script>
<!--Layer插件弹出对话框前台代码结束-->

</head>

<body>
<!-- 顶层 -->
<div class="crumd"><a href="" id="A1">首页</a> &gt; 角色管理 &gt; 角色权限管理</div>
	<!-- 添加 -->
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
	<td width="91%">
 	 </td>
		<td class="bbtn btn-primary" align="center">
			<s:a action="role_addUI" style="color:white;text-decoration: none;">增加角色</s:a>
		</td>
	</tr>
</table>
<div class="mframe">
	<table width="91.8%" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="tl"></td>
                    <td class="tm">
                        <span class="tt">角色权限管理</span>
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
				<td align="center">序号</td>
				<td align="center">角色名称</td>
				<td align="center">角色说明</td>
				<td align="center">创建时间</td>
				<td align="center">相关操作</td>
			</tr>
		</thead>
		<tbody>
		<!--显示数据列表-->
		<s:iterator value="#roleList" status="s">
				<tr onmouseover="SetNewColor(this);" onmouseout="SetOldColor(this);">
					<td align="center">${s.count}&nbsp;</td>
					<td align="center">${roleName}&nbsp;</td>
					<td align="center"><a id="${description}" class="roledescription">${description}&nbsp;</a>
					</td>
					<td  align="center">${addDate}&nbsp;</td>
					<td  align="center">
						<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？注意不要随意删除角色哦！！')">
							<img  style=" border:0px;"  src="${pageContext.request.contextPath}/style/images/del.gif"  />
						</s:a>
						|
						<s:a action="role_editUI?id=%{id}">
							<img style="border: 0px;" src="${pageContext.request.contextPath}/style/images/edit.gif" />
						</s:a>
						|
						<s:if test="#session.user.hasPrivilegeByName(privilegeName)">
							<a id="${id}" class="rolePrivilege" style="cursor:pointer;" title="${roleName}">分配权限</a>
						</s:if>
						&nbsp;
					</td>
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
	

	<script type="text/javascript">
        (function () {
            $('a.rolePrivilege').on('click', function (event) {
                // event.preventDefault();
                var value = $(this).attr("id");
                var roleName = $(this).attr("title");
              //iframe层
                layer.open({
                  type: 2,
                  title: '正在给['+roleName+']分配权限',
                  shadeClose: true,
                  shade: 0.8,
                  area: ['500px', '90%'],
                  content: 'role_setPrivilegeUI.action?id=' + value, //iframe的url
                  yes:function(index){
                	  layer.close(index);
                  }
                }); 
          });
        })();
        	

    </script>
    <%--//iframe窗
        	layer.open({
        	  type: 2,
        	  title: false,
        	  closeBtn: 0, //不显示关闭按钮
        	  shade: [0],
        	  area: ['340px', '215px'],
        	  offset: 'rb', //右下角弹出
        	  time: 2000, //2秒后自动关闭
        	  shift: 2,
        	  content: ['role_setPrivilegeUI.action?id=' + value, 'yes'], //iframe的url，no代表不显示滚动条
        	  end: function(){ //此处用于演示
        	    layer.open({
        	      type: 2,
        	      title: '很多时候，我们想最大化看，比如像这个页面。',
        	      shadeClose: true,
        	      shade: false,
        	      maxmin: true, //开启最大化最小化按钮
        	      area: ['893px', '600px'],
        	      content: 'role_setPrivilegeUI.action?id=' + value
        	    });
        	  }
        	});
            }) --%>
</body>
</html>
