package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Course;
import com.hhit.entity.SubmitTask;
import com.hhit.entity.UserDetails;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SubmitTaskAction extends BaseAction<SubmitTask>{
	
	private Integer courseId;
	
	/** 列表 */
	public String list() throws Exception{
		//准备数据
		UserDetails userFind=getCurrentUser().getUserDetails();
		List<Course> courseList=courseService.findByUser(userFind);
		ActionContext.getContext().put("courseList", courseList);
		
		new QueryHelper(SubmitTask.class, "s")//
		.addCondition(courseId!=null, "s.task.course=?", courseService.findById(courseId))//
		.addCondition("s.task.course.student=?", userFind)//
		.preparePageBean(submitTaskService, pageNum, pageSize);
		return "list";
	}
	/** 跳转提交作业界面 */
	public String submitTaskUI() throws Exception{
		
		return "submitTaskUI";
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}
