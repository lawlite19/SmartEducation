package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ChartDirector.GetSessionImage;

import com.hhit.base.BaseAction;
import com.hhit.entity.Class_;
import com.hhit.entity.Department;
import com.hhit.entity.LogFile;
import com.hhit.entity.Role;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
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
	
}
