package com.hhit.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.SingleChoice;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SingleChoiceAction extends BaseAction<SingleChoice>{
	//
	private Integer courseId;
	private Integer chapterId;

	//查询添加
	private String txtCourseName="";
	private String txtKnowledgeName="";
	private String txtQuestion="";
	
	//json返回
	private String result;
	//存储上传excel文件
	private File questionBank;
	private String questionBankFileName;

	/** 列表 */
	public String list() throws Exception {

		// 准备分页信息
		new QueryHelper(SingleChoice.class, "s")//
		.addCondition(txtCourseName.trim()!="", "s.course.courseName LIKE ?", "%"+txtCourseName.trim()+"%")//
		.addCondition(txtKnowledgeName.trim()!="", "s.knowledgeName LIKE ?", "%"+txtKnowledgeName.trim()+"%")//
		.addCondition(txtQuestion.trim()!="", "s.question LIKE ?", "%"+txtQuestion.trim()+"%")
		.preparePageBean(singleChoiceService, pageNum, pageSize);
		return "list";
	}
	/** 删除 */
	public String delete() throws Exception {
		//直接根据id删除
		singleChoiceService.delete(model.getId());
		return "toList";
	}	
	/** 添加页面 */
	public String addUI() throws Exception {
		//准备数据--课程
		List<Course> courseList=courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);
		//准备数据--章节
		List<Chapter> chapterList=Collections.EMPTY_LIST;
		ActionContext.getContext().put("chapterList", chapterList);
		return "saveUI";
	}
	/** 添加 */
	public String add() throws Exception {
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// 设置没有封装的属性
		model.setAddTime(new Timestamp(new Date().getTime()));
		model.setUsedCount(0);
		model.setCourse(courseService.findById(courseId));
		if(chapterId!=null){
			model.setChapter(chapterService.findById(chapterId));
		}
		// 保存到数据库
		singleChoiceService.save(model);

		return "toList";
	}
	/** 修改页面 */
	public String editUI() throws Exception {
		//准备数据--课程
		List<Course> courseList=courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);


		// 准备回显的数据
		SingleChoice singleChoiceFind=singleChoiceService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(singleChoiceFind);
		//准备数据--章节
		if(singleChoiceFind.getCourse()!=null){
			List<Chapter> chapterList=chapterService.findByCourse(singleChoiceFind.getCourse());
			ActionContext.getContext().put("chapterList", chapterList);
		}
		else{
			List<Chapter> chapterList=Collections.EMPTY_LIST;
			ActionContext.getContext().put("chapterList", chapterList);
		}
		//回显--课程
		if(singleChoiceFind.getCourse()!=null){
			courseId=singleChoiceFind.getCourse().getId();
		}
		//回显--章节
		if(singleChoiceFind.getChapter()!=null){
			chapterId=singleChoiceFind.getChapter().getId();
		}
		return "saveUI";
	}
	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		SingleChoice singleChoiceFind=singleChoiceService.findById(model.getId());
		
		// 2，设置要修改的属性
		singleChoiceFind.setAnswer(model.getAnswer());
		singleChoiceFind.setDifExponent(model.getDifExponent());
		singleChoiceFind.setKnowledgeName(model.getKnowledgeName());
		singleChoiceFind.setModifyTime(new Timestamp(new Date().getTime()));
		singleChoiceFind.setQuestion(model.getQuestion());
		singleChoiceFind.setQuestionType(model.getQuestionType());
		singleChoiceFind.setAnswerA(model.getAnswerA());
		singleChoiceFind.setAnswerB(model.getAnswerB());
		singleChoiceFind.setAnswerC(model.getAnswerC());
		singleChoiceFind.setAnswerD(model.getAnswerD());
		// 3，更新到数据库
		singleChoiceService.update(singleChoiceFind);
		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		singleChoiceService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	/** 批量导入 */
	public String bulkImport() throws Exception{
		
		//上传到服务器目录-->/questionBankUpload目录
		if (questionBank != null) {
			// 获取当前应用程序物理路径
			String rootPath = ServletActionContext.getServletContext()
					.getRealPath("/");
			File tarDir = new File(rootPath + "/questionBankUpload");
			if (!tarDir.exists()) {
				tarDir.mkdirs();
			}
			//修改文件名字为当天日期
			//以.切分
			String [] strArray=questionBankFileName.split("\\.");
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd-HH-mm-ss" );
			String timeString=sdf.format(new Date());
			questionBankFileName="\\SingleChoice"+timeString+"."+strArray[strArray.length-1];
			File tarFile = new File(tarDir, questionBankFileName);
			try {
				FileUtils.copyFile(questionBank, tarFile);
			} catch (IOException e) {
				e.printStackTrace();
				addFieldError("questionBankInfo", "文件上传出错！");
				return "tobulkImportUI";
			}
			//数据导入操作
			//需要解析的Excel文件
			String str[]=new String[9];
			Integer difficult = null;
			File file = new File(tarDir+questionBankFileName);
			try {
				//创建Excel，读取文件内容
				//HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
				//高版本的用XSSF
				XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
				//读取默认第一个工作表sheet
				XSSFSheet sheet = workbook.getSheetAt(0);
				int firstRowNum = 3;
				//获取sheet中最后一行行号
				int lastRowNum = sheet.getLastRowNum();
				for (int i = firstRowNum; i <=lastRowNum; i++) {
					XSSFRow row = sheet.getRow(i);
					//获取当前行最后单元格列号
					//int lastCellNum = row.getLastCellNum();
					for (int j = 0; j < 10; j++) {
						XSSFCell cell = row.getCell(j);
						if(j==9)
							difficult=(int) cell.getNumericCellValue();
						else
							str[j] = cell.getStringCellValue();
					}
					Course courseFind=courseService.findByCourseName(str[1]);
					//构造函数
					SingleChoice singleChoiceFind=new SingleChoice(str[0], courseFind, str[2], str[3], str[4], str[5], str[6], str[7], str[8], difficult);
					//保存数据库
					singleChoiceService.save(singleChoiceFind);
				}
				workbook.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				addFieldError("questionBankInfo", "导入出错！");
				return "tobulkImportUI";
			}
		}
		else{
			addFieldError("questionBankInfo", "文件错误！");
			return "tobulkImportUI";
		}
		return "toList";
	}
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getTxtCourseName() {
		return txtCourseName;
	}

	public void setTxtCourseName(String txtCourseName) {
		this.txtCourseName = txtCourseName;
	}

	public String getTxtKnowledgeName() {
		return txtKnowledgeName;
	}

	public void setTxtKnowledgeName(String txtKnowledgeName) {
		this.txtKnowledgeName = txtKnowledgeName;
	}

	public String getTxtQuestion() {
		return txtQuestion;
	}

	public void setTxtQuestion(String txtQuestion) {
		this.txtQuestion = txtQuestion;
	}
	public File getQuestionBank() {
		return questionBank;
	}
	public void setQuestionBank(File questionBank) {
		this.questionBank = questionBank;
	}
	public String getQuestionBankFileName() {
		return questionBankFileName;
	}
	public void setQuestionBankFileName(String questionBankFileName) {
		this.questionBankFileName = questionBankFileName;
	}

	
}
