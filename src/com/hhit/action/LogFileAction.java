package com.hhit.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.LogFile;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LogFileAction extends BaseAction<LogFile>{

	//json返回数据
	private String result;
	
	/** 列表 */
	public String list() throws Exception{
		
		
		new QueryHelper(LogFile.class, "l")//
		.addCondition((model.getUserNum()!=null), "l.userNum LIKE ?", "%"+model.getUserNum()+"%")
		.addOrderProperty("l.id", false)//false表示降序
		.preparePageBean(logFileService, pageNum, pageSize);
		return "list";
	}
	/** 删除 */
	public String delete() throws Exception{
		logFileService.delete(model.getId());
		
		return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		logFileService.delete(model.getId());
		result="ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
