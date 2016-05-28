package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ATatics;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TaticsAction extends BaseAction<ATatics> {
	int id;
	/** 列表 */
	public String list() throws Exception {
		List<ATatics> taticsList = null;
		taticsList =taticsService.findAll();
		ATatics tatics=null;
		id=taticsService.findMaxId();
		tatics =taticsService.findById(id);
		ActionContext.getContext().put("taticsList", taticsList);
		ActionContext.getContext().put("tatics", tatics);
		return "list";
	}
}
