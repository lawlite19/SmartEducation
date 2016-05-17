package com.hhit.action;	

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.APeerAccess;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PeerAccessAction extends BaseAction<APeerAccess> {
	String[] accessgrade; 
	
	public String[] getAccessgrade() {
		return accessgrade;
	}
	public void setAccessgrade(String[] accessgrade) {
		this.accessgrade = accessgrade;
	}
	public String list(){
	//	int termid=termService.findMaxId();
	//	ATerm term=termService.findById(termid);
		User leader=getCurrentUser();
		Teacher teacher=teacherService.findByTeaNum(leader.getUserNum());
		List<Teacher> teacherList=teacherService.findByLead(leader.getUserNum(),teacher.getDepartment());
	//	List<APeerAccess> peerAccessList=peerAccessService.findByTerm(term); 
		
		ActionContext.getContext().put("teacherList", teacherList);
		new QueryHelper(Teacher.class, "c").preparePageBean(teacherService, pageNum, pageSize);
		return "list";
	}
	public String access(){
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		
		Date nowdate=new Date();
		if(nowdate.after(term.getStartTime())&&nowdate.before(term.getEndTime()))
				{
				List<APeerAccessAccount> peeraccoutList=peerAccountService.findByTerm(term);
				ActionContext.getContext().put("peeraccoutList", peeraccoutList);
				return "access";
				}
		else{
			ActionContext.getContext().put("authority", 0);
			return "toList";
		} 
	}
	public String success(){
		User leader=getCurrentUser();
		Teacher teacher=teacherService.findByTeaNum(leader.getUserNum());
		int i,point;
		double sum=0;
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		List<APeerAccessAccount> peeraccoutList=peerAccountService.findByTerm(term);
		for(i=0;i<peeraccoutList.size();i++)
		{
			point=peeraccoutList.get(i).getPoints();
			sum=(Double.valueOf(accessgrade[i]).doubleValue()*10+50)/100*point+sum;
		}
		model.setATerm(term);
		model.setLeader(teacher);
		int teacherid=model.getId();
		Teacher teacherd=teacherService.findById(teacherid);
		model.setTeacher(teacherd);
		model.setPeerAccess(sum);
		peerAccessService.save(model);
		
		return "toList";
	}
}
