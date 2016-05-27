package com.hhit.action;



import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ATeachingAccess;
import com.hhit.entity.ATeachingAccount;
import com.hhit.entity.ATerm;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeachAccessAction extends BaseAction<ATeachingAccess> {
	String usertype;
	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	/**读取评价分数**/
	Double[] accessgrade; 
	public Double[] getAccessgrade() {
		return accessgrade;
	}
	public void setAccessgrade(Double[] accessgrade) {
		this.accessgrade = accessgrade;
	}
	String stu="学生";
	String tea="老师";
	public String list(){
		User user=getCurrentUser();
		ATerm term=termService.findById(termService.findMaxId());
		if(user.getUserType().equals(stu))
		{
			
			Student student=user.getStudent();
			List<ClassSelectCourse> teachingList=classSelectCourseService.findByClassAndTerm(student.getClass_(),term);
			ActionContext.getContext().put("teachingList", teachingList);
			setUsertype(stu);
			ActionContext.getContext().put("usertype", usertype);
		}
		
		else if(user.getUserType().equals(tea))
		{
			Teacher teacher=user.getTeacher();
			List<ClassSelectCourse>  teachingList=classSelectCourseService.findByTeacherNumAndTerm(teacher.getTeaNum(),term);
			ActionContext.getContext().put("teachingList", teachingList);
			setUsertype(tea);
			ActionContext.getContext().put("usertype", usertype);
		}

		new QueryHelper(ClassSelectCourse.class, "c").preparePageBean(classSelectCourseService, pageNum, pageSize);
		return "list";
	}
	
	public String access(){
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		List<ATeachingAccount> teachaccoutList=teachAccountService.findByTerm(term);
		ActionContext.getContext().put("teachaccoutList", teachaccoutList);
		return "access";
		
		} 
	
	public String success(){
		int i;
		double sum=0;
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		User user=getCurrentUser();
		List<ATeachingAccount> teachaccoutList=teachAccountService.findByTerm(term);
		if(user.getUserType().equals(stu))
		{
			ClassSelectCourse csc=classSelectCourseService.findById(model.getId());
			Teacher teacher=teacherService.findByTeaNum(csc.getTeacherNum());//获得评价的教师
			for(i=0;i<teachaccoutList.size();i++)
			{	
				if(accessgrade[i]==1.00)
				{
					sum=sum+teachaccoutList.get(i).getAPoint();
					model.setGrade(teachaccoutList.get(i).getAPoint());
				}
				else if(accessgrade[i]==2.00)
				{
					sum=sum+teachaccoutList.get(i).getBPoint();
					model.setGrade(teachaccoutList.get(i).getBPoint());
				}
				else if(accessgrade[i]==3.00)
				{
					sum=sum+teachaccoutList.get(i).getCPoint();
					model.setGrade(teachaccoutList.get(i).getCPoint());
				}
				else if(accessgrade[i]==4.00)
				{
					sum=sum+teachaccoutList.get(i).getDPoint();
					model.setGrade(teachaccoutList.get(i).getDPoint());
				}
				model.setATeachingAccount(teachaccoutList.get(i));
				model.setATerm(term);
				model.setStudent(user.getStudent());
				model.setTeacher(teacher);
				teaAccessService.save(model);
			}	
			return "toList";
		}	
		else  
		{
			ClassSelectCourse csc=classSelectCourseService.findById(model.getId());
			for(i=0;i<teachaccoutList.size();i++)
			{	
				if(accessgrade[i]==1.00)
					sum=sum+teachaccoutList.get(i).getAPoint();
				else if(accessgrade[i]==2.00)
					sum=sum+teachaccoutList.get(i).getBPoint();
				else if(accessgrade[i]==3.00)
					sum=sum+teachaccoutList.get(i).getCPoint();
				else if(accessgrade[i]==4.00)
					sum=sum+teachaccoutList.get(i).getDPoint();
				
			}	
			csc.setTeachAccess(sum);
			classSelectCourseService.update(csc);
			return "toList";
		}
		
	}

}
