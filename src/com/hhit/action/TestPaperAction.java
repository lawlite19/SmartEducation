package com.hhit.action;

import java.util.ArrayList;
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
import com.hhit.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TestPaperAction extends BaseAction<TestPaper>{

	private Integer judgementCount;
	private Integer singleChoiceCount;
	private Integer courseId;
	private Integer chapterId;
	private Integer classId;
	
//app
//=======================
	/** 自动生成测试提库 */
	public String appAutoMakeQuestion() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		
		int questionCount=judgementCount+singleChoiceCount;
		//设置测试题目类型
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
				//设置页大小为4
				pageSize=4;
				/***
				 * 判断题目数量是否足够
				 */
				//得到记录总数
				int recordCount=judgementService.findByCourseAndChapter(courseFind,chapterFind).intValue();
				if(recordCount<judgementCount-pageSize){//因为我忽略掉了最后一页
					map.put("name", "judgementNotEnough");
				}
				else{
					//得到记录总数
					int recordSingleCount=singleChoiceService.findByCourseAndChapter(courseFind,chapterFind).intValue();
					if(recordSingleCount<singleChoiceCount-pageSize){//因为我忽略掉了最后一页
						map.put("name", "singleChoiceNotEnough");
					}
					else{
						Class_ classFind= classService.findById(classId);
						//保存测试卷
						TestPaper testPaperModel=new TestPaper(model.getTestType(), questionCount, 0, 
								model.getEndTime(), model.getTeaNum(), classFind,
								courseFind, chapterFind);
						testPaperService.save(testPaperModel);
						/*
						 * 生成判断题
						 */
						
						//计算总页码-1-------->最后一页不要了
						int pageCount = (recordCount + pageSize - 1) / pageSize-1;
						int i=0,random,k=0;
						List<Integer> intList=new ArrayList<>();
						//产生随机页号
						while(i<(int)Math.ceil((double)judgementCount/(double)4)){
							random=(int)(Math.random()*pageCount);
							if(!intList.contains(random)){
								intList.add(random);
								i++;
							}
						}
						for(i=0;i<intList.size();i++){
							PageBean pageBean=new QueryHelper(Judgement.class, "j")//
								.addCondition("j.course=?", courseFind)//
								.addCondition("j.chapter=?", chapterFind)//
								.prepareAppPageBean(judgementService, intList.get(i), pageSize);
							for(int j=0;j<4;j++){
								if(k<judgementCount){
									TestQuestion questionModel=new TestQuestion((Judgement)pageBean.getRecordList().get(j), null, testPaperModel);
									testQuestionService.save(questionModel);
								}
								else{
									break;
								}
								k++;
							}
						}
						/**
						 * 生成单选题
						 */

						//计算总页码-1-------->最后一页不要了
						int pageSingleCount = (recordSingleCount + pageSize - 1) / pageSize-1;
						int j=0,randomSingle,m=0;
						List<Integer> intSingleList=new ArrayList<>();
						//产生随机页号
						while(j<(int)Math.ceil((double)singleChoiceCount/(double)4)){
							randomSingle=(int)(Math.random()*pageSingleCount);
							if(!intSingleList.contains(randomSingle)){
								intSingleList.add(randomSingle);
								j++;
							}
						}
						for(j=0;j<intList.size();j++){
							PageBean pageBean=new QueryHelper(SingleChoice.class, "s")//
								.addCondition("s.course=?", courseFind)//
								.addCondition("s.chapter=?", chapterFind)//
								.prepareAppPageBean(singleChoiceService, intList.get(j), pageSize);
							for(int n=0;n<4;n++){
								if(m<judgementCount){
									TestQuestion questionModel=new TestQuestion(null,(SingleChoice)pageBean.getRecordList().get(j), testPaperModel);
									testQuestionService.save(questionModel);
								}
								else{
									break;
								}
								m++;
							}
						}
						map.put("name", "success");
					}
				}
			}
		}
		
		JsonUtil.toJson(ServletActionContext.getResponse(),map);
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
				List<TestPaper> testPaperList=testPaperService.findByClassAndCourse(classFind,courseFind);
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
	
}
