package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.entity.LogFile;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PeerAccountAction extends BaseAction<APeerAccessAccount> {
	private Integer termID;
	public Integer getTermID() {
		return termID;
	}
	public void setTermID(Integer termID) {
		this.termID = termID;
	}
	
	
	
	/**列表*/
	public String list() throws Exception {
		List<APeerAccessAccount> peerAccountList = null;
		peerAccountList =peerAccountService.findAll();
		ActionContext.getContext().put("peerAccountList", peerAccountList);
		
		new QueryHelper(APeerAccessAccount.class, "c")//
		.preparePageBean(peerAccountService, pageNum, pageSize);
		return "list";
	}
	/** 删除*/
	public String delete() throws Exception {
		
		peerAccountService.delete(model.getId());
		logFileService.save(new LogFile(getCurrentUser().getUserNum(), getCurrentUser().getUserType(), ServletActionContext.getRequest().getRemoteAddr(),
				new Timestamp(new Date().getTime()), "删除同行评价指标【id="+model.getId().toString()+"】成功"));
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
		peerAccountService.save(model);
		//操作日志保存
		logFileService.save(new LogFile(getCurrentUser().getUserNum(),getCurrentUser().getUserType(),
				ServletActionContext.getRequest().getRemoteAddr(), 
				new Timestamp(new Date().getTime()), "添加同行评价指标信息成功"));
		return "toList";
	}
	public String editUI() throws Exception {
		List<ATerm> termList = null;
		termList =termService.findAll();
		ActionContext.getContext().put("termList", termList);
		
		APeerAccessAccount peerAccout = peerAccountService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(peerAccout);
		return "saveUI";
	}
	
	public String edit() throws Exception {
		APeerAccessAccount peerAccout = peerAccountService.findById(model.getId());
		
		// 2，设置要修改的属性
		peerAccout.setDescription(model.getDescription());
		peerAccout.setPoints(model.getPoints());
		model.setATerm(termService.findById(termID));
		peerAccout.setATerm(model.getATerm());

		// 3，更新到数据库中
		peerAccountService.update(peerAccout);

		return "toList";
	}
}
