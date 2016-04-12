package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Course;
import com.hhit.entity.Task;
import com.hhit.entity.UserDetails;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings({ "unchecked", "serial" })
@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Task>{
	private Integer courseId;

	/** 列表 */
	public String list() throws Exception{
		//准备数据
		//查找登录用户对应的课程
		UserDetails userDetails=getCurrentUser().getUserDetails();
		List<Course> courseList=courseService.findByUser(userDetails);
		ActionContext.getContext().put("courseList", courseList);
		new QueryHelper(Task.class, "t")//
		.addCondition("t.course.teacher=?", getCurrentUser().getUserDetails())//
		.addCondition(courseId!=null, "t.course=?", courseService.findById(courseId))//
		.addOrderProperty("id", false)//false为降序
		.preparePageBean(taskService, pageNum, pageSize);
		return "list";
	}
	
	/** 跳转布置作业界面 */
	public String addUI() throws Exception{
		//准备数据
		//查找登录用户对应的课程
		UserDetails userDetails=getCurrentUser().getUserDetails();
		List<Course> courseList=courseService.findByUser(userDetails);
		ActionContext.getContext().put("courseList", courseList);
		
		return "saveUI";
	}
	/** 布置作业 */
	public String add() throws Exception{
		//设置对应隐含属性
		Course courseFind=courseService.findById(courseId);
		model.setReleaseTime(new Timestamp(new Date().getTime()));
		model.setCourse(courseFind);
		//保存对象
		taskService.save(model);
		return "toList";
	}
	/** 跳转修改作业界面 */
	public String editUI() throws Exception{
		//准备数据
		//课程select
		UserDetails userDetails=getCurrentUser().getUserDetails();
		List<Course> courseList=courseService.findByUser(userDetails);
		ActionContext.getContext().put("courseList", courseList);
		//传递过来的对象信息
		Task taskFind=taskService.findById(model.getId());
		courseId=taskFind.getCourse().getId();
		ActionContext.getContext().getValueStack().push(taskFind);
		return "saveUI";
	}
	/** 修改作业 */
	public String edit() throws Exception{
		//查找源对象
		Task taskFind=taskService.findById(model.getId());
		
		//设置相关属性
		taskFind.setCourse(courseService.findById(courseId));
		taskFind.setDescription(model.getDescription());
		taskFind.setEndDate(model.getEndDate());
		taskFind.setTaskName(model.getTaskName());
		
		//更新
		taskService.update(taskFind);
		return "toList";
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}
