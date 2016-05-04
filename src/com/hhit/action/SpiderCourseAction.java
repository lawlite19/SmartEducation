package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Favorite;
import com.hhit.entity.SpiderChapter;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderDocument;
import com.hhit.entity.Student;
import com.hhit.entity.VisitCourseRecord;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpiderCourseAction extends BaseAction<SpiderCourse>{

	//课程类型
	private Integer professionId;
	//课程
	private Integer courseId;
	//章节
	private Integer chapterId;
	//文档
	private Integer documentId;
	
	//json返回
	private String result;
	
	
	/** 列表--管理 */
	public String list() throws Exception{
		
		new QueryHelper(SpiderCourse.class, "s")//
		.addCondition(model.getName()!=null, "s.name LIKE ?", "%"+model.getName()+"%")
		.addCondition(model.getInfo()!=null, "s.info LIKE ?", "%"+model.getInfo()+"%")
		.preparePageBean(spiderCourseService, pageNum, pageSize);
		return "list";
	}
	/** 列表--访问 */
	public String show() throws Exception{
		//准备信息--所有课程类型
		ActionContext.getContext().put("professionTypeList", spiderProfessionTypeService.findAll());
		//准备信息--点击的课程类型名
		if(professionId!=null){
			ActionContext.getContext().getValueStack().push(spiderProfessionTypeService.findById(professionId));
			ActionContext.getContext().put("professionId", professionId);
		}
			
		//分页信息
		new QueryHelper(SpiderCourse.class, "s")//
		.addCondition((professionId!=null), "s.professionType=?", spiderProfessionTypeService.findById(professionId))
		.preparePageBean(spiderCourseService, pageNum, 12);
		return "show";
	}
	/** 课程信息--访问 */
	public String showCourseInfo() throws Exception{
		//找到课程对象
		SpiderCourse courseFind=spiderCourseService.findById(courseId);
		//课程Id--显示，用于收藏该课程
		ActionContext.getContext().put("courseId", courseFind.getId());
		//课程名--显示课程名
		ActionContext.getContext().put("courseName", courseFind.getName());
		//准备数据--是否收藏
		Favorite favorFind=favoriteService.findByStuAndCourse(getCurrentUser().getStudent(),courseFind);
		ActionContext.getContext().put("favorFind", favorFind);
		
		//准备数据--课程介绍
		ActionContext.getContext().getValueStack().push(spiderCourseInfoService.findByCourse(courseFind));
		//准备数据--课程参考教材
		List<SpiderDocument> documentFind= spiderDocumentService.findByCourse(courseFind);
		ActionContext.getContext().put("documentFind", documentFind);
		//根据课程查找章节
		List<SpiderChapter> chapterList= spiderChapterService.findByCourse(courseFind);
		ActionContext.getContext().put("chapterList", chapterList);

//		//增加课程访问次数 ====不好，交给ajax处理
//		Integer count= courseFind.getVisitCount();
//		synchronized (count) {
//			courseFind.setVisitCount(count++);
//		}
//		//增加学生访问次数
		
		return "showCourseInfo";
	}
	
	/** 删除--管理 */
	public String delete() throws Exception{
		spiderCourseService.delete(model.getId());
		return "toList";
	}
	
	/** 批量删除--管理 */
	public String bulkDelete() throws Exception{
		
		Map<String, String> map=new HashMap<String, String>();
		spiderCourseService.delete(model.getId());
		result="ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	
	/** 查询课程的章节和文档--管理 */
	public String listCourseInfo() throws Exception{
		//查询课程对象
		SpiderCourse spiderCourseFind=spiderCourseService.findById(model.getId());
		//放到栈顶
		ActionContext.getContext().put("spiderChapterList", spiderCourseFind.getSpiderChapters());
		ActionContext.getContext().put("spiderDocumentList", spiderCourseFind.getSpiderDocuemnts());
		setCourseId(model.getId());
		return "listCourseInfo";
	}
	/** 删除章节--管理  */
	public String deleteChapter() throws Exception{
		spiderChapterService.delete(chapterId);
		
		return "toCourseInfoList";
	}
	/** 删除文档--管理 */
	public String deleteDocument() throws Exception{
		spiderDocumentService.delete(documentId);
		return "toCourseInfoList";
	}
	
	/** 记录用户操作--ajax--synchronized关键字，多线程并发时只能一个一个访问 */
	public String recordCount() throws Exception{
		//课程次数,this代表当前代码块
		synchronized (this) {
			SpiderCourse courseFind=spiderCourseService.findById(courseId);
			Integer count=0;
			if(courseFind.getVisitCount()!=null)
				count=courseFind.getVisitCount();
			courseFind.setVisitCount(count++);
			spiderCourseService.update(courseFind);
		}
		//用户访问次数
		synchronized (this) {
			SpiderCourse courseFind=spiderCourseService.findById(courseId);
			Student stuFind=getCurrentUser().getStudent();
		    VisitCourseRecord visitModel=visitCourseRecordService.findByStuAndCourse(stuFind,courseFind);
			if(visitModel!=null){
				Integer count=visitModel.getCount();
				visitModel.setCount(count++);
				visitCourseRecordService.update(visitModel);
			}
			else{
				visitModel=new VisitCourseRecord(stuFind, courseFind,1);
				visitCourseRecordService.save(visitModel);
			}
			
			visitCourseRecordService.save(visitModel);
		}
		return null;
	}
	
	public Integer getProfessionId() {
		return professionId;
	}
	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public Integer getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	
}
