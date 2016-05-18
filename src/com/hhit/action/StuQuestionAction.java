package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.PageBean;
import com.hhit.entity.StuQuestion;
import com.hhit.entity.Student;
import com.hhit.entity.TeaAnswer;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StuQuestionAction extends BaseAction<StuQuestion>{
	
	private Integer courseId;


	
//app
//==========================
	//学生提问
	public String appStuQuestion() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//找到学生
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			
			//找到课程
			Course courseFind=courseService.findById(courseId);
			if(courseFind==null){
				map.put("name", "noCourse");
			}
			else{
				//找到班级
				Class_ classFind=stuFind.getClass_();
				//找到选课
				ClassSelectCourse classCourseFind=classSelectCourseService.findByClassAndCourse(classFind, courseFind);
				//得到teaNum
				String teaNum=classCourseFind.getTeacherNum();
				//设置未封装属性
				model.setDone(0);
				model.setAddTime(new Timestamp(new Date().getTime()));
				model.setTeaNum(teaNum);
				
				//保存
				stuQuestionService.save(model);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//学生的问题列表--分页
	public String appListStuQuestion() throws Exception{
		Map<String, Object> map=new HashMap<>();
		PageBean pageBean=new QueryHelper(StuQuestion.class, "s")//
							.addCondition("s.stuNum=?", model.getStuNum())//
							.addOrderProperty("s.id", false)//false 为倒叙
							.prepareAppPageBean(stuQuestionService, pageNum, pageSize);
		if(pageBean==null){
			map.put("name", "noRecord");
		}
		else{
			map.put("stuQuestions", pageBean.getRecordList());
			map.put("count", pageBean.getRecordCount());
			map.put("currentPage", pageBean.getCurrentPage());
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//老师的问题列表--分页
	public String appListTeaQuestion() throws Exception{
		Map<String, Object> map=new HashMap<>();
		PageBean pageBean=new QueryHelper(StuQuestion.class, "s")//
		.addCondition("s.teaNum=?", model.getTeaNum())//
		.addOrderProperty("s.id", false)//false 为倒叙
		.prepareAppPageBean(stuQuestionService, pageNum, pageSize);
		if(pageBean==null){
			map.put("name", "noRecord");
		}
		else{
			map.put("stuQuestions", pageBean.getRecordList());
			map.put("count", pageBean.getRecordCount());
			map.put("currentPage", pageBean.getCurrentPage());
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//问题详情
	public String appStuQuestionDetails() throws Exception{
		Map<String, Object> map=new HashMap<>();
		//找到问题
		StuQuestion stuQuestionFind=stuQuestionService.findById(model.getId());
		if(stuQuestionFind==null){
			map.put("name", "noStuQuesiton");
		}
		else{
			map.put("stuQuestion", stuQuestionFind);
			List<TeaAnswer> teaAnswerList=teaAnswerService.findByQuestion(stuQuestionFind);
			if(teaAnswerList.size()<1){
				map.put("name", "noAnswer");
			}
			else{
				ClassPropertyFilter.ListTeaAnswerFilter(map, teaAnswerList);
			}
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}
