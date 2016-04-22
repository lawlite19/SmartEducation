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

	/** 列表 */
	public String list() throws Exception{
		
		new QueryHelper(SpiderCourse.class, "s")//
		.preparePageBean(spiderCourseService, pageNum, pageSize);
		return "list";
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
	
}
