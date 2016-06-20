package com.hhit.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.Judgement;
import com.hhit.entity.PageBean;
import com.hhit.entity.SingleChoice;
import com.hhit.entity.TestPaper;
import com.hhit.entity.TestQuestion;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TestPaperAction extends BaseAction<TestPaper>{

	private Integer judgementCount;
	private Integer singleChoiceCount;
	private Integer courseId;
	private Integer chapterId;
	private Integer[] chapterIds;
	private Integer classId;
	private String startTimeString;
	private String endTimeString;
	
//app
//=======================
	/** 自动生成测试提库 */
	public String appAutoMakeQuestion() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//测试题目总数
		int questionCount=judgementCount+singleChoiceCount;
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			Chapter chapterFind=chapterService.findById(chapterId);
			if(chapterFind==null){
				map.put("name", "noChapter");
			}
			else{
				List<Chapter> chapterList=chapterService.findByIds(chapterIds);
				if(chapterList.size()<1){
					map.put("name", "noChildrenChapter");
				}
				else{
					/***
					 * 判断题目数量是否足够
					 */
					//得到记录总数
					int recordJudgementCount=judgementService.findByCourseAndChapterList(courseFind,chapterList).intValue();
					if(recordJudgementCount<judgementCount){
						map.put("name", "judgementNotEnough");
					}
					else{
						//得到记录总数
						int recordSingleCount=singleChoiceService.findByCourseAndChapterList(courseFind,chapterList).intValue();
						if(recordSingleCount<singleChoiceCount){//因为我忽略掉了最后一页
							map.put("name", "singleChoiceNotEnough");
						}
						else{
							//保存测试卷
							TestPaper testPaperModel=new TestPaper(model.getTestType(), questionCount, 0,Timestamp.valueOf(startTimeString),//转换时间字符串为Timestamp,
									Timestamp.valueOf(endTimeString), model.getTeaNum(), classService.findById(classId),
									courseFind, chapterFind);
							testPaperService.save(testPaperModel);
							/*
							 * 生成判断题
							 */
							
							//设置页大小为选择的判断题数量
							pageSize=judgementCount;
							
							//计算总页码--------->最后一页不要了
							int pageCount = (recordJudgementCount + pageSize - 1) / pageSize-1;
							int random;
							//产生随机页号
							random=(int)(Math.random()*pageCount)+1;
							//得到这一页的题目---自己在service里写的
							PageBean pageBean =judgementService.getPageBeanByInList(courseFind,random,pageSize,recordJudgementCount,chapterList);
							//保存题目
							for(int i=0;i<judgementCount;i++){
								TestQuestion questionModel=new TestQuestion((Judgement)pageBean.getRecordList().get(i), null, testPaperModel);
								testQuestionService.save(questionModel);
							}
							
							/**
							 * 生成单选题
							 */

							pageSize=singleChoiceCount;
							//计算总页码--------->最后一页不要了
							int pageSingleCount = (recordSingleCount + pageSize - 1) / pageSize-1;
							int randomSingle;
							//产生随机页号
							randomSingle=(int)(Math.random()*pageSingleCount)+1;
							//得到这一页的题目---自己在service里写的
							PageBean pageBeanSingle =singleChoiceService.getPageBeanByInList(courseFind,randomSingle,pageSize,recordSingleCount,chapterList);
							//保存题目
							for(int i=0;i<singleChoiceCount;i++){
								TestQuestion questionModel=new TestQuestion(null,(SingleChoice)pageBeanSingle.getRecordList().get(i), testPaperModel);
								testQuestionService.save(questionModel);
							}
							map.put("name", "success");
						}
					}
				}
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(),map);
		return null;
	}
	//学生测试卷---根据班级查找--在提交的时间段内的
	public String appStuTestPaper() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Class_ classFind=classService.findById(classId);
		if(classFind==null){
			map.put("name", "noClass");
		}
		else{
			Timestamp nowTime=new Timestamp(new Date().getTime());
			List<TestPaper> testPaperList=testPaperService.findByClass(classFind,nowTime);
			if(testPaperList.size()<1){
				map.put("name", "noTestPaper");
			}
			else{
				ClassPropertyFilter.ListTestPaperFilter(map, testPaperList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//测试卷题目
	public String appTestPaperQuestion() throws Exception{
		Map<String, Object> map=new HashMap<>();
		TestPaper testPaperFind=testPaperService.findById(model.getId());
		if(testPaperFind==null){
			map.put("name", "noTestPaper");
		}
		else{
			List<TestQuestion> testQuestionList=new ArrayList<TestQuestion>(testPaperFind.getTestQuestions());
			if(testQuestionList.size()<1){
				map.put("name", "noQuestion");
			}
			else{
				List<Judgement> judgementList=new ArrayList<>();
				List<SingleChoice> singleChoiceList=new ArrayList<>();
				for(int i=0;i<testQuestionList.size();i++){
					if(testQuestionList.get(i).getJudgement()!=null){
						judgementList.add(testQuestionList.get(i).getJudgement());
					}
					if(testQuestionList.get(i).getSingleChoice()!=null){
						singleChoiceList.add(testQuestionList.get(i).getSingleChoice());
					}
				}
				ClassPropertyFilter.ListJudgementFilter(map, judgementList);
				ClassPropertyFilter.ListSingleChoiceFilter(map, singleChoiceList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//老师课程的测试卷
	public String appTeaCourseTestPaper() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			List<TestPaper> testPaperList=testPaperService.findByTeaNumAndCourse(model.getTeaNum(),courseFind);
			if(testPaperList.size()<1){
				map.put("name", "noTestPaper");
			}
			else{
				ClassPropertyFilter.ListTestPaperFilter(map, testPaperList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//老师的课程已经发布试卷的班级
	public String appTeaClassOfPaper() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Course courseFind=courseService.findById(courseId);
		if(courseFind==null){
			map.put("name", "noCourse");
		}
		else{
			List<TestPaper> testPaperList=testPaperService.findByTeaNumAndCourse(model.getTeaNum(), courseFind);
			if(testPaperList.size()<1){
				map.put("name", "noClass");
			}
			else{
				List<Class_> classList=new ArrayList<>();
				for(int i=0;i<testPaperList.size();i++){
					if(!classList.contains(testPaperList.get(i).getClass_())){
						classList.add(testPaperList.get(i).getClass_());
					}
				}
				ClassPropertyFilter.ListClassFilter(map, classList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//学生课程测试卷
	public String appStuCourseTestPaper() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Class_ classFind=classService.findById(classId);
		if(classFind==null){
			map.put("name", "noClass");
		}
		else{
			Course courseFind=courseService.findById(courseId);
			if(courseFind==null){
				map.put("name", "noCourse");
			}
			else{
				Timestamp nowTime=new Timestamp(new Date().getTime());
				List<TestPaper> testPaperList=testPaperService.findByClassAndCourse(classFind, courseFind,nowTime);
				if(testPaperList.size()<1){
					map.put("name", "noTestPaper");
				}
				else{
					ClassPropertyFilter.ListTestPaperFilter(map, testPaperList);
					map.put("name", "success");
				}
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//老师测试卷--按id倒序
	public String appTeaTestPaper() throws Exception{
		Map<String, Object> map = new HashMap<>();
		List<TestPaper> testPaperList = testPaperService.findByTeaNum(model.getTeaNum());
		if (testPaperList.size() < 1) {
			map.put("name", "noTestPaper");
		} else {
			ClassPropertyFilter.ListTestPaperFilter(map, testPaperList);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	public Integer getJudgementCount() {
		return judgementCount;
	}

	public void setJudgementCount(Integer judgementCount) {
		this.judgementCount = judgementCount;
	}

	public Integer getSingleChoiceCount() {
		return singleChoiceCount;
	}

	public void setSingleChoiceCount(Integer singleChoiceCount) {
		this.singleChoiceCount = singleChoiceCount;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer[] getChapterIds() {
		return chapterIds;
	}
	public void setChapterIds(Integer[] chapterIds) {
		this.chapterIds = chapterIds;
	}
	public String getStartTimeString() {
		return startTimeString;
	}
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	
}
