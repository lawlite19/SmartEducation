package com.hhit.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Favorite;
import com.hhit.entity.SpiderCourse;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FavoriteAction extends BaseAction<Favorite>{
	
	private Integer courseId;
	//json返回
	private String result;
	
	/** 添加--学生收藏 */
	public String add() throws Exception{
		
		Map<String, String> map=new HashMap<String, String>();
		
		//查找课程对象，model.getId()为课程的Id
		SpiderCourse courseFind= spiderCourseService.findById(courseId);
		
		//创建对象，并设置属性
		Favorite favorModel=new Favorite();
		favorModel.setImgurl(courseFind.getImgUrl());
		favorModel.setInfo(courseFind.getInfo());
		favorModel.setName(courseFind.getName());
		favorModel.setProfessionType(courseFind.getProfessionTypeName());
		favorModel.setUrl(favorModel.getUrl());
		//--设置对应学生
		favorModel.setStudent(getCurrentUser().getStudent());
		//--设置对应课程
		favorModel.setSpiderCourse(courseFind);
		//保存
		favoriteService.save(favorModel);
		
		result="favorOK";
		map.put("name", result);
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
