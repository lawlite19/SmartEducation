package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.SpiderCourse;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpiderCourseAction extends BaseAction<SpiderCourse>{

	private Integer professionId;
	/** 列表--管理 */
	public String list() throws Exception{
		
		new QueryHelper(SpiderCourse.class, "s")//
		.preparePageBean(spiderCourseService, pageNum, pageSize);
		return "list";
	}
	/** 列表--访问 */
	public String show() throws Exception{
		//准备信息--所有课程类型
		ActionContext.getContext().put("professionTypeList", spiderProfessionTypeService.findAll());
		//准备分页信息
		new QueryHelper(SpiderCourse.class, "s")//
		.addCondition((professionId!=null), "s.professionType=?", spiderProfessionService.findById(professionId))
		.preparePageBean(spiderCourseService, pageNum, 12);
		return "show";
	}
	
	
	/** 删除 */
	public String delete() throws Exception{
		spiderCourseService.delete(model.getId());
		return "toList";
	}
	
	/** 查询课程的章节和文档 */
	public String listCourseInfo() throws Exception{
		//查询课程对象
		SpiderCourse spiderCourseFind=spiderCourseService.findById(model.getId());
		//放到栈顶
		ActionContext.getContext().put("spiderChapterList", spiderCourseFind.getSpiderChapters());
		ActionContext.getContext().put("spiderDocumentList", spiderCourseFind.getSpiderDocuemnts());
		
		return "listCourseInfo";
	}
	public Integer getProfessionId() {
		return professionId;
	}
	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}
	
}
