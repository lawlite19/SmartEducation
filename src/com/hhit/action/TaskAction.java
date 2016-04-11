package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Task;
import com.hhit.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Task>{

	/** 列表 */
	public String list() throws Exception{
		
		new QueryHelper(Task.class, "t")//
		.preparePageBean(taskService, pageNum, pageSize);
		return "list";
	}
	
	/** 布置作业 */
	public String assignTask() throws Exception{
		
		
		return "toList";
	}
}
