package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeachElementAction extends BaseAction<DataDict> {
	public String list(){
		DataType datatype=dataTypeService.findById(4);
		List<DataDict> teachList =dataDictService.findByType(datatype);
		ActionContext.getContext().put("teachList", teachList);
		return  "list";
	}
	
	public String addUI() throws Exception {
		return "saveUI";
	}
	
	public String add(){
		model.setDataType(dataTypeService.findById(4));
		dataDictService.save(model);
		return "toList";
	}
	public String delete() throws Exception {
		// 删除数据
		dataDictService.delete(model.getId());
		return "toList";
	}
	public String edit() throws Exception {
		// 设置属性
		model.setDictNum(model.getDictNum());
		model.setDictName(model.getDictName());
		model.setDescription(model.getDescription());
		// 更新数据库
		dataDictService.update(model);

		return "toList";
	}
	
	public String editUI() throws Exception {
		//回显
		DataDict dataDictFind=dataDictService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(dataDictFind);
		
		return "saveUI";
	}

}
