package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Privilege;
import com.hhit.entity.Role;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	// @Resource
	// 抽取到BaseAction中
	// private IRoleService roleService;

	private Integer[] privilegeIds;

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 修改界面 */
	public String editUI() throws Exception {
		// 准备回显的数据
		ActionContext.getContext().getValueStack()
				.push(roleService.findById(model.getId()));
		// role = roleService.findById(model.getId());
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中获取原对象
		Role roleFind = roleService.findById(model.getId());
		// 2，设置要修改的属性
		roleFind.setRoleName(model.getRoleName());
		roleFind.setDescription(model.getDescription());
		// roleFind.setAddDate(new Timestamp(new Date().getTime()));
		// 3，更新到数据库
		roleService.update(roleFind);
		return "toList";
	}

	/** 添加 */
	public String add() throws Exception {
		model.setAddDate(new Timestamp(new Date().getTime()));
		roleService.save(model);
		return "toList";
	}

	/** 跳转到添加界面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 设置权限页面 */
	public String setPrivilegeUI() throws Exception {
		// 准备回显的数据
		Role role = roleService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		if (role.getPrivileges() != null) {
			privilegeIds = new Integer[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}
		// 准备数据 privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		return "setPrivilegeUI";
	}

	/** 设置权限 */
	public String setPrivilege() throws Exception {
		// 1，从数据库中获取原对象
		Role role = roleService.findById(model.getId());

		// 2，设置要修改的属性
		List<Privilege> privilegeList = privilegeService.findByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3，更新到数据库
		roleService.update(role);

		return "toList";
	}

	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
