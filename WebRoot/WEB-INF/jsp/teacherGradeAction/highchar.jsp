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
  				url:"teachergrade_charhigh.action",
  				datatype:"json",
  				async:false,
  				success:function (data) {
  			
			    $('#container').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: '年终个人评级结果柱状图'
			        },
			        subtitle: {
			            text:'学期：'+data.termname
			        },
			        xAxis: {
			            categories: [
			                '自我评价',
			                '学生评价',
			                '同行评价',
			                '教学要素评价',
			                '总分',     
			                        ]
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: '分数',
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
			            name: data.teaname ,	
			            data: data.teagrade
			
			        }]
			    });
			}
  			});
  		}
  		);
 </script>
  
  </head>
  
 <body>
 <div class="crumd"><a href="" id="A1">首页</a> &gt; 个人评价信息 &gt; 年度评价信息</div>


<!-- 添加和返回 -->
<table align="center" cellspacing="5" cellpadding="5">
	<tr>
		<td class="bbtn btn-primary" align="center">
			<s:a action="teachergrade_list" style="color:white;text-decoration: none;">表格显示</s:a>
		</td>
	</tr>
</table>
	 <div id="container" style="min-width:700px;height:400px"></div>
	 <div id="container" style="min-width:700px;height:400px"></div>

</body>
</html>
