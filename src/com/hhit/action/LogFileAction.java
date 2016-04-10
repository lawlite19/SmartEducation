package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.LogFile;
import com.hhit.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LogFileAction extends BaseAction<LogFile>{

	/** 列表 */
	public String list() throws Exception{
		
		new QueryHelper(LogFile.class, "l").preparePageBean(logFileService, pageNum, pageSize);
		return "list";
	}
	/** 删除 */
	public String delete() throws Exception{
		logFileService.delete(model.getId());
		
		return "toList";
	}
	
}
