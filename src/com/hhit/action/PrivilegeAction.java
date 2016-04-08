package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Privilege;
import com.hhit.entity.Role;
import com.hhit.util.PrivilegeUtils;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings({ "unchecked", "serial" })
@Controller
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege> {

	private Integer[] privilegeIds;

	private Integer privilegeId;

	/** 跳转功能管理界面 */
	public String toPrivilegeUI() throws Exception {

		// 准备数据 privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		//设置select的功能树
		List<Privilege> topPrivilegeList = (List<Privilege>) ActionContext.getContext().getApplication().get("topPrivilegeList");
		List<Privilege> selectPrivilegeList = PrivilegeUtils.getAllPrivileges(topPrivilegeList);
		ActionContext.getContext().put("selectPrivilegeList",selectPrivilegeList);
		return "toPrivilegeUI";
	}
	
	/** 修改功能 */
	public String editUI() throws Exception{
		
		// 准备数据 privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		//设置select的功能树
		List<Privilege> topPrivilegeList = (List<Privilege>) ActionContext.getContext().getApplication().get("topPrivilegeList");
		List<Privilege> selectPrivilegeList = PrivilegeUtils.getAllPrivileges(topPrivilegeList);
		ActionContext.getContext().put("selectPrivilegeList",selectPrivilegeList);
		
		//准备回显数据
		
		privilegeId = privilegeService.findById(model.getId()).getParent().getId();
		
		Privilege privilegeFind=privilegeService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(privilegeFind);
		
		return "toPrivilegeUI";
	}
	//添加功能
	public String add() throws Exception{
		//设置父级功能
		model.setParent(privilegeService.findById(privilegeId));
		privilegeService.save(model);
		//实际需要重启服务器才行，因为功能是启动服务器时就加载好的
		//之后返回提示信息
		return toPrivilegeUI();
	}
	//修改功能
	public String edit() throws Exception{
		
		model.setParent(privilegeService.findById(privilegeId));
		privilegeService.update(model);
		
		//实际也需要重启服务器才行，因为功能是启动服务器时就加载好的
		//之后返回提示信息
		return toPrivilegeUI();
	}
	
	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

}
