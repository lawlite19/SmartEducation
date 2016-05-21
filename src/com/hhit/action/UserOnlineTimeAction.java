package com.hhit.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ChartDirector.Chart;
import ChartDirector.LegendBox;
import ChartDirector.LineLayer;
import ChartDirector.Mark;
import ChartDirector.XYChart;

import com.hhit.base.BaseAction;
import com.hhit.entity.PageBean;
import com.hhit.entity.User;
import com.hhit.entity.UserOnlineTime;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserOnlineTimeAction extends BaseAction<UserOnlineTime>{
	
	
	public String list() throws Exception{
		//得到当前用户
		User userFind=getCurrentUser();
		//总时长
		Integer totalDuration=userFind.getTotalMinute();
		//日期时间记录--分页
		PageBean pageBean=new QueryHelper(UserOnlineTime.class, "u")//
							.addCondition("u.user=?", userFind)//
							.prepareAppPageBean(userOnlineTimeService, pageNum, pageSize);
		List<UserOnlineTime> onlineList=pageBean.getRecordList();
		int size=onlineList.size();
		// The data for the line chart
		double[] data =new double[size];
		// The labels for the line chart
		String[] labels =new String[size];
		for(int i=0;i<size;i++){
			data[i]=onlineList.get(i).getDurationMinute();
			labels[i]=onlineList.get(i).getDate().toString();
		}
		// Create an XYChart object of size 600 x 360 pixels, with a light blue (EEEEFF) background, black
		// border, 1 pxiel 3D border effect and rounded corners
		XYChart c = new XYChart(900, 500, 0xeeeeff, 0x000000, 1);
		c.setDefaultFonts("simsun.ttc");
		c.setRoundedFrame();

		// Set plotarea at (55, 60) with size of 520 x 240 pixels. Use white (ffffff) as background and grey
		// (cccccc) for grid lines
		c.setPlotArea(100, 100, 600, 240, 0xffffff, -1, -1, 0xcccccc, 0xcccccc);

		// Add a legend box at (55, 58) (top of plot area) using 9pt Arial Bold font with horizontal layout
		// Set border and background colors of the legend box to Transparent
		LegendBox legendBox = c.addLegend(55, 58, false, "宋体", 9);
		legendBox.setBackground(Chart.Transparent);

		// Reserve 10% margin at the top of the plot area during auto-scaling to leave space for the
		// legends.
		c.yAxis().setAutoScale(0.1);

		// Add a title to the chart using 15pt Times Bold Italic font. The text is white (ffffff) on a blue
		// (0000cc) background, with glass effect.
		ChartDirector.TextBox title = c.addTitle("在线总时长"+totalDuration+"分钟",
		    "宋体", 15, 0xffffff);
		title.setBackground(0x0000cc, 0x000000, Chart.glassEffect(Chart.ReducedGlare));

		// Add a title to the y axis
		c.yAxis().setTitle("分钟","宋体",12);

		// Set the labels on the x axis. Draw the labels vertical (angle = 90)
		c.xAxis().setLabels(labels).setFontAngle(70);
		// Add a line layer to the chart
		LineLayer layer = c.addLineLayer();

		// Set the default line width to 3 pixels
		layer.setLineWidth(3);


		// Create the image
		String chart1URL = c.makeSession(ServletActionContext.getRequest(), "chart1");

		// Create an image map for the chart
		String chartImageMap = c.getHTMLImageMap("xystub.jsp", "",
		    "title='{dataSetName} @ {xLabel} = USD {value|0} millions'");

		// Create an image map for the legend box
		String legendImageMap = legendBox.getHTMLImageMap(
		    "javascript:popMsg('the legend key [{dataSetName}]');", " ",
		    "title='This legend key is clickable!'");

		// Obtain the image map coordinates for the title, mark, and copyright message. These will be used
		// to define the image map inline. (See HTML code below.)
		String titleCoor = title.getImageCoor();
		
		ActionContext.getContext().put("chart1URL", chart1URL);
		ActionContext.getContext().put("chartImageMap", chartImageMap);
		ActionContext.getContext().put("legendImageMap", legendImageMap);
		ActionContext.getContext().put("titleCoor", titleCoor);
		
		 return "list";
	}
}
