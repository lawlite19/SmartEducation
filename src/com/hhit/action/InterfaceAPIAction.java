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
	/** 学生的班级 */
	public String stuClassInfoUI() throws Exception{
		return "stuClassInfoUI";
	}
	/** 老师的课程 */
	public String teaCourseUI() throws Exception{
		return "teaCourseUI";
	}
	/** 老师课程的班级 */
	public String teaCourseClassUI() throws Exception{
		return "teaCourseClassUI";
	}
	/** 根据id获取学生信息 */
	public String stuInfoByIdUI() throws Exception{
		return "stuInfoByIdUI";
	}
	/** 根据id获取老师信息 */
	public String teaInfoByIdUI() throws Exception{
		return "teaInfoByIdUI";
	}
	/** 章节的子章节 */
	public String childrenChapterUI() throws Exception{
		return "childrenChapterUI";
	}
	/** 获取学生的信息和班级 */
	public String stuInfoAndClassUI() throws Exception{
		return "stuInfoAndClassUI";
	}
	/** 获取老师信息和部门和角色 */
	public String teaInfoAndDeptAndRoleUI() throws Exception{
		return "teaInfoAndDeptAndRoleUI";
	}
	/** 当前学期 */
	public String currentTermUI() throws Exception{
		return "currentTermUI";
	}
	/** 学生的系 */
	public String stuDeptLevel3UI() throws Exception{
		return "stuDeptLevel3UI";
	}
	/** 系中的老师 */
	public String deptLevel3TeaUI() throws Exception{
		return "deptLevel3TeaUI";
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
	/** 学生课程测试卷 */
	public String getStuTestPaperUI() throws Exception{
		return "getStuTestPaperUI";
	}
	/** 测试卷题目 */
	public String testPaperQuestionUI() throws Exception{
		return "testPaperQuestionUI";
	}
	/** 老师课程测试卷 */
	public String teaCourseTestPaperUI() throws Exception{
		return "teaCourseTestPaperUI";
	}
	/** 答题时错题记录 */
	public String stuQuestionErrorRecordUI() throws Exception{
		return "stuQuestionErrorRecordUI";
	}
	/** 测试卷分数记录 */
	public String stuTestScoreUI() throws Exception{
		return "stuTestScoreUI";
	}
	/** 检测是否做过测试卷了 */
	public String checkStuAlreadyTestUI() throws Exception{
		return "checkStuAlreadyTestUI";
	}
	/** 老师查看测试卷 */
	public String teaSeeTestPaperInfoUI() throws Exception{
		return "teaSeeTestPaperInfoUI";
	}
	/** 老师查看教学进程 */
	public String teaTeachProcessUI() throws Exception{
		return "teaTeachProcessUI";
	}
	/** 老师对应课程已发布作业的班级 */
	public String teaClassOfPaperUI() throws Exception{
		return "teaClassOfPaperUI";
	}
	/** 学生课程测试卷 */
	public String stuCourseTestPaperUI() throws Exception{
		return "stuCourseTestPaperUI";
	}
	/** 老师的测试卷 */
	public String teaTestPaperUI() throws Exception{
		return "teaTestPaperUI";
	}
	/** 老师的测试卷 */
	public String teaAccountOfClassUI() throws Exception{
		return "teaAccountOfClassUI";
	}
	/** 老师的课程的系级对应的班级 */
	public String teaCourseDeptUI() throws Exception{
		return "teaCourseDeptUI";
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
	/** 课程讨论列表 */
	public String listSpiderCourseDiscussUI() throws Exception{
		return "listSpiderCourseDiscussUI";
	}
	/** 学生浏览记录 */
	public String stuVisitCourseRecordUI() throws Exception{
		return "stuVisitCourseRecordUI";
	}
	/** 课程搜索 */
	public String stuSearchCourseUI() throws Exception{
		return "stuSearchCourseUI";
	}
	/** 课程搜索 */
	public String listSpiderCourseScoreUI() throws Exception{
		return "listSpiderCourseScoreUI";
	}
	/** 学生学期课程 */
	public String myTermCourseUI() throws Exception{
		return "myTermCourseUI";
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
	/** 学生课程分数--全部 */
	public String stuAllCourseScoreUI() throws Exception{
		return "stuAllCourseScoreUI";
	}
	/** 老师给学生送花*/
	public String teaSendFlowerUI() throws Exception{
		return "teaSendFlowerUI";
	}
	/** 老师取消送花*/
	public String teaCancelFlowerUI() throws Exception{
		return "teaCancelFlowerUI";
	}
	/** 学生得到花的记录*/
	public String stuFlowerRecordUI() throws Exception{
		return "stuFlowerRecordUI";
	}
	
}
