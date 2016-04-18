package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.SpiderCourse;
import com.hhit.util.QueryHelper;

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
}
