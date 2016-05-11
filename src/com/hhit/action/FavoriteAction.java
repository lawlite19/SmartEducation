package com.hhit.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Favorite;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.Student;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FavoriteAction extends BaseAction<Favorite>{
	
	private Integer courseId;
	private String stuNum;
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
	/** 删除--取消收藏 */
	public String delete() throws Exception{
		Map<String, String> map=new HashMap<String,String>();
		//查找课程对象，model.getId()为课程的Id
		SpiderCourse courseFind= spiderCourseService.findById(courseId);

		favoriteService.deleteByStuAndCourse(getCurrentUser().getStudent(),courseFind);
		result="canclefavorOK";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	
//app
//==================================
	/** 添加--学生收藏 */
	public String appAdd() throws Exception{
		
		Map<String, String> map=new HashMap<String, String>();
		if(courseId==null){
			map.put("name", "noCourseId");
		}
		else{
			if(stuNum==null){
				map.put("name", "noStuNum");
			}
			else{
				//查找课程对象，model.getId()为课程的Id
				SpiderCourse courseFind= spiderCourseService.findById(courseId);
				Student stuFind=studentService.findByStuNum(stuNum);
				if(courseFind!=null){
					if(stuFind!=null){
						//创建对象，并设置属性
						Favorite favorModel=new Favorite();
						favorModel.setImgurl(courseFind.getImgUrl());
						favorModel.setInfo(courseFind.getInfo());
						favorModel.setName(courseFind.getName());
						favorModel.setProfessionType(courseFind.getProfessionTypeName());
						favorModel.setUrl(favorModel.getUrl());
						//--设置对应学生
						favorModel.setStudent(stuFind);
						//--设置对应课程
						favorModel.setSpiderCourse(courseFind);
						//保存
						favoriteService.save(favorModel);
						
						map.put("name", "success");
					}
					else{
						map.put("name", "noStudent");
					}
				}
				else{
					map.put("name", "noCourse");
				}
			}
			
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	/** 删除--取消收藏 */
	public String appDelete() throws Exception{
		Map<String, String> map=new HashMap<String,String>();
		if(courseId==null){
			map.put("name", "noCourseId");
		}
		else{
			if(stuNum==null){
				map.put("name", "noStuNum");
			}
			else{
				//查找课程对象，model.getId()为课程的Id
				SpiderCourse courseFind= spiderCourseService.findById(courseId);
				Student stuFind=studentService.findByStuNum(stuNum);
				if(courseFind!=null){
					if(stuFind!=null){
						favoriteService.deleteByStuAndCourse(stuFind,courseFind);
						map.put("name", "success");
					}
					else{
						map.put("name", "noStudent");
					}
				}
				else{
					map.put("name", "noCourse");
				}
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
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

}
