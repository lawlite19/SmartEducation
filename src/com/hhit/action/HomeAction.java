package com.hhit.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.entity.Privilege;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	private Integer parentId;
	// private ServletContext servletContext;
	private List<Privilege> privilegeList;

	public String index() throws Exception {
		return "index";
	}

	public String top() throws Exception {
		return "top";
	}

	public String left() throws Exception {
		// 获得初始化容器中的权限对象集合
		@SuppressWarnings("unchecked")
		// List<Privilege> privileges = (List<Privilege>)
		// servletContext.getAttribute("secondPrivilegeList");
		List<Privilege> privileges = (List<Privilege>) ActionContext
				.getContext().getApplication().get("secondPrivilegeList");
		if (parentId == null) {
			// TODO
		} else {
			privilegeList = new ArrayList<Privilege>();
			for (Privilege pri : privileges) {
				if (pri.getParent().getId().equals(parentId)) {
					privilegeList.add(pri);
				}
			}
		}
		// 放到栈顶显示数据
		ActionContext.getContext().put("privilegeList", privilegeList);
		return "left";
	}

	public String right() throws Exception {
		return "right";
	}

	public String switchLeft() throws Exception {
		return "switchLeft";
	}

	public String switchTop() throws Exception {
		return "switchTop";
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	// @Override
	// public void setServletContext(ServletContext serv) {
	// servletContext = serv;
	// }
	//
	// public ServletContext getServletContext() {
	// return servletContext;
	// }

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

}
