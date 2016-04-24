package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Department;
import com.hhit.entity.Role;
import com.hhit.entity.Student;
import com.hhit.util.DepartmentUtils;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private Integer departmentId;
	
	private int viewType;// 0姓名；1账号
	private String inputTerm = "";// 输入的词条
	
	
	/** 列表 */
	public String list() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		new QueryHelper(Student.class, "s")//
		.addCondition((departmentId != null), "u.department.id=?",departmentId)//
				.addCondition(
						(viewType == 0) && (inputTerm.trim().length() > 0),
						"u.userName LIKE ?", "%" + inputTerm + "%")//
				.addCondition(
						(viewType == 1) && (inputTerm.trim().length() > 0),
						"u.userNum LIKE ?", "%" + inputTerm + "%")//
				.preparePageBean(studentService, pageNum, pageSize);

		return "list";
	}
}
