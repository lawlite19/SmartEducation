<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色列表</title>
<!--Layer插件弹出对话框前台代码开始-->
<script src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
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
	<table>

		<!-- 表头-->
		<thead>
			<tr>
				<td width="200px">角色名称</td>
				<td width="300px">角色说明</td>
				<td width="300px">创建时间</td>
				<td>相关操作</td>
			</tr>
		</thead>

		<!--显示数据列表-->
		<tbody>

			<s:iterator value="#roleList">
				<tr class="TableDetail1 template">
					<td>${roleName}&nbsp;</td>
					<td><a id="${description}" class="roledescription">${description}&nbsp;</a>
					</td>
					<td>${addDate}&nbsp;</td>
					<td><s:a action="role_delete?id=%{id}"
							onclick="return confirm('确定要删除吗？')">删除</s:a> <s:a
							action="role_editUI?id=%{id}">修改</s:a>
						<s:if test="#session.user.hasPrivilegeByName(privilegeName)">
							<a id="${id}" class="rolePrivilege" title="${roleName}">设置权限</a>
						</s:if>
					</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	<!-- 其他功能超链接 -->
	<div id="TableTail">
		<div id="TableTail_inside">
			<s:a action="role_addUI">增加角色</s:a>
		</div>
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
