package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;

import com.hhit.entity.ATeachingAccount;
import com.hhit.entity.ATerm;
import com.hhit.entity.LogFile;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeachAccountAction extends BaseAction<ATeachingAccount> {
	
	private Integer termID;
	public Integer getTermID() {
		return termID;
	}
	public void setTermID(Integer termID) {
		this.termID = termID;
	}
	
	
	/**列表*/
	public String list() throws Exception {
		List<ATeachingAccount> teachAccountList = null;
		teachAccountList =teachAccountService.findAll();
		ActionContext.getContext().put("teachAccountList", teachAccountList);
		
		new QueryHelper(ATeachingAccount.class, "c")//
		.preparePageBean(teachAccountService, pageNum, pageSize);
		return "list";
	}
	
	/** 删除*/
	public String delete() throws Exception {
		
		teachAccountService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(), ServletActionContext.getRequest().getRemoteAddr(),
				new Timestamp(new Date().getTime()), "删除教学模式指标【id="+model.getId().toString()+"】成功"));
		return "toList";
	}
	public String addUI() throws Exception {
		List<ATerm> termList = null;
		termList =termService.findAll();
		ActionContext.getContext().put("termList", termList);
		
		return "saveUI";
	}
	public String add() throws Exception {
		model.setATerm(termService.findById(termID));
		teachAccountService.save(model);
		//操作日志保存
		logFileService.save(new LogFile(getCurrentUser().getUserNum(),getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), 
				new Timestamp(new Date().getTime()), "添加教学模式指标信息成功"));
		return "toList";
	}
	public String editUI() throws Exception {
		List<ATerm> termList = null;
		termList =termService.findAll();
		ActionContext.getContext().put("termList", termList);
		
		ATeachingAccount teachAccout = teachAccountService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(teachAccout);
		return "saveUI";
	}
	
	public String edit() throws Exception {
		ATeachingAccount teachAccout = teachAccountService.findById(model.getId());
		
		// 2，设置要修改的属性
		teachAccout.setDescription(model.getDescription());
		teachAccout.setAllPoint(model.getAllPoint());
		teachAccout.setDescriptionA(model.getDescriptionA());
		teachAccout.setDescriptionB(model.getDescriptionB());
		teachAccout.setDescriptionC(model.getDescriptionC());
		teachAccout.setDescriptionD(model.getDescriptionD());
		teachAccout.setAPoint(model.getAPoint());
		teachAccout.setBPoint(model.getBPoint());
		teachAccout.setCPoint(model.getCPoint());
		teachAccout.setDPoint(model.getDPoint());

		model.setATerm(termService.findById(termID));
		teachAccout.setATerm(model.getATerm());

		// 3，更新到数据库中
		teachAccountService.update(teachAccout);

		return "toList";
	}
}
