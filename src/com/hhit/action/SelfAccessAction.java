package com.hhit.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.ASelfAccessAccount;
import com.hhit.entity.AStudentAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Course;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SelfAccessAction extends BaseAction<ClassSelectCourse> {
	
	/**读取评价分数**/
	Double[] accessgrade; 
	String teaNum;
	int courseFind;
	double selfGrade;
	public String getTeaNum() {
		return teaNum;
	}
	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}
	public int getCourseFind() {
		return courseFind;
	}
	public void setCourseFind(int courseFind) {
		this.courseFind = courseFind;
	}
	public double getSelfGrade() {
		return selfGrade;
	}
	public void setSelfGrade(double selfGrade) {
		this.selfGrade = selfGrade;
	}
	public Double[] getAccessgrade() {
		return accessgrade;
	}
	public void setAccessgrade(Double[] accessgrade) {
		this.accessgrade = accessgrade;
	}
	
	public String list(){
		ATerm term=termService.findById(termService.findMaxId());
		User user=getCurrentUser();
		Teacher teacher=user.getTeacher();
		List<ClassSelectCourse>  tealist=classSelectCourseService.findByTeacherNumAndTerm(teacher.getTeaNum(),term);
		ActionContext.getContext().put("tealist", tealist);
		new QueryHelper(ClassSelectCourse.class, "c").preparePageBean(classSelectCourseService, pageNum, pageSize);
		return "list";
	}
	public String access(){
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		
		Date nowdate=new Date();
		if(nowdate.after(term.getStartTime())&&nowdate.before(term.getEndTime()))
				{
				List<ASelfAccessAccount> selfaccoutList=selfAccountService.findByTerm(term);
				ActionContext.getContext().put("selfaccoutList", selfaccoutList);
				return "access";
				}
		else{
			return "toList";
		} 
	}
	public String success(){
		int i;
		double sum=0;
		int termid=termService.findMaxId();
		//accessgrade[0]=0.00;
		ATerm term=termService.findById(termid);
		List<ASelfAccessAccount> selfaccoutList=selfAccountService.findByTerm(term);
		for(i=0;i<selfaccoutList.size();i++)
		{	
			if(accessgrade[i]==1.00)
				sum=sum+selfaccoutList.get(i).getAPoint();
			else if(accessgrade[i]==2.00)
				sum=sum+selfaccoutList.get(i).getBPoint();
			else if(accessgrade[i]==3.00)
				sum=sum+selfaccoutList.get(i).getCPoint();
			else if(accessgrade[i]==4.00)
				sum=sum+selfaccoutList.get(i).getDPoint();
			
		}	
		ClassSelectCourse csc=classSelectCourseService.findById(model.getId());
		csc.setSelfAccess(sum);
		classSelectCourseService.update(csc);
		return "toList";
		}
	
	public String appSelfAccess() throws IOException {
		Map<String, Object> map=new HashMap<>();
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		List<ASelfAccessAccount> selfaccoutList=selfAccountService.findByTerm(term);
		ClassPropertyFilter.ListSelfAccountFilter(map,selfaccoutList);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;

	}
	public String appSaveSelfGrade() throws IOException{
		Map<String, Object> map=new HashMap<>();
		if(teaNum!=null){
		ATerm term=termService.findById(termService.findMaxId());
		Course course=courseService.findById(courseFind);
		ClassSelectCourse csc=classSelectCourseService.findByTeacherNumAndCourseandTerm(teaNum, course,term);
		csc.setSelfAccess(selfGrade);
		classSelectCourseService.update(csc);
		map.put("result", "success");
		}
		else 
		{
			map.put("result","noTeaNum");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
		
	}
}
