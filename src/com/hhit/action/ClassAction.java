package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;













import com.hhit.base.BaseAction;
import com.hhit.entity.Class_;
import com.hhit.entity.Department;
import com.hhit.entity.LogFile;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.DepartmentUtils;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ClassAction extends BaseAction<Class_>{

	private Integer departmentId;
	
	//查询输入条件
	private String inputTerm="";
	//json
	private String result;
	
	/** 列表 */
	public String list() throws Exception {
		// 准备数据
		List<Department> topList=departmentService.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 准备分页信息
		new QueryHelper(Class_.class, "c")//
		.addCondition((departmentId != null), "c.department.id=?",departmentId)//
		.addCondition(inputTerm.trim().length() > 0, "c.className LIKE ?", "%"+inputTerm+"%")
		.preparePageBean(classService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		classService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), new Timestamp(new Date().getTime()),
				"删除【班级id="+model.getId()+"】成功"));
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据
		List<Department> topList=departmentService.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// 设置没有封装的属性
		model.setDepartment(departmentService.findById(departmentId));
		// 保存到数据库
		classService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据
		List<Department> topList=departmentService.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备回显的数据
		Class_ classFind=classService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(classFind);
		if(classFind.getDepartment()!=null)
			departmentId=classFind.getDepartment().getId();
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		Class_ classFind=classService.findById(model.getId());
		// 2，设置要修改的属性
		classFind.setClassName(model.getClassName());
		classFind.setClassNum(model.getClassNum());
		classFind.setGrade(model.getGrade());
		// >> 设置所属部门
		classFind.setDepartment(departmentService.findById(departmentId));
		
		// 3，更新到数据库
		classService.update(classFind);
		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		classService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		// // 将要返回的map对象进行json处理
		// JSONObject json = JSONObject.fromObject(map);
		// // 调用json对象的toString方法转换为字符串然后赋值给result
		// this.result = json.toString();
		JsonUtil.toJson(ServletActionContext.getResponse(), map);

		return null;
	}
	/** 根据部门查找对应的班级，用于动态加载班级select */
	public String findByDeptId() throws Exception{
		Map<String, Object> map=new HashMap<>();
		//查找部门
		Department deptFind=departmentService.findById(departmentId);
		List<Class_> classList=classService.findByDept(deptFind);
		if(classList.size()>0){
			ClassPropertyFilter.ListClassFilter(map, classList);
			map.put("name", "success");
		}
		else{
			map.put("name", "noClass");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}


	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getInputTerm() {
		return inputTerm;
	}

	public void setInputTerm(String inputTerm) {
		this.inputTerm = inputTerm;
	}


}
