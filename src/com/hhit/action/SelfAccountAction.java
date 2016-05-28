package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ASelfAccessAccount;
import com.hhit.entity.AStudentAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.entity.LogFile;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SelfAccountAction extends BaseAction<ASelfAccessAccount> {
	private Integer termID;
	public Integer getTermID() {
		return termID;
	}
	public void setTermID(Integer termID) {
		this.termID = termID;
	}
	/**列表*/
	public String list() throws Exception {
		List<ASelfAccessAccount> selfAccountList = null;
		selfAccountList =selfAccountService.findAll();
		ActionContext.getContext().put("selfAccountList", selfAccountList);
		
		new QueryHelper(ASelfAccessAccount.class, "c")//
		.preparePageBean(selfAccountService, pageNum, pageSize);
		return "list";
	}
	/** 删除*/
	public String delete() throws Exception {
		
		selfAccountService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(), ServletActionContext.getRequest().getRemoteAddr(),
				new Timestamp(new Date().getTime()), "删除自我评价指标【id="+model.getId().toString()+"】成功"));
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
		selfAccountService.save(model);
		//操作日志保存
		logFileService.save(new LogFile(getCurrentUser().getUserNum(),getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), 
				new Timestamp(new Date().getTime()), "添加自我评价指标信息成功"));
		return "toList";
	}
	public String editUI() throws Exception {
		List<ATerm> termList = null;
		termList =termService.findAll();
		ActionContext.getContext().put("termList", termList);
		
		ASelfAccessAccount selfAccout = selfAccountService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(selfAccout);
		return "saveUI";
	}
	
	public String edit() throws Exception {
		ASelfAccessAccount selfAccout = selfAccountService.findById(model.getId());
		
		// 2，设置要修改的属性
		selfAccout.setDescription(model.getDescription());
		selfAccout.setAllPoint(model.getAllPoint());
		selfAccout.setDescriptionA(model.getDescriptionA());
		selfAccout.setDescriptionB(model.getDescriptionB());
		selfAccout.setDescriptionC(model.getDescriptionC());
		selfAccout.setDescriptionD(model.getDescriptionD());
		selfAccout.setAPoint(model.getAPoint());
		selfAccout.setBPoint(model.getBPoint());
		selfAccout.setCPoint(model.getCPoint());
		selfAccout.setDPoint(model.getDPoint());

		model.setATerm(termService.findById(termID));
		selfAccout.setATerm(model.getATerm());

		// 3，更新到数据库中
		selfAccountService.update(selfAccout);

		return "toList";
	}
}
