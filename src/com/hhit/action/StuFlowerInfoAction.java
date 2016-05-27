package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.PageBean;
import com.hhit.entity.StuFlowerInfo;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StuFlowerInfoAction extends BaseAction<StuFlowerInfo>{
	

//app接口
//========================
	@SuppressWarnings("unchecked")
	public String appStuFlowerInfo() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		
		PageBean pageBean=new QueryHelper(StuFlowerInfo.class, "s")//
							.addCondition("s.stuNum=?", model.getStuNum())//
							.addOrderProperty("s.id", false)//
							.prepareAppPageBean(stuFlowerInfoService, pageNum, pageSize);
		List<StuFlowerInfo> stuFlowerList=pageBean.getRecordList();
		if(stuFlowerList.size()<1){
			map.put("name", "noFlower");
		}
		else{
			ClassPropertyFilter.ListStuFlowerInfoFilter(map, stuFlowerList);
			map.put("count", pageBean.getRecordCount());
			map.put("currentPage", pageBean.getCurrentPage());
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
}
