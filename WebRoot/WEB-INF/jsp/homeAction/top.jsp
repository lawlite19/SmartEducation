<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head >
    <title>智慧教育后台系统</title>
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
 data-appid="101304940" data-redirecturi="http://www.soeasystudy.com/back.jsp" charset="utf-8"></script>
    
    <%@include file="/WEB-INF/jsp/public/commons.jspf" %>
        <style type="text/css">
         *
        {
            padding: 0;
            margin: 0;
            text-decoration: none;
        }

        body
        {
            margin: 0;
            background: url(${pageContext.request.contextPath}/style/images/admin_tbg.png) repeat-x;
            padding: 0;
            color: #fff;
            font-family: 宋体, arial;
            font-size: 12px;
        }

        .wrap
        {
            position: relative;
            height: 102px;
            background: url(${pageContext.request.contextPath}/style/images/pnew2.png) no-repeat right top;
        }

        .logo
        {
            background: url(${pageContext.request.contextPath}/style/images/logoold.gif) no-repeat;
            left: 0px;
            top: 0px;
            position: absolute;
            height: 74px;
            width: 150px;
        }

        .logofont
        {
            background: url(${pageContext.request.contextPath}/style/images/logofont.png) no-repeat;
            width: 400px;
            height: 35px;
            position: absolute;
            right: 30px;
            top: 7px;
        }

        .menu
        {
            position: absolute;
            left: 156px;
            top: 36px;
        }

            .menu li
            {
                float: left;
                list-style: none;
                font-size: 14px;
                height: 39px;
                line-height: 39px;
            }

            .menu a
            {
                text-decoration: none;
                display: block;
            }

            .menu li.selected a
            {
                background: url(${pageContext.request.contextPath}/style/images/menu.png) no-repeat right -39px;
                color: #0A6697;
                padding-right: 17px;
                font-weight: bold;
            }

            .menu li.selected span
            {
                background: url(${pageContext.request.contextPath}/style/images/menu.png) no-repeat left top;
                display: block;
                padding-left: 17px;
                text-decoration: none;
            }

            .menu li a
            {
                display: block;
                background: url(${pageContext.request.contextPath}/style/images/menu.png) no-repeat right -117px;
                padding-right: 17px;
                text-decoration: none;
                color: #fff;
            }

            .menu span
            {
                background: url(${pageContext.request.contextPath}/style/images/menu.png) no-repeat 0 -78px;
                display: block;
                padding-left: 17px;
            }

        .member
        {
            position: absolute;
            left: 12px;
            top: 82px;
            color: #077FBC;
        }

        .s1 a:link, .s1 a:visited
        {
            color: #fff;
            text-decoration: none;
            padding: 0 10px 0 10px;
        }

        .s1 a:hover
        {
            color: #fff;
            text-decoration: underline;
        }

        .s2
        {
            position: absolute;
            right: 490px;
            top: 80px;
            background: url(${pageContext.request.contextPath}/style/images/sy.gif) no-repeat;
            text-indent: 2em;
            line-height: 18px;
        }

        .s3
        {
            line-height: 18px;
            position: absolute;
            right: 405px;
            top: 80px;
            background: url(${pageContext.request.contextPath}/style/images/t.png) no-repeat;
            text-indent: 2em;
        }
        .s6
        { 
            line-height: 18px;
            position: absolute;
            right: 225px;
            top: 80px;
            background: url(${pageContext.request.contextPath}/style/images/help.gif) no-repeat;
            text-indent: 2em;
        }
        .s5
        {
            line-height: 18px;
            position: absolute;
            right: 20px;
            top: 80px;
            width: 180px;
            color: #1598E0;
        }
       .s4
        {
            line-height: 18px;
            position: absolute;
            right: 315px;
            top: 80px;
            background: url(${pageContext.request.contextPath}/style/images/person.gif) no-repeat;
            text-indent: 2em;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/admin_clock.js"></script>
</head>
<body>
    <script type="text/javascript">
            function ChangeDisplay(liId) {
                document.getElementById(liId).className = "selected";
                var liTag;
                if (document.all) {
                    liTag = document.all.tags("li");
                }
                else {
                    liTag = document.getElementsByTagName("li");
                }
                for (var i = 0; i < liTag.length; i++) // 遍历页面上所有的 input 
                {
                    if (liTag[i].id != liId) {
                        liTag[i].className = "";
                    }
                }

            }
            function SetDefaultDisplay()
            {
                document.getElementById("li_1").className = "selected";
            }
        </script>
        <div class="wrap">
        <div class="logo"></div>
        <div class="menu">
 		<ul>
 			
 			<s:iterator value="#application.topPrivilegeList">
 			<s:if test="#session.user.hasPrivilegeByName(privilegeName)">
               <li onclick="javascript:ChangeDisplay('li_${id}');" id='li_${id}'><a href='home_left.action?parentId=${id}' target="left"><span>${privilegeName}</span></a></li>
			</s:if>
			</s:iterator>
  		</ul>
        </div>
        <div class="member">
            <img src="${pageContext.request.contextPath}/style/images/q.png" /> 您好：[]
        </div>
        <div class="s2"><a href="right.aspx" style="color: #1598E0" target="right">首页</a></div>
            <div class="s3"><a href="${pageContext.request.contextPath}/user_logout.action" onclick="signOut();" style="color: #1598E0" target="_top">退出系统</a></div>
            <div class="s4"><a href="${pageContext.request.contextPath}/user_modifyPasswordUI.action" style="color: #1598E0" target="right">密码修改</a></div>
            <div class="s6"><a href=""  style="color: #1598E0" target="right">帮助文档</a></div>
        <div class="s5" id="clock"></div>
      </div>
      <script type="text/javascript">
      	function signOut(){
      		//如果qq登录，就退出
      		if(QC.Login.check()){
      			QC.Login.signOut();
      		}
      		//如果微博登录，就退出
      		if(WB2.checkLogin()){
      			WB2.logout();
      		}
      		
      	}
      </script>
      
      
        <!--添加后台主页时间-->
        <script type="text/javascript">
            var clock = new Clock();
            clock.display(document.getElementById("clock"));
        </script>
</body>
</html>
