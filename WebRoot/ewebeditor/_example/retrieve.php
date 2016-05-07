<?php header("content-Type: text/html; charset=gb2312");?>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<TITLE>eWebEditor ： 表单接收示例</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>导航 ： <a href="default.htm">示例首页</a> &gt; 表单接收示例</b></p>
<p>此例演示了如何接收到表单提交过来的HTML代码，并显示它。</p>

<?php

echo "编辑内容如下：<br><br>".stripslashes($_POST["content1"]);
echo "<br><br><p><input type=button value=' 后退 ' onclick='history.back()'></p>";

?>

</BODY>
</HTML>