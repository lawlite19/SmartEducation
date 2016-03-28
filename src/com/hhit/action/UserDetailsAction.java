package com.hhit.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Department;
import com.hhit.entity.Role;
import com.hhit.entity.User;
import com.hhit.entity.UserDetails;
import com.hhit.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserDetailsAction extends BaseAction<UserDetails> {

	private Integer departmentId;
	private Integer[] roleIds;

	/** 列表 */
	public String list() throws Exception {
		List<UserDetails> userDetails = userDetailsService.findAll();
		// userDetails.get(0).
		ActionContext.getContext().put("userDetailsList", userDetails);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 直接根据id删除
		userDetailsService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		/**保存用户相信信息 */
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// >> 设置所属部门
		model.setDepartment(departmentService.findById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		//保存数据库
		userDetailsService.save(model);
		//** 保存用户登陆信息 */
		// >> 设置默认密码为账号（要使用MD5摘要）
		User userModel = new User();
		String md5Digest = DigestUtils.md5Hex(model.getUserNum());
		userModel.setPassword(md5Digest);
		userModel.setIsUsable(1);
		userModel.setUserDetails(model);
		userModel.setUserNum(model.getUserNum());
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

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据
		UserDetails userDetails = userDetailsService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(userDetails);
		if (userDetails.getDepartment() != null) {
			departmentId = userDetails.getDepartment().getId();
		}
		if (userDetails.getRoles() != null) {
			roleIds = new Integer[userDetails.getRoles().size()];
			int index = 0;
			for (Role role : userDetails.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		UserDetails userDetails = userDetailsService.findById(model.getId());

		// 2，设置要修改的属性
		userDetails.setUserName(model.getUserName());
		userDetails.setUserNum(model.getUserNum());
		userDetails.setSex(model.getSex());
		userDetails.setEmail(model.getEmail());
		userDetails.setBirthday(model.getBirthday());
		userDetails.setTelphone(model.getTelphone());
		userDetails.setQqNum(model.getQqNum());
		userDetails.setWeChatNum(model.getWeChatNum());
		userDetails.setOtherInfo(model.getOtherInfo());
		// >> 设置所属部门
		userDetails.setDepartment(departmentService.findById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.findByIds(roleIds);
		userDetails.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userDetailsService.update(userDetails);

		return "toList";
	}

	/** 初始化密码为登陆账号 */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		UserDetails userDetails=userDetailsService.findById(model.getId());
		User user = userService.findByDetailsId(userDetails);

		// 2，设置要修改的属性（要使用MD5摘要）
		String md5Digest = DigestUtils.md5Hex(user.getUserNum());
		user.setPassword(md5Digest);

		// 3，更新到数据库
		userService.update(user);
		return "toList";
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

}
