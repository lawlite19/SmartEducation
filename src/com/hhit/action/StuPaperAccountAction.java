package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.StuPaperAccount;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StuPaperAccountAction extends BaseAction<StuPaperAccount>{

	private Integer classId;
	private Integer courseId;

	
//app
//===========================
	public String appTeaCountByCourseClass() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			Class_ classFind=classService.findById(classId);
			if(classFind==null){
				map.put("name", "noClass");
			}
			else{
				List<StuPaperAccount> paperAccountList=stuPaperAccountService.findByCourseAndClass(courseFind,classFind);
				ClassPropertyFilter.ListStuPaperAccountFilter(map, paperAccountList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	
}
