package com.hhit.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Course;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeachProcessAction extends BaseAction<TeachProcess>{
	
	private Integer courseId;
	
	/** 列表 */
	public String list() throws Exception{
		Teacher teaFind=getCurrentUser().getTeacher();
		
		if(teaFind.getCourses()!=null){
			//准备数据--老师对应课程
			List<Course> couseList=new ArrayList<>(teaFind.getCourses());
			ActionContext.getContext().put("couseList", couseList);
		}
		if(courseId==null){
			//准备数据--第一门课程的教学进程
			Course courseFind=couseList.get(0);
			List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind,courseFind);
			ActionContext.getContext().put("teachProcessList", teachProcessList);
		}
		else{
			//准备数据--课程id
			ActionContext.getContext().put("courseId", courseId);
			//准备数据--对应课程的教学进程
			List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind,courseService.findById(courseId));
			ActionContext.getContext().put("teachProcessList", teachProcessList);
		}
		return "list";
	}
	
	
	
	
	//=========
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
}
