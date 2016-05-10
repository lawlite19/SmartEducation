package com.hhit.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.Department;
import com.hhit.entity.LogFile;
import com.hhit.entity.Role;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.DepartmentUtils;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeacherAction extends BaseAction<Teacher>{
	
	private Integer departmentId;
	
	private Integer[] roleIds;
	
	private Integer classId;
	
	private int viewType;// 0姓名；1账号
	private String inputTerm = "";// 输入的词条
	
	// ajax json返回
	private String result;
	
	private String teacherNum;
	
	//老师自己添加课程对应的班级需要
	private Integer courseId;
	//班级
	private Integer[] classIds;
	//
	private Integer classSelectCourseId;
	
	/** 列表 */
	public String list() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		new QueryHelper(Teacher.class, "t")//
		.addCondition((departmentId != null), "t.department.id=?",departmentId)//
				.addCondition((viewType == 0) && (inputTerm.trim().length() > 0),"t.teaName LIKE ?", "%" + inputTerm + "%")//
				.addCondition((viewType == 1) && (inputTerm.trim().length() > 0),"t.teaNum LIKE ?", "%" + inputTerm + "%")//
				.preparePageBean(teacherService, pageNum, pageSize);

		return "list";
	}
	/** 删除 */
	public String delete() throws Exception{
		teacherService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), new Timestamp(new Date().getTime()),
				"删除【老师id="+model.getId()+"】成功"));
		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		teacherService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);

		return null;
	}
	
	/** 添加界面 */
	public String addUI() throws Exception{
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		return "saveUI";
	}
	/** 添加 */
	public String add() throws Exception {
		/** 保存用户相应信息 */
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// >> 设置所属部门和角色
		model.setDepartment(departmentService.findById(departmentId));
		List<Role> roleList=roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// 保存数据库
		teacherService.save(model);
		// ** 保存用户登陆信息 */
		// >> 设置默认密码为账号（要使用MD5摘要）
		User userModel = new User();
		String md5Digest = DigestUtils.md5Hex(model.getTeaNum());
		userModel.setPassword(md5Digest);
		userModel.setTeacher(model);
		userModel.setUserNum(model.getTeaNum());
		userModel.setUserType("老师");
		// 保存到数据库
		userService.save(userModel);

		return "toList";
	}
	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 准备回显的数据
		Teacher teaFind=teacherService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(teaFind);
		
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		//回显数据--部门
		if (teaFind.getDepartment() != null) {
			departmentId = teaFind.getDepartment().getId();
		}
		//回显数据--角色
		if (teaFind.getRoles() != null) {
			roleIds = new Integer[teaFind.getRoles().size()];
			int index = 0;
			for (Role role : teaFind.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}
	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		Teacher teaFind=teacherService.findById(model.getId());

		// 2，设置要修改的属性
		teaFind.setBirthday(model.getBirthday());
		teaFind.setFinalEdu(model.getFinalEdu());
		teaFind.setInTime(model.getInTime());
		teaFind.setSex(model.getSex());
		teaFind.setTeaName(model.getTeaName());
		teaFind.setTeaNum(model.getTeaNum());
		teaFind.setTitle(model.getTitle());
		// >> 设置所属部门和角色
		teaFind.setDepartment(departmentService.findById(departmentId));
		List<Role> roleList = roleService.findByIds(roleIds);
		teaFind.setRoles(new HashSet<Role>(roleList));
		
		// 3，更新到数据库
		teacherService.update(teaFind);

		return "toList";
	}
	/** 初始化密码 */
	public String initPassword() throws Exception{
		Teacher teaFind=teacherService.findById(model.getId());
		User userFind=userService.findByTeacher(teaFind);
		
		userFind.setPassword(DigestUtils.md5Hex("123456"));
		//更新用户
		userService.update(userFind);
		return "toList";
	}
	/** 老师对应的课程(老师可以自己添加课程) */
	public String findTeacherCourse() throws Exception{
		Map<String, Object> map=new HashMap<>();
		//根据工号查找
		Teacher teaFind=teacherService.findByTeacherNum(teacherNum);
		if(teaFind==null){
			map.put("name", "noTeacher");
		}
		else{
			if(teaFind.getCourses().size()>0){
				List<Course> courseList=new ArrayList<>(teaFind.getCourses());
				//过滤
				ClassPropertyFilter.ListCourseFilter(map, courseList);
				map.put("name", "success");
			}
			else{
				map.put("name","noCourse");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	/** 添加课程对应的班级列表界面 */
	public String listCourseClass() throws Exception{
		Teacher teaFind=getCurrentUser().getTeacher();
		if(teaFind.getCourses().size()<1){
			return "noCourseError";
		}
		else{
			//准备数据--老师对应课程
			List<Course> couseList=new ArrayList<>(teaFind.getCourses());
			ActionContext.getContext().put("couseList", couseList);

		}
		if(courseId==null){
			//准备数据--第一门课程的教学进程
			List<Course> couseList=new ArrayList<>(teaFind.getCourses());
			Course courseFind=couseList.get(0);
			//准备数据--课程名
			ActionContext.getContext().getValueStack().push(courseFind);

			List<ClassSelectCourse> classSelectCourseList=classSelectCourseService.findByTeacherNumAndCourse(teaFind.getTeaNum(),courseFind);
			ActionContext.getContext().put("classSelectCourseList", classSelectCourseList);
			courseId=courseFind.getId();
		}
		else{
			//准备数据--课程名
			ActionContext.getContext().getValueStack().push(courseService.findById(courseId));
			//准备数据--课程id
			ActionContext.getContext().put("courseId", courseId);
			//准备数据--对应课程的教学进程
			List<ClassSelectCourse> classSelectCourseList=classSelectCourseService.findByTeacherNumAndCourse(teaFind.getTeaNum(),courseService.findById(courseId));
			ActionContext.getContext().put("classSelectCourseList", classSelectCourseList);
		}
		
		return "listCourseClass";
	}
	/** 添加课程班级界面 */
	@SuppressWarnings("unchecked")
	public String addCourseClassUI() throws Exception{
		//准备数据--课程名
		Course courseFind=courseService.findById(courseId);
		ActionContext.getContext().getValueStack().push(courseFind);
		//准备数据--部门
		List<Department> topList=departmentService.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		//准备数据--班级--空集合
		List<Class_> classList=Collections.EMPTY_LIST;
		ActionContext.getContext().put("classList", classList);
		return "saveCourseClassUI";
	}
	/** 添加课程班级 */
	public String addCourseClass() throws Exception{
		Teacher teaFind=getCurrentUser().getTeacher();
		//找到班级
		List<Class_> classList= classService.findByIds(classIds);
		//找到课程
		Course courseFind=courseService.findById(courseId);
		for(int i=0;i<classList.size();i++){
			//保存选课表
			classSelectCourseService.save(new ClassSelectCourse(""+classList.get(i).getId()+"-"+teaFind.getTeaNum()+"-"+courseId,
					teaFind.getTeaNum(), classList.get(i), courseFind));
		}
		//准备数据--courseId
		ActionContext.getContext().put("courseId", courseId);
		return "toListCourseClass";
	}
	/** 删除课程班级 */
	public String deleteCourseClass() throws Exception{
		classSelectCourseService.delete(classSelectCourseId);
		
		//courseId携带过去
		ActionContext.getContext().put("courseId", courseId);
		return "toListCourseClass";
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public String getInputTerm() {
		return inputTerm;
	}
	public void setInputTerm(String inputTerm) {
		this.inputTerm = inputTerm;
	}
	public Integer[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer[] getClassIds() {
		return classIds;
	}
	public void setClassIds(Integer[] classIds) {
		this.classIds = classIds;
	}
	public Integer getClassSelectCourseId() {
		return classSelectCourseId;
	}
	public void setClassSelectCourseId(Integer classSelectCourseId) {
		this.classSelectCourseId = classSelectCourseId;
	}
	
}
