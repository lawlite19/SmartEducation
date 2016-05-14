package com.hhit.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PushStrategyAction extends BaseAction<TeachProcess>{

	private Integer courseId;
	private Integer chapterId;
	private Integer dayId;
	
	/** 根据教学继承界面 */
	public String accordTeachProcess() throws Exception{
		Teacher teaFind=getCurrentUser().getTeacher();
		if(teaFind.getCourses().size()<1){
			return "noCourseError";
		}
		else{
			//准备数据--老师对应课程
			List<Course> couseList=new ArrayList<>(teaFind.getCourses());
			ActionContext.getContext().put("couseList", couseList);

		}
		if(courseId==null){
			//准备章节--空集合
			List<Chapter> chapterList=Collections.EMPTY_LIST;
			ActionContext.getContext().put("chapterList", chapterList);
		}
		else{
			Course courseFind=courseService.findById(courseId);
			//准备数据--课程名
			ActionContext.getContext().getValueStack().push(courseFind);
			//准备数据--课程id
			ActionContext.getContext().put("courseId", courseId);
			//准备数据--课程章节
			List<Chapter> chapterList=chapterService.findByCourse(courseFind);
			ActionContext.getContext().put("chapterList", chapterList);
			//准备数据--教学进程
			List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind, courseFind);
			ActionContext.getContext().put("teachProcessList", teachProcessList);
		}
		
		return "accordTeachProcess";
	}
	/** 保存推送的时间 */
	public String updateTeachPorcess() throws Exception{
		Chapter chapterFind=chapterService.findById(chapterId);
		Teacher teaFind=getCurrentUser().getTeacher();
		Course courseFind=courseService.findById(courseId);
		//根据章节、课程、老师
		List<TeachProcess> teachProcessList=teachProcessService.findByChapterAndTeaAndCourse(chapterFind,teaFind,courseFind);
		Timestamp startDate=teachProcessList.get(0).getStartData();
		Calendar now = Calendar.getInstance();  
		now.setTime(startDate);
	    now.set(Calendar.DATE, now.get(Calendar.DATE) - dayId);  
	    //真实时间
	    Timestamp realPushTime=new Timestamp(now.getTime().getTime());
	    for(int i=0;i<teachProcessList.size();i++){
	    	teachProcessList.get(i).setPushTime(realPushTime);
	    	teachProcessService.update(teachProcessList.get(i));
	    }
		
		//携带courseId
		ActionContext.getContext().put("courseId", courseId);
		return "toAccordTeachProcess";
	}
	
	
//===========================
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getDayId() {
		return dayId;
	}
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	
}
