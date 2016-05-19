package com.hhit.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.PageBean;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.Student;
import com.hhit.entity.VisitCourseRecord;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;


@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class VisitCourseRecordAction extends BaseAction<VisitCourseRecord>{
	private String stuNum;

	
//app
//=================================
	//用户浏览记录---分页
	@SuppressWarnings("unchecked")
	public String appStuVisitRecord() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Student stuFind=studentService.findByStuNum(stuNum);
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			PageBean pageBean=new QueryHelper(VisitCourseRecord.class, "v")
							.addCondition("v.student=?", stuFind)//
							.prepareAppPageBean(visitCourseRecordService, pageNum, pageSize);
			if(pageBean==null){
				map.put("name", "noCourse");
			}
			else{
				map.put("count", pageBean.getRecordCount());
				map.put("currentPage", pageBean.getCurrentPage());
				List<VisitCourseRecord> recordList=pageBean.getRecordList();
				List<SpiderCourse> courseList=new ArrayList<>();
				for(int i=0;i<recordList.size();i++){
					courseList.add(recordList.get(i).getSpiderCourse());
				}
				ClassPropertyFilter.ListSpiderCourseFilter(map, courseList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	
	
	
	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
}
