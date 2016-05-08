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
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class JudgementAction extends BaseAction<Judgement>{
	
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
		new QueryHelper(Judgement.class, "j")//
		.addCondition(txtCourseName.trim()!="", "j.course.courseName LIKE ?", "%"+txtCourseName.trim()+"%")//
		.addCondition(txtKnowledgeName.trim()!="", "j.knowledgeName LIKE ?", "%"+txtKnowledgeName.trim()+"%")//
		.addCondition(txtQuestion.trim()!="", "j.question LIKE ?", "%"+txtQuestion.trim()+"%")
		.preparePageBean(judgementService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		//直接根据id删除
		judgementService.delete(model.getId());
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
		judgementService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		//准备数据--课程
		List<Course> courseList=courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);


		// 准备回显的数据
		Judgement judgementFind=judgementService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(judgementFind);
		//准备数据--章节
		if(judgementFind.getCourse()!=null){
			List<Chapter> chapterList=chapterService.findByCourse(judgementFind.getCourse());
			ActionContext.getContext().put("chapterList", chapterList);
		}
		else{
			List<Chapter> chapterList=Collections.EMPTY_LIST;
			ActionContext.getContext().put("chapterList", chapterList);
		}
		//回显--课程
		if(judgementFind.getCourse()!=null){
			courseId=judgementFind.getCourse().getId();
		}
		//回显--章节
		if(judgementFind.getChapter()!=null){
			chapterId=judgementFind.getChapter().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		Judgement judgementFind=judgementService.findById(model.getId());
		
		// 2，设置要修改的属性
		judgementFind.setAnswer(model.getAnswer());
		judgementFind.setDifExponent(model.getDifExponent());
		judgementFind.setKnowledgeName(model.getKnowledgeName());
		judgementFind.setModifyTime(new Timestamp(new Date().getTime()));
		judgementFind.setQuestion(model.getQuestion());
		judgementFind.setQuestionType(model.getQuestionType());
		
		// 3，更新到数据库
		judgementService.update(judgementFind);
		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		userDetailsService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
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
	
}
