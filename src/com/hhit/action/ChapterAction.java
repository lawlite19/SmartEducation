package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ChapterAction extends BaseAction<Chapter>{

	//
	private Integer courseId;
	
	//ajax--根据课程Id查找章节
	public String findByCourseId() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//找到课程
		Course courseFind=courseService.findById(courseId);
		List<Chapter> chapterList=chapterService.findByCourse(courseFind);
		if(chapterList.size()>0){
			map.put("name", "success");
			ClassPropertyFilter.ListChapterFilter(map, chapterList);
		}
		else{
			map.put("name", "noChapter");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//ajax--根据父章节加载子章节
	public String findByParent() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Chapter parentFind=chapterService.findById(model.getId());
		List<Chapter> chapterList=chapterService.findByParent(parentFind);
		if(chapterList.size()<1){
			map.put("name", "noChapter");
		}
		else{
			ClassPropertyFilter.ListChapterFilter(map, chapterList);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

//app
//========================
	public String appCourseChapter() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//找到课程
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			List<Chapter> chapterList=chapterService.findByCourse(courseFind);
			if(chapterList.size()>0){
				ClassPropertyFilter.ListChapterFilter(map, chapterList);
				map.put("name", "success");
			}
			else{
				map.put("name", "noChapter");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
}
