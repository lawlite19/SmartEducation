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
	/** 学生修改密码 */
	public String stuModifyPasswordUI() throws Exception{
		return "stuModifyPasswordUI";
	}
	/** 老师修改密码 */
	public String teaModifyPasswordUI() throws Exception{
		return "teaModifyPasswordUI";
	}
	/** 学生信息--根据学号 */
	public String stuInfoUI() throws Exception{
		return "stuInfoUI";
	}
	/** 老师信息--根据工号 */
	public String teaInfoUI() throws Exception{
		return "teaInfoUI";
	}
	/** 学生修改个人信息 */
	public String stuModifyInfoUI() throws Exception{
		return "stuModifyInfoUI";
	}
	/** 老师修改个人信息 */
	public String teaModifyInfoUI() throws Exception{
		return "teaModifyInfoUI";
	}
	/** 老师授课的班级 */
	public String teaTeachClassUI() throws Exception{
		return "teaTeachClassUI";
	}
	

	
	
	
//实时测评接口
//=======================================
	
	/** 教学进程 */
	public String teachProcessUI() throws Exception{
		return "teachProcessUI";
	}
	/** 学生提问 */
	public String stuQuestionUI() throws Exception{
		return "stuQuestionUI";
	}
	/** 学生提问问题列表 */
	public String listStuQuestionUI() throws Exception{
		return "listStuQuestionUI";
	}
	/** 老师回答 */
	public String teaAnswerUI() throws Exception{
		return "teaAnswerUI";
	}
	/** 老师问题列表 */
	public String listTeaQuestionUI() throws Exception{
		return "listTeaQuestionUI";
	}
	/** 学生问题详情 */
	public String stuQuestionDetailsUI() throws Exception{
		return "stuQuestionDetailsUI";
	}
	/** 学生问题详情 */
	public String autoMakeQuestionUI() throws Exception{
		return "autoMakeQuestionUI";
	}
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
	/** 学生收藏的课程 */
	public String myFavoriteCourseUI() throws Exception{
		return "myFavoriteCourseUI";
	}
	/** 课程评分 */
	public String spiderCourseScoreUI() throws Exception{
		return "spiderCourseScoreUI";
	}
	/** 课程讨论 */
	public String spiderCourseDiscussUI() throws Exception{
		return "spiderCourseDiscussUI";
	}

//教学质量接口	
//==================================	
	/** 学生课程分数 */
	public String termUI() throws Exception{
		return "termUI";
	}
	/** 学生课程分数--学期 */
	public String stuTermCourseScoreUI() throws Exception{
		return "stuTermCourseScoreUI";
	}
	/** 学生课程分数--学年 */
	public String stuYearCourseScoreUI() throws Exception{
		return "stuYearCourseScoreUI";
	}
	
}
