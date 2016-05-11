package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 跳转jsp功能
 * @author bob
 */
@Controller
@Scope("prototype")
public class InterfaceAPIAction {

//公共接口界面
//======================================
	/** 登录  */
	public String loginUI() throws Exception{
		return "loginUI";
	}
	/** 学生课程 */
	public String studentCourseUI() throws Exception{
		return "studentCourseUI";
	}
	/** 课程章节 */
	public String courseChapterUI() throws Exception{
		return "courseChapterUI";
	}

	
	
	
//实时测评接口
//=======================================
	
	
	
	
	
	
//电子书包接口
//=======================================
	/** 专业类型 */
	public String courseTypeUI() throws Exception{
		return "courseTypeUI";
	}
	/** 课程 */
	public String spiderCourseUI() throws Exception{
		return "spiderCourseUI";
	}
	/** 课程信息 */
	public String spiderCourseInfoUI() throws Exception{
		return "spiderCourseInfoUI";
	}
	/** 收藏课程 */
	public String myFavoriteUI() throws Exception{
		return "myFavoriteUI";
	}
	/** 取消收藏课程 */
	public String cancleMyFavoriteUI() throws Exception{
		return "cancleMyFavoriteUI";
	}
}
