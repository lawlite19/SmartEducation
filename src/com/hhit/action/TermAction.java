package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ATerm;
import com.hhit.entity.LogFile;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TermAction extends BaseAction<ATerm> {
	private Integer termId;	
	/** 列表 */
	public String list() throws Exception {
		List<ATerm> termList = null;
		termList =termService.findList();
		ActionContext.getContext().put("termList", termList);

		return "list";
	}
	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}
	public String add() throws Exception {

		termService.save(model);
		//操作日志保存
		logFileService.save(new LogFile(getCurrentUser().getUserNum(),getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), 
				new Timestamp(new Date().getTime()), "添加评价基础信息成功"));
		return "toList";
	}
	/** 修改 */
	public String editUI() throws Exception {
		ATerm term = termService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(term);
		return "saveUI";
	}
	public String edit() throws Exception {
		ATerm term = termService.findById(model.getId());
		
		// 2，设置要修改的属性
		term.setName(model.getName());
		term.setStartTime(model.getStartTime());
		term.setEndTime(model.getEndTime());

		// 3，更新到数据库中
		termService.update(term);

		return "toList";
	}
	/** 删除*/
	public String delete() throws Exception {
		
		termService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(), ServletActionContext.getRequest().getRemoteAddr(),
				new Timestamp(new Date().getTime()), "删除学期【id="+model.getId().toString()+"】成功"));
		return "toList";
	}
	
}
