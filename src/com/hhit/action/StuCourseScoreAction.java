package com.hhit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.DataDict;
import com.hhit.entity.StuCourseScore;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StuCourseScoreAction extends BaseAction<StuCourseScore>{

	private Integer termId;
	private String year;
	private String termName;
//app
//========================
	//学生学期成绩
	public String appTermScore() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
//		DataDict termFind=dataDictService.findById(termId);
		if(termName==null){
			map.put("name", "noTermName");
		}
		else{
			List<StuCourseScore> stuCourseScoreList=stuCourseScoreService.findByStuNumAndTerm(model.getStuNum(),termName);
			ClassPropertyFilter.ListStuCourseScoreFilter(map, stuCourseScoreList);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//学生学年成绩
	public String appYearScore() throws Exception{
		Map<String, Object> map=new HashMap<>();
		//找到学年对应的学期
		List<DataDict> termList=dataDictService.findByYear(year);
		
		List<StuCourseScore> stuCourseScoreList=stuCourseScoreService.findByStuNumAndTerms(model.getStuNum(),termList);
		if(stuCourseScoreList.size()<1){
			map.put("name", "noCourseScore");
		}
		else{
			ClassPropertyFilter.ListStuCourseScoreFilter(map, stuCourseScoreList);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//学生全部成绩
	public String appAllScore() throws Exception{
		Map<String, Object> map=new HashMap<>();
		List<StuCourseScore> courseScoreList=stuCourseScoreService.findByStuNum(model.getStuNum());
		if(courseScoreList.size()<1){
			map.put("name", "noCourseScore");
		}
		else{
			ClassPropertyFilter.ListStuCourseScoreFilter(map, courseScoreList);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	
	
//=================
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	
}
