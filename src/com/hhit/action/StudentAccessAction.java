package com.hhit.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.AStudentAccess;
import com.hhit.entity.AStudentAccessAccount;
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
public class StudentAccessAction extends BaseAction<AStudentAccess> {
	/**读取评价分数**/
	String[] accessgrade; 
	public String[] getAccessgrade() {
		return accessgrade;
	}
	public void setAccessgrade(String[] accessgrade) {
		this.accessgrade = accessgrade;
	}
	public String list(){
		int status[] = new int[40];
		User user=getCurrentUser();
		Student student=user.getStudent();
		ATerm term=termService.findById(termService.findMaxId());
		List<ClassSelectCourse> classSelectList=classSelectCourseService.findByClassAndTerm(student.getClass_(),term);
		for(int i=0;i<classSelectList.size();i++)
		{
			Teacher teacher=teacherService.findByTeaNum(classSelectList.get(i).getTeacherNum());
			if(null == stuAccessService.findByTeaAndStuAndTerm(teacher,student,term))
				status[i]=0;
			else status[i]=1;
				
		}
		ActionContext.getContext().put("classSelectList", classSelectList);
		ActionContext.getContext().put("status", status);
		new QueryHelper(ClassSelectCourse.class, "c").preparePageBean(classSelectCourseService, pageNum, pageSize);
		return "list";
	}
	public String access(){
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		
		Date nowdate=new Date();
		if(nowdate.after(term.getStartTime())&&nowdate.before(term.getEndTime()))
				{
				List<AStudentAccessAccount> stuaccoutList=stuAccountService.findByTerm(term);
				ActionContext.getContext().put("stuaccoutList", stuaccoutList);
				return "access";
				}
		else{
			return "toList";
		} 
	}
	public String success(){
		User user=getCurrentUser();
		Student student=user.getStudent();//得到评价的学生
		ClassSelectCourse csc=classSelectCourseService.findById(model.getId());
		Teacher teacher=teacherService.findByTeaNum(csc.getTeacherNum());//获得评价的教师
		int i,point;
		double sum=0;
		int termid=termService.findMaxId();
		ATerm term=termService.findById(termid);
		List<AStudentAccessAccount> stuaccoutList=stuAccountService.findByTerm(term);
		for(i=0;i<stuaccoutList.size();i++)
		{
			point=stuaccoutList.get(i).getPoint();
			sum=(Double.valueOf(accessgrade[i]).doubleValue()*10+50)/100*point+sum;
		}
		model.setATerm(term);
		model.setStudent(student);
		model.setTeacher(teacher);
		model.setStudentAccess(sum);
		model.setCourse(csc.getCourse());
		stuAccessService.save(model);
		return "toList";
	}
}
