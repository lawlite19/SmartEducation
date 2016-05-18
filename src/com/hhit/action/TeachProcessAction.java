package com.hhit.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;
import com.hhit.entity.Student;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeachProcessAction extends BaseAction<TeachProcess>{
	
	private Integer courseId;
	private Integer teachTypeId;
	private Integer chapterId;
	
	//app
	private String stuNum;
	private String teaNum;
	
	/** 列表 */
	public String list() throws Exception{
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
			//准备数据--第一门课程的教学进程
			List<Course> couseList=new ArrayList<>(teaFind.getCourses());
			Course courseFind=couseList.get(0);
			//准备数据--课程名
			ActionContext.getContext().getValueStack().push(courseFind);

			List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind,courseFind);
			ActionContext.getContext().put("teachProcessList", teachProcessList);
			courseId=courseFind.getId();
		}
		else{
			//准备数据--课程名
			ActionContext.getContext().getValueStack().push(courseService.findById(courseId));
			//准备数据--课程id
			ActionContext.getContext().put("courseId", courseId);
			//准备数据--对应课程的教学进程
			List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind,courseService.findById(courseId));
			ActionContext.getContext().put("teachProcessList", teachProcessList);
		}
		return "list";
	}
	
	
	/** 添加对应课程教学进程界面 */
	public String addUI() throws Exception{
		//准备数据--课程id
		ActionContext.getContext().put("courseId", courseId);
		//准备数据--教学方式--001
		DataType dataTypeFind=dataTypeService.findByNum("001");
		ActionContext.getContext().put("teachTypeList", dataTypeFind.getDataDicts());
		//准备数据--课程对应章节
		List<Chapter> chapterList=chapterService.findByCourse(courseService.findById(courseId));
		ActionContext.getContext().put("chapterList", chapterList);
		return "saveUI";
	}
	/** 添加对应课程教学进程 */
	public String add() throws Exception{
		//查找--课程
		Course courseFind=courseService.findById(courseId);
		//查找--教师
		Teacher teaFind=getCurrentUser().getTeacher();
		//查找--教学方式
		DataDict dataDictFind=dataDictService.findById(teachTypeId);
		//设置属性
		model.setCourse(courseFind);
		model.setTeacher(teaFind);
		model.setTeachType(dataDictFind);
		model.setChapter(chapterService.findById(chapterId));
		//保存
		teachProcessService.save(model);
		//将courseId携带过去
		ActionContext.getContext().put("courseId", courseId);
		return "toList";
	}
	/** 修改对应课程教学进程界面 */
	public String editUI() throws Exception{
		//准备数据--对象
		TeachProcess teachProcessFind=teachProcessService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(teachProcessFind);
		//准备数据--课程id
		ActionContext.getContext().put("courseId", courseId);
		//准备数据--教学方式--001
		DataType dataTypeFind=dataTypeService.findByNum("001");
		ActionContext.getContext().put("teachTypeList", dataTypeFind.getDataDicts());
		//准备数据--课程对应章节
		List<Chapter> chapterList=chapterService.findByCourse(courseService.findById(courseId));
		ActionContext.getContext().put("chapterList", chapterList);
		//回显数据--教学方式
		if(teachProcessFind.getTeachType()!=null)
			teachTypeId=teachProcessFind.getTeachType().getId();
		//回显数据
		if(teachProcessFind.getChapter()!=null){
			chapterId=teachProcessFind.getChapter().getId();
		}
		return "saveUI";
	}
	/** 修改对应课程教学进程*/
	public String edit() throws Exception{
		//查找源对象
		TeachProcess processFind=teachProcessService.findById(model.getId());
		//查找--教师
		Teacher teaFind=getCurrentUser().getTeacher();
		//查找--教学方式
		DataDict dataDictFind=dataDictService.findById(teachTypeId);
		//查找--章节
		Chapter chapterFind=chapterService.findById(chapterId);
		//设置属性
		processFind.setChapter(model.getChapter());
		processFind.setLessonContent(model.getLessonContent());
		processFind.setLessonCount(model.getLessonCount());
		processFind.setStartData(model.getStartData());
		processFind.setWeekCount(model.getWeekCount());
		processFind.setTeacher(teaFind);
		processFind.setTeachType(dataDictFind);
		processFind.setChapter(chapterFind);
		//更新
		teachProcessService.update(processFind);
		//将courseId携带过去
		ActionContext.getContext().put("courseId", courseId);
		return "toList";
	}
	/** 删除 */
	public String delete() throws Exception{
		teachProcessService.delete(model.getId());
		//带回courseId
		ActionContext.getContext().put("courseId", courseId);
		return "toList";
	}
	
	
//app	
//=============================================	
	//学生查看教学进程
	public String appCourseTeachProcess() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//找到课程
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			//找到学生
			Student stuFind=studentService.findByStuNum(stuNum);
			if(stuFind==null){
				map.put("name", "noStudent");
			}
			else{
				//找到班级
				Class_ classFind=stuFind.getClass_();
				//根据班级和课程找到班级选课
				ClassSelectCourse classCourseFind=classSelectCourseService.findByClassAndCourse(classFind,courseFind);
				//得到老师工号
				String teaNum=classCourseFind.getTeacherNum();
				//找到老师
				Teacher teaFind=teacherService.findByTeacherNum(teaNum);
				//根据工号和课程找到教学进程
				List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind, courseFind);
				ClassPropertyFilter.ListTeachPorcessFilter(map, teachProcessList);
			}
		}
		
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//老师查看教学进程
	public String appTeaCourseTeachProcess() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//找到课程
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			//找到老师
			Teacher teaFind=teacherService.findByTeacherNum(teaNum);
			if(teaFind==null){
				map.put("name", "noTeacher");
			}
			else{
				//根据老师和课程找到教学进程
				List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourse(teaFind, courseFind);
				ClassPropertyFilter.ListTeachPorcessFilter(map, teachProcessList);
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	//=========
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getTeachTypeId() {
		return teachTypeId;
	}
	public void setTeachTypeId(Integer teachTypeId) {
		this.teachTypeId = teachTypeId;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}


	public String getTeaNum() {
		return teaNum;
	}


	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}
	
}
