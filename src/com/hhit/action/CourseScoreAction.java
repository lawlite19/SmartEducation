package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.CourseScore;
import com.hhit.entity.SpiderCourse;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CourseScoreAction extends BaseAction<CourseScore>{
	
	private Integer spiderCourseId;
	
//app
//==========================	
	public String appCourseScore() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		
		SpiderCourse spiderCourseFind=spiderCourseService.findById(spiderCourseId);
		if(spiderCourseFind==null){
			map.put("name", "noCourse");
		}
		else if((model.getStuNum()==null)||(model.getStuNum().equals(""))){
			map.put("name", "noStuNum");
		}
		else{
			//检查是否已经评论过
			CourseScore courseScoreFind=courseScoreService.findByStuNumAndCourse(model.getStuNum(),spiderCourseFind);
			if(courseScoreFind==null){
				//设置未封装的属性
				model.setAddTime(new Timestamp(new Date().getTime()));
				model.setSpiderCourse(spiderCourseFind);
				courseScoreService.save(model);
				map.put("name", "success");
			}
			else{
				map.put("name", "alreadyScore");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	public Integer getSpiderCourseId() {
		return spiderCourseId;
	}

	public void setSpiderCourseId(Integer spiderCourseId) {
		this.spiderCourseId = spiderCourseId;
	}
	
}
