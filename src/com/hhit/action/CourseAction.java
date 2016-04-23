package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Course;
import com.hhit.entity.UserDetails;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CourseAction extends BaseAction<Course>{

	/** 列表 */
	public String list() throws Exception{
		UserDetails userFind=getCurrentUser().getUserDetails();
		List<Course> courseList=courseService.findByUser(userFind);
		ActionContext.getContext().put("courseList", courseList);
		return "list";
	}
	/** 删除 */
	public String delete() throws Exception{
		courseService.delete(model.getId());
		return "toList";
	}
	/** 跳转课程添加界面 */
	public String addUI() throws Exception{
		return "saveUI";
	}
	/** 添加 */
	public String add() throws Exception{
		UserDetails userDetailsFind=getCurrentUser().getUserDetails();
		//设置相关属性
		model.setAddTime(new Timestamp(new Date().getTime()));
		model.setDepartment(userDetailsFind.getDepartment());
//		model.setTeacher(userDetailsFind);
		//保存
		courseService.save(model);
		return "toList";
	}
	/** 跳转课程修改界面 */
	public String editUI() throws Exception{
		//准备数据
		Course courseFind=courseService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(courseFind);
		return "saveUI";
	}
	/** 修改 */
	public String edit() throws Exception{
		//查询出原对象
		Course courseFind=courseService.findById(model.getId());
		//设置相关属性
//		courseFind.setCourseLevel(model.getCourseLevel());
		courseFind.setCourseName(model.getCourseName());
		courseFind.setDescription(model.getDescription());
		//更新数据库
		courseService.update(courseFind);
		return "toList";
	}
}
