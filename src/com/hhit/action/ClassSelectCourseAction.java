package com.hhit.action;

import java.util.ArrayList;
import java.util.Collections;
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
import com.hhit.entity.Teacher;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ClassSelectCourseAction extends BaseAction<ClassSelectCourse>{
	
	//查询
	private String className="";
	private String teacherNum="";
	private String courseName="";
	//
	private Integer classId;
	private Integer courseId;
	
	/** 列表 */
	public String list() throws Exception {
		// 准备分页信息
		new QueryHelper(ClassSelectCourse.class, "c")//
		.addCondition(className.trim()!="", "c.class_.className LIKE ?", "%"+className.trim()+"%")//
		.addCondition(teacherNum.trim()!="", "c.teacherNum LIKE ?", "%"+teacherNum.trim()+"%")//
		.addCondition(courseName.trim()!="", "c.course.courseName LIKE ?", "%"+courseName.trim()+"%")//
		.preparePageBean(classSelectCourseService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		//直接根据id删除
		classSelectCourseService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据--班级
		List<Class_> classList=classService.findAll();
		ActionContext.getContext().put("classList", classList);
		//准备空集合--课程
		List<Course> courseList=Collections.EMPTY_LIST;
		ActionContext.getContext().put("courseList", courseList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// 设置没有封装的属性
		model.setClass_(classService.findById(classId));
		model.setCourse(courseService.findById(courseId));
		model.setSelectNum(""+classId+"-"+model.getTeacherNum()+"-"+courseId);
		// 保存到数据库
		classSelectCourseService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		//准备数据--源对象
		ClassSelectCourse classSelectCourseFind=classSelectCourseService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(classSelectCourseFind);
		// 准备数据--班级
		List<Class_> classList=classService.findAll();
		ActionContext.getContext().put("classList", classList);
		//准备数据--课程
		Teacher teaFind=teacherService.findByTeacherNum(classSelectCourseFind.getTeacherNum());
		List<Course> courseList=new ArrayList<>(teaFind.getCourses());
		ActionContext.getContext().put("courseList", courseList);
		// 准备回显--班级
		classId=classSelectCourseFind.getClass_().getId();
		courseId=classSelectCourseFind.getCourse().getId();
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		ClassSelectCourse classSelectCourseFind=classSelectCourseService.findById(model.getId());
		// 2，设置要修改的属性
		// >> 设置所属部门
		classSelectCourseFind.setClass_(classService.findById(classId));
		classSelectCourseFind.setCourse(courseService.findById(courseId));
		classSelectCourseFind.setSelectNum(""+classId+"-"+model.getTeacherNum()+courseId);
		classSelectCourseFind.setTeacherNum(teacherNum);
		// 3，更新到数据库
		classSelectCourseService.update(classSelectCourseFind);
		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		classSelectCourseService.delete(model.getId());
		map.put("name", "ok");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);

		return null;
	}
//=============
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	
}
