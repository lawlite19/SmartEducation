package com.hhit.action;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.Judgement;
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

	
}
