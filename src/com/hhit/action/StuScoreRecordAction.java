package com.hhit.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ChartDirector.CDMLTable;
import ChartDirector.Chart;
import ChartDirector.LegendBox;
import ChartDirector.LineLayer;
import ChartDirector.PlotArea;
import ChartDirector.TextBox;
import ChartDirector.XYChart;

import com.hhit.base.BaseAction;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.StuPaperAccount;
import com.hhit.entity.StuScoreRecord;
import com.hhit.entity.Student;
import com.hhit.entity.TestPaper;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StuScoreRecordAction extends BaseAction<StuScoreRecord>{
	
	
	private Integer testPaperId;
	private Integer courseId;
	//1:课前预习，2:课堂测试，3:课后复习
	private Integer testTypeId;
	
	/** 课程界面--显示课程测试成绩 */
	public String stuCourseScoreInfo() throws Exception{
		//数据--学生
		Student stuFind=getCurrentUser().getStudent();
		//数据--班级
		Class_ classFind=stuFind.getClass_();
		//数据--选课
		List<ClassSelectCourse> classSelectCourseList=classSelectCourseService.findByClass(classFind);

		ActionContext.getContext().put("classSelectCourseList", classSelectCourseList);
		if(courseId!=null){
			//课程
			Course courseFind=courseService.findById(courseId);
			//得到对应的测试卷
			List<TestPaper> paperList=testPaperService.findByClassAndCourseASC(classFind, courseFind);

			//定义分数list
			List<StuScoreRecord> scoreRecordList=new ArrayList<>();
			
			for(int i=0;i<paperList.size();i++){
				
				List<StuScoreRecord> scoreList=stuScoreRecordService.findByPaper(paperList.get(i));
				for(int j=0;j<scoreList.size();j++){
					scoreRecordList.add(scoreList.get(j));
				}
			}
			int count=scoreRecordList.size();
			//labels序号
			String[] labels= new String[count];
			//分数
			double[] dataScore=new double[count];
			//及格线
			double[] dataPass=new double[count];
			for(int i=0;i<count;i++){
				String timeString=scoreRecordList.get(i).getSubmitTime().toString();
				String dateString=timeString.substring(0, 10);
				labels[i]=""+dateString+scoreRecordList.get(i).getTestPaper().getTestType();
				dataScore[i]=scoreRecordList.get(i).getScore();
				dataPass[i]=60;
			}
			// Create a XYChart object of size 600 x 400 pixels
			XYChart c = new XYChart(800, 500);
			c.setDefaultFonts("simsun.ttc");

			// 添加图形的标题 ---- 18pt Times Bold Italic font
			TextBox title = c.addTitle("成绩记录", "宋体", 18);

			// Tentatively set the plotarea at (50, 55) and of (chart_width - 100) x (chart_height - 120) pixels
			// in size. Use a vertical gradient color from sky blue (aaccff) t0 light blue (f9f9ff) as
			// background. Set both horizontal and vertical grid lines to dotted semi-transprent black
			// (aa000000).
			PlotArea plotArea = c.setPlotArea(80, 85, c.getWidth() - 100, c.getHeight() - 80,
			    c.linearGradientColor(0, 55, 0, 55 + c.getHeight() - 120, 0xaaccff, 0xf9fcff), -1, -1,
			    c.dashLineColor(0xaa000000, Chart.DotLine), -1);

			// Add a legend box and anchored the top center at the horizontal center of the chart, just under
			// the title. Use 10pt Arial Bold as font, with transparent background and border.
			LegendBox legendBox = c.addLegend(c.getWidth() / 2, title.getHeight(), false, "宋体", 10);
			legendBox.setAlignment(Chart.TopCenter);
			legendBox.setBackground(Chart.Transparent, Chart.Transparent);

			// Set y-axis title using 10 points Arial Bold Italic font, label style to 8 points Arial Bold, and
			// axis color to transparent
			c.yAxis().setTitle("分数", "宋体", 10);
			c.yAxis().setLabelStyle("宋体", 8);
			c.yAxis().setColors(Chart.Transparent);
//			c.yAxis().setOffset(0, 36);
			// Set y-axis tick density to 30 pixels. ChartDirector auto-scaling will use this as the guideline
			// when putting ticks on the y-axis.
			c.yAxis().setTickDensity(15);

			// Add a line layer to the chart
			LineLayer layer = c.addLineLayer2();

			// Set the line width to 3 pixels
			layer.setLineWidth(3);
			
			// Add the three data sets to the line layer, using circles, diamands and X shapes as symbols
			layer.addDataSet(dataScore, 0x00ff00, "我的成绩").setDataSymbol(Chart.DiamondSymbol, 11);
			layer.addDataSet(dataPass, 0xff0000, "及格成绩").setDataSymbol(Chart.CircleSymbol,9);

			// Set the x axis labels
			c.xAxis().setLabels(labels);

			// Convert the labels on the x-axis to a CDMLTable
			CDMLTable table = c.xAxis().makeLabelTable();

			// Set the default top/bottom margins of the cells to 3 pixels
			table.getStyle().setMargin2(0, 0, 3, 3);

			// Use Arial Bold as the font for the first row
			table.getRowStyle(0).setFontStyle("宋体");

			//
			// We can add more information to the table. In this sample code, we add the data series and the
			// legend icons to the table.
			//

			// Add 3 more rows to the table. Set the background of the 1st and 3rd rows to light grey (eeeeee).
			table.appendRow().setBackground(0xeeeeee, Chart.LineColor);
			table.appendRow();
			table.appendRow().setBackground(0xeeeeee, Chart.LineColor);
			table.appendRow();
			// Put the values of the 3 data series to the cells in the 3 rows
			for (int i = 0; i < dataScore.length; ++i) {
			    table.setText(i, 1, String.valueOf(dataScore[i]));
			    table.setText(i, 2, String.valueOf(dataPass[i]));
			}

			// Insert a column on the left for the legend icons. Use 5 pixels left/right margins and 3 pixels
			// top/bottom margins for the cells in this column.
			table.insertCol(0).setMargin2(5, 5, 3, 3);

			// The top cell is set to transparent, so it is invisible
			table.getCell(0, 0).setBackground(Chart.Transparent, Chart.Transparent);

			// The other 3 cells are set to the legend icons of the 3 data series
			table.setText(0, 1, layer.getLegendIcon(0));
			table.setText(0, 2, layer.getLegendIcon(1));

			// Layout legend box first, so we can get its size
			c.layoutLegend();

			// Adjust the plot area size, such that the bounding box (inclusive of axes) is 2 pixels from the
			// left, right and bottom edge, and is just under the legend box.
			c.packPlotArea(2, legendBox.getTopY() + legendBox.getHeight(), c.getWidth() - 3, c.getHeight() - 13);

			// After determining the exact plot area position, we may adjust the legend box and the title
			// positions so that they are centered relative to the plot area (instead of the chart)
			legendBox.setPos(plotArea.getLeftX() + (plotArea.getWidth() - legendBox.getWidth()) / 2,
			    legendBox.getTopY());
			title.setPos(plotArea.getLeftX() + (plotArea.getWidth() - title.getWidth()) / 2, title.getTopY());

			// Output the chart
			String chart1URL = c.makeSession(ServletActionContext.getRequest(), "chart1");

			// Include tool tip for the chart
			String imageMap1 = c.getHTMLImageMap("", "",
			    "title='Revenue of {dataSetName} in {xLabel}: US$ {value}M'");
			ActionContext.getContext().put("chart1URL", chart1URL);
			ActionContext.getContext().put("imageMap1", imageMap1);
			
			//准备数据courseId
			ActionContext.getContext().put("courseId", courseId);
		}
		return "stuCourseScoreInfo";
	}
	

//app
//========================
	//记录答题分数
	public String appStuScoreRecord() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			TestPaper testPaperFind=testPaperService.findById(testPaperId);
			if(testPaperFind==null){
				map.put("name", "noTestPaper");
			}
			else{
				StuScoreRecord recordFind=stuScoreRecordService.findByStuNumAndPaper(model.getStuNum(),testPaperFind);
				if(recordFind!=null){
					map.put("name", "alreadyExist");
				}
				else{
					model.setStuName(stuFind.getStuName());
					model.setTestPaper(testPaperFind);
					model.setSubmitTime(new Timestamp(new Date().getTime()));
					stuScoreRecordService.save(model);
					Course courseFind=testPaperFind.getCourse();
					//更新或保存用户统计信息
					StuPaperAccount stuPaperAccount=stuPaperAccountService.findByStuNumAndCourse(stuFind.getStuNum(),courseFind);
					if(stuPaperAccount==null){
						//保存
						stuPaperAccount=new StuPaperAccount(stuFind.getStuName(), stuFind.getStuNum(), 1, model.getScore(), stuFind.getClass_(),testPaperFind.getCourse());
						stuPaperAccountService.save(stuPaperAccount);
					}
					else{
						//更新
						int subCount=stuPaperAccount.getSubmitCount();
						float score=stuPaperAccount.getAverageScore();
						float realScore=(score*subCount+model.getScore())/(subCount+1);
						stuPaperAccount.setAverageScore(realScore);
						stuPaperAccount.setSubmitCount(subCount+1);
						stuPaperAccountService.update(stuPaperAccount);
					}
					//更新提交人数--同步操作
					synchronized (this) {
						//再读数据库一次
						testPaperFind=testPaperService.findById(testPaperId);
						int submitCount=testPaperFind.getSubmitCount()+1;
						testPaperFind.setSubmitCount(submitCount);
						testPaperService.update(testPaperFind);
					}
					map.put("name", "success");
				}
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//检测是否做过了测试
	public String checkStuAlreadyTest() throws Exception{
		Map<String, Object> map=new HashMap<>();
		TestPaper testPaperFind=testPaperService.findById(testPaperId);
		if(testPaperFind==null){
			map.put("name", "noTestPaper");
		}
		else{
			StuScoreRecord recordFind=stuScoreRecordService.findByStuNumAndPaper(model.getStuNum(),testPaperFind);
			if(recordFind!=null){
				map.put("name", "alreadyExist");
			}
			else{
				map.put("name", "noExist");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//老师查看测试卷的信息
	public String appTeaLookStuScore() throws Exception{
		Map<String, Object> map=new HashMap<>();
		TestPaper paperFind=testPaperService.findById(testPaperId);
		if(paperFind==null){
			map.put("name", "noTestPaper");
		}
		else{
			List<StuScoreRecord> recordList=stuScoreRecordService.findByPaper(paperFind);
			if(recordList.size()<1){
				map.put("name", "noRecord");
			}
			else{
				ClassPropertyFilter.ListStuScoreRecordFilter(map, recordList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	
	public Integer getTestPaperId() {
		return testPaperId;
	}
	public void setTestPaperId(Integer testPaperId) {
		this.testPaperId = testPaperId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getTestTypeId() {
		return testTypeId;
	}
	public void setTestTypeId(Integer testTypeId) {
		this.testTypeId = testTypeId;
	}
}
