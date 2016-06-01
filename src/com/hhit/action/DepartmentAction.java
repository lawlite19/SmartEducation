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
import com.hhit.entity.Department;
import com.hhit.entity.LogFile;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.DepartmentUtils;
import com.hhit.util.JsonUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	// @Resource
	// 抽取到BaseAction中
	// private IDepartmentService departmentService;
	private Integer parentId;
	
	// ajax json返回
	private String result;

	private String stuNum;
	
	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) { // 顶级部门列表
			departmentList = departmentService.findTopList();
		} else { // 子部门列表
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.findById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		
		departmentService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(), ServletActionContext.getRequest().getRemoteAddr(),
				new Timestamp(new Date().getTime()), "删除部门【id="+model.getId().toString()+"】成功"));
		return "toList";
	}
	
	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}
	/** 添加 */
	public String add() throws Exception {
		// 封装信息到对象中
		Department parent = departmentService.findById(parentId);
		model.setParent(parent);
		// 保存
		departmentService.save(model);
		//操作日志保存
		logFileService.save(new LogFile(getCurrentUser().getUserNum(),getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), 
				new Timestamp(new Date().getTime()), "添加部门【"+model.getDeptName()+"】成功"));
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
		Department department = departmentService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库取出原对象
		Department department = departmentService.findById(model.getId());

		// 2，设置要修改的属性
		department.setDeptName(model.getDeptName());
		department.setDeptLevel(model.getDeptLevel());
		department.setDeptDescription(model.getDeptDescription());
		department.setParent(departmentService.findById(parentId)); // 设置所属的上级部门

		// 3，更新到数据库中
		departmentService.update(department);

		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		departmentService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(), ServletActionContext.getRequest().getRemoteAddr(),
				new Timestamp(new Date().getTime()), "删除部门【id="+model.getId().toString()+"】成功"));
		result = "ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);

		return null;
	}
	
//app
//============================================
	public String appStuDeptLevel3() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Student stuFind=studentService.findByStuNum(stuNum);
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			Department deptFind=stuFind.getDepartment();
			if(deptFind==null){
				map.put("name", "noDept");
			}
			else{
				Department deptLevel2=deptFind.getParent();
				List<Department> deptList=departmentService.findByParent(deptLevel2);
				ClassPropertyFilter.ListDepartmentFilter(map, deptList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//系里的老师
	public String appDeptLevel3Tea() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Department deptFind=departmentService.findById(model.getId());
		if(deptFind==null){
			map.put("name", "noDept");
		}
		else{
			List<Teacher> teaList=teacherService.findByDept(deptFind);
			if(teaList.size()<1){
				map.put("name", "noTeacher");
			}
			else{
				ClassPropertyFilter.ListTeacherFilter(map, teaList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

}
