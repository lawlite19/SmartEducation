package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Role;
import com.hhit.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
//	@Resource
//	抽取到BaseAction中
//	private IRoleService roleService;

	private Integer id;

	private Role role;

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(id);
		return "toList";
	}

	/** 修改界面 */
	public String editUI() throws Exception {
		// 准备回显的数据
		role = roleService.findById(id);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中获取原对象
		Role roleFind = roleService.findById(id);

		// 2，设置要修改的属性
		roleFind.setRoleName(role.getRoleName());
		roleFind.setDescription(role.getDescription());
		// roleFind.setAddDate(new Timestamp(new Date().getTime()));
		// 3，更新到数据库
		roleService.update(roleFind);
		return "toList";
	}

	/** 添加 */
	public String add() throws Exception {
		role.setAddDate(new Timestamp(new Date().getTime()));
		roleService.save(role);
		return "toList";
	}
	/** 跳转到添加界面 */
	public String addUI() throws Exception{
		return "saveUI";
	}
	/** 设置权限页面 */
	public String setPrivilegeUI() throws Exception {
		// 准备回显的数据
		Role rolefind = roleService.findById(id);
		ActionContext.getContext().getValueStack().push(rolefind);
		// 根据RoleId获得对应的权限
		// List<TbPrivilege> privileges=
		// rolePriIdService.getPriByRoleId(rolefind);

		// if (rolefind.getTbRolePrivileges() != null) {
		// privilegeIds = new Integer[rolefind.getTbRolePrivileges().size()];
		// int index = 0;
		// for (TbRolePrivilege priv : rolefind.getTbRolePrivileges()) {
		// privilegeIds[index++] = priv.getId().getTbPrivilege().getId();
		// }
		// }
		// 准备数据 privilegeList
		// List<TbPrivilege> privilegeList = privilegeService.findAll();
		// ActionContext.getContext().put("privilegeList", privilegeList);
		return "setPrivilegeUI";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
