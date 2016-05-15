package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings({ "unchecked", "serial" })
@Controller
@Scope("prototype")
public class DataDictAction extends BaseAction<DataDict> {

	private Integer dataTypeId;

	// ajax json返回
	private String result;

	/** 列表 */
	public String list() throws Exception {
		// 准备数据
		List<DataType> dataTypeList = (List<DataType>) ActionContext
				.getContext().getApplication().get("dataTypeList");
		ActionContext.getContext().put("dataTypeList", dataTypeList);
		// 分页信息
		new QueryHelper(DataDict.class, "d")//
				.addCondition(dataTypeId != null, "d.dataType=?",dataTypeService.findById(dataTypeId))//
				.preparePageBean(dataDictService, pageNum, pageSize);
		return "list";
	}

	/** 跳转添加界面 */
	public String addUI() throws Exception {
		// 准备数据
		List<DataType> dataTypeList = (List<DataType>) ActionContext
				.getContext().getApplication().get("dataTypeList");
		ActionContext.getContext().put("dataTypeList", dataTypeList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 设置所属数据项类型
		model.setDataType(dataTypeService.findById(dataTypeId));
		// 保存到数据库
		dataDictService.save(model);
		return "toList";
	}

	/** 跳转修改界面 */
	public String editUI() throws Exception {
		// 准备数据
		List<DataType> dataTypeList = (List<DataType>) ActionContext
				.getContext().getApplication().get("dataTypeList");
		ActionContext.getContext().put("dataTypeList", dataTypeList);
		//回显
		DataDict dataDictFind=dataDictService.findById(model.getId());
		dataTypeId=dataDictFind.getDataType().getId();
		ActionContext.getContext().getValueStack().push(dataDictFind);
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		//取出源对象
		DataDict dataDictFind=dataDictService.findById(model.getId());
		// 设置属性
		dataDictFind.setDataType(dataTypeService.findById(dataTypeId));
		dataDictFind.setDictNum(model.getDictNum());
		dataDictFind.setDictName(model.getDictName());
		dataDictFind.setDescription(model.getDescription());
		
		// 更新数据库
		dataDictService.update(dataDictFind);

		return "toList";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 删除数据
		dataDictService.delete(model.getId());
		return "toList";
	}

	/** 批量删除 */
	public String bulkDelete() throws Exception {
		// 定义一个map存储要返回的数据
		Map<String, Object> map = new HashMap<String, Object>();
		dataDictService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		// 转为json格式
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	/** 检查编号 */
	public String checkDictNum() throws Exception{
		Map<String, String> map=new HashMap<>();
		DataDict dataDictFind=dataDictService.findByDictNum(model.getDictNum());
		if(dataDictFind!=null){
			map.put("name", "error");
			JsonUtil.toJson(ServletActionContext.getResponse(), map);
		}
		return null;
	}
	

	public Integer getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
