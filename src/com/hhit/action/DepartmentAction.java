package com.hhit.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Department;
import com.hhit.service.IDepartmentService;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	
//	@Resource
//	抽取到BaseAction中
//	private IDepartmentService departmentService;
	
	private Integer parentId;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
	
}
