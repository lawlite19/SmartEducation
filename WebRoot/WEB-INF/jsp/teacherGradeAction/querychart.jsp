<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
   <title>个人评价信息</title>
    <%@ include file="/WEB-INF/jsp/public/list.jspf" %>
  
  <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
  <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
  <script>
  		$(function()
  		{
  			$.ajax({
  				type:"get",
  				url:"teachergrade_chartquery.action",
  				datatype:"json",
  				async:false,
  				success:function (data) {
  		 $('#container1').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '教师个人评价信息历史柱状图'
        },
        subtitle: {
            text: data.teaname
        },
        xAxis: {
            categories: data.termname
        },
        yAxis: {
            min: 25,
            title: {
                text: '评价得分'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 分</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '学生评价',
            data: data.stugrade

        }, {
            name: '同行评价',
            data: data.peergrade

        }, {
            name: '自我评价',
            data: data.selfgrade

        }, {
            name: '教学要素评价',
            data: data.teachgrade

        },
            {
            name: '总评加分',
            data: data.allgrade

        }]
    });
			}
  			});
  			
  			
  			
  			$.ajax({
  				type:"get",
  				url:"teachergrade_chartquery.action",
  				datatype:"json",
  				async:false,
  				success:function (data) {
  		 $('#container2').highcharts({
        title: {
            text: '教师个人评价信息历史折现图'
        },
        subtitle: {
            text: data.teaname
        },
        xAxis: {
            categories: data.termname
        },
        yAxis: {
             plotLines: [{
                value: 0,
                width: 10,
                color: '#808080'
            }],
            title: {
                text: '评价得分'
            }
        },
        tooltip: {
            valueSuffix: '分'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '学生评价',
            data: data.stugrade

        }, {
            name: '同行评价',
            data: data.peergrade

        }, {
            name: '自我评价',
            data: data.selfgrade

        }, {
            name: '教学要素评价',
            data: data.teachgrade

        },
            {
            name: '总评加分',
            data: data.allgrade

        }]
    });
			}
  			});
  			
  			
  			
  		}
  		);
 </script>
  <script>
  	function isdisplay(){
  		var c1=document.getElementById("container1");
  		var c2=document.getElementById("container2");
  		var zhexian=document.getElementById("zhexiean");
  		var zhuzhuang=document.getElementById("zhuzhuang");
  		if(c1.style.display=='none')
  			{
  				c1.style.display='';
  				c2.style.display='none';
  				zhexian.style.display='';
  				zhuzhuang.style.display='none';
  			}
  		else{
  				c2.style.display='';
  				c1.style.display='none';
  				zhexian.style.display='none';
  				zhuzhuang.style.display='';
  		}
  		
  	}
  </script>
  </head>
  
 <body>
 <div class="crumd"><a href="" id="A1">首页</a> &gt; 个人评价信息 &gt; 年度评价信息</div>


<!-- 添加和返回 -->
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="teachergrade_query" style="color:white;text-decoration: none;">表格显示</s:a>
		</td>
	</tr>
</table>
	 <div id="container1" style="min-width:700px;height:400px"></div>
	 <div id="container2" style="min-width:700px;height:400px;display:none" ></div>
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center" onclick=" isdisplay()" >
			<div  style="color:white;text-decoration: none;" id="zhexian">折线图</div>
		</td>
		<td class="bbtn btn-primary" align="center" style="display:none" onclick="isdisplay()" id="zhuzhuang"  >
			<div  style="color:white;text-decoration: none;" >柱状图</div>
		</td>
	</tr>
</table>
</body>
</html>
