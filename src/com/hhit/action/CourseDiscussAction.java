package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.CourseDiscuss;
import com.hhit.entity.CourseScore;
import com.hhit.entity.SpiderCourse;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CourseDiscussAction extends BaseAction<CourseDiscuss>{
	private Integer spiderCourseId;
	
	//app
	//==========================	
		public String appCourseDiscuss() throws Exception{
			Map<String, Object> map=new HashMap<String, Object>();
			
			SpiderCourse spiderCourseFind=spiderCourseService.findById(spiderCourseId);
			if(spiderCourseFind==null){
				map.put("name", "noCourse");
			}
			else if((model.getStuNum()==null)||(model.getStuNum().equals(""))){
				map.put("name", "noStuNum");
			}
			else{
				//设置未封装属性
				model.setAddTime(new Timestamp(new Date().getTime()));
				model.setSpiderCourse(spiderCourseFind);
				courseDiscussService.save(model);
				map.put("name", "success");
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
