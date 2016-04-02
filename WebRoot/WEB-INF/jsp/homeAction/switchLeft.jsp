<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <style type="text/css">
        body
        {
            padding: 0;
            margin: 0;
        }

        #d
        {
            height: 2000px;
            width: 6px;
            background: #49AFF1 url(${pageContext.request.contextPath}/style/images/admin_switch.gif) no-repeat 0 260px;
            border: 1px solid #ddd;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        var opened = true;
        function mOver() {
            var o = document.getElementById("d");
            o.style.backgroundPosition = opened ? "-20px 260px" : "-60px 260px";
            o.style.backgroundColor = "#49AFF1";
        }
        function mOut() {
            var o = document.getElementById("d");
            o.style.backgroundPosition = opened ? "0 260px" : "-40px 260px";
            o.style.backgroundColor = "#49AFF1";
        }

        function mClick() {
            var fm = parent.document.getElementById("frame");
            var o = document.getElementById("d");
            o.style.backgroundColor = "#49AFF1";
            if (opened) {
                //		fm.cols="0,8,*";
                opened = false;
                o.style.backgroundPosition = "-40px 260px";
                closeLeft();
            } else {
                //		fm.cols="150,8,*";
                opened = true;
                o.style.backgroundPosition = "0px 260px";
                openLeft();
            }
        }

        function closeLeft() {
            var fm = parent.document.getElementById("frame");
            var w = fm.cols;
            w = parseInt(w.substring(0, w.indexOf(",")));
            if (w > 0) {
                w -= 60;
                if (w < 0) {
                    w = 0;
                }
            }
            fm.cols = w + ",8,*";
            if (w > 0) {
                setTimeout("closeLeft()", 150);
            }
        }

        function openLeft() {
            var fm = parent.document.getElementById("frame");
            var w = fm.cols;
            w = parseInt(w.substring(0, w.indexOf(",")));
            if (w < 140) {
                w += 60;
                if (w > 150) {
                    w = 150;
                }
            }
            fm.cols = w + ",8,*";
            if (w < 140) {
                setTimeout("openLeft()", 150);
            }
        }


    </script>
</head>
<body>
    <div id="d" onmouseover="mOver()" onmouseout="mOut()" onclick="mClick()"></div>
</body>
</html>
