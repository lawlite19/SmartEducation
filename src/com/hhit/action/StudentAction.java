package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Department;
import com.hhit.entity.Role;
import com.hhit.entity.Student;
import com.hhit.entity.User;
import com.hhit.entity.UserDetails;
import com.hhit.util.DepartmentUtils;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private Integer departmentId;
	
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
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		new QueryHelper(Student.class, "s")//
		.addCondition((departmentId != null), "s.department.id=?",departmentId)//
				.addCondition((viewType == 0) && (inputTerm.trim().length() > 0),"s.stuName LIKE ?", "%" + inputTerm + "%")//
				.addCondition((viewType == 1) && (inputTerm.trim().length() > 0),"s.stuNum LIKE ?", "%" + inputTerm + "%")//
				.preparePageBean(studentService, pageNum, pageSize);

		return "list";
	}
	/** 删除 */
	public String delete() throws Exception{
		studentService.delete(model.getId());
		 return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		userDetailsService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		// // 将要返回的map对象进行json处理
		// JSONObject json = JSONObject.fromObject(map);
		// // 调用json对象的toString方法转换为字符串然后赋值给result
		// this.result = json.toString();
		JsonUtil.toJson(ServletActionContext.getResponse(), map);

		return null;
	}
	
	/** 添加界面 */
	public String addUI() throws Exception{
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}
	/** 添加 */
	public String add() throws Exception {
		/** 保存用户相应信息 */
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// >> 设置所属部门
		model.setDepartment(departmentService.findById(departmentId));
		// 保存数据库
		studentService.save(model);
		// ** 保存用户登陆信息 */
		// >> 设置默认密码为账号（要使用MD5摘要）
		User userModel = new User();
		String md5Digest = DigestUtils.md5Hex(model.getStuNum());
		userModel.setPassword(md5Digest);
		userModel.setStudent(model);
		userModel.setUserNum(model.getStuNum());
		userModel.setUserType("学生");
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
		UserDetails userDetails = userDetailsService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(userDetails);
		if (userDetails.getDepartment() != null) {
			departmentId = userDetails.getDepartment().getId();
		}

		return "saveUI";
	}
	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		Student stuFind=studentService.findById(model.getId());

//		// 2，设置要修改的属性
//		userDetails.setUserName(model.getUserName());
//		userDetails.setUserNum(model.getUserNum());
//		userDetails.setSex(model.getSex());
//		userDetails.setEmail(model.getEmail());
//		userDetails.setBirthday(model.getBirthday());
//		userDetails.setTelphone(model.getTelphone());
//		userDetails.setQqNum(model.getQqNum());
//		userDetails.setWeChatNum(model.getWeChatNum());
//		userDetails.setOtherInfo(model.getOtherInfo());
//		// >> 设置所属部门
//		userDetails.setDepartment(departmentService.findById(departmentId));

		// 3，更新到数据库
//		userDetailsService.update(userDetails);

		return "toList";
	}
}
