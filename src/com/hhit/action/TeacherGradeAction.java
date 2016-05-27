package com.hhit.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ATatics;
import com.hhit.entity.ATeacherGrade;
import com.hhit.entity.ATerm;
import com.hhit.entity.Class_;
import com.hhit.entity.Teacher;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeacherGradeAction extends BaseAction<ATeacherGrade> {
	
	private Integer termId;
	
	//查询输入条件
	private String inputTerm="";
	
	
	public String list(){
		Teacher teacher=getCurrentUser().getTeacher();
		ATerm term=termService.findById(termService.findMaxId());
		ATeacherGrade teagrade=teaGradeService.findByTeaAndTerm(teacher,term);
		if(teagrade==null)
		{
			double peergrade=peerAccessService.findgradeByTeaAndTerm(teacher,term);
			model.setPeergrade(peergrade);//获得并保存同行评价分数
			
			double selfgrade=classSelectCourseService.findgradeByTeacherNumAndTerm(teacher.getTeaNum(), term);
			double teachgrade=classSelectCourseService.findteagradeByTeacherNumAndTerm(teacher.getTeaNum(), term);
			model.setTeachgrade(teachgrade);//获得并保存教学要素评价分数
			model.setSelfgrade(selfgrade);//获得并保存自我评价分数
			
			double stugrade=stuAccessService.findgradeByTeaAndTerm(teacher, term);
			model.setStugrade(stugrade);
			
			ATatics tatics=taticsService.findByTerm(term);
			double allgrade=tatics.getPeerProportion()*peergrade+tatics.getSelfProportion()*selfgrade
							+tatics.getStudentProportion()*stugrade+tatics.getTeachingProportion()*teachgrade;
			model.setAllgrade(allgrade);//计算并保存总分
			
			model.setTeacher(teacher);
			model.setATerm(term);
			teaGradeService.save(model);
			ActionContext.getContext().put("teagrade", model);
			
		}
		else {
			ActionContext.getContext().put("teagrade", teagrade);
		}
		List<Object> classgrade=stuAccessService.findclassgradeByTeaAndTerm(teacher, term);
		ActionContext.getContext().put("classgrade", classgrade);
		return "list";
		
	}
	public String highchar() throws IOException{
		return "highchar";
	}
	
	public String charhigh() throws IOException{
		Teacher teacher=getCurrentUser().getTeacher();
		ATerm term=termService.findById(termService.findMaxId());
		ATeacherGrade teagrade=teaGradeService.findByTeaAndTerm(teacher,term);
		String teaname=teacher.getTeaName();
		String termname=term.getName();
		Map<String,Object> map =new HashMap<String,Object>();
		double[] data=new double[5];
		data[0]=teagrade.getAllgrade();
		data[1]=teagrade.getPeergrade();
		data[2]=teagrade.getSelfgrade();
		data[3]=teagrade.getStugrade();
		data[4]=teagrade.getTeachgrade();
//		String[] property={"Teacher","ATerm"};
		map.put("teagrade", data);
		map.put("teaname", teaname);		
		map.put("termname",termname);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	public String query(){
		Teacher teacher=getCurrentUser().getTeacher();
		List<ATeacherGrade> teachgradeList=teaGradeService.findByTea(teacher);
		ActionContext.getContext().put("teachgradeList", teachgradeList);
		return "query";
	}
	public String querychart(){
		return "querychart";
	}
	public String chartquery() throws IOException{
		Teacher teacher=getCurrentUser().getTeacher();
		String teaname=teacher.getTeaName();
		List<String> termname=termService.findTermName();
		List<Double> peergrade=teaGradeService.findPeerGrade(teacher);
		List<Double> selfgrade=teaGradeService.findSelfGrade(teacher);
		List<Double> teachgrade=teaGradeService.findTeachGrade(teacher);
		List<Double> stugrade=teaGradeService.findStuGrade(teacher);
		List<Double> allgrade=teaGradeService.findAllGrade(teacher);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("teaname", teaname);
		map.put("termname", termname);
		map.put("peergrade", peergrade);
		map.put("selfgrade", selfgrade);
		map.put("teachgrade", teachgrade);
		map.put("stugrade", stugrade);
		map.put("allgrade", allgrade);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	public String adminquery(){
		//List<ATeacherGrade> teachgradeList=teaGradeService.findAll();
		List<ATerm> termList=termService.findAll();
		ActionContext.getContext().put("termList", termList);
		
		new QueryHelper(ATeacherGrade.class, "t")//
		.addCondition((termId != null), "t.ATerm.id=?",termId)//
		.addCondition(inputTerm.trim().length() > 0, "t.Teacher.teaName LIKE ?", "%"+inputTerm+"%")
		.preparePageBean(teaGradeService, pageNum, pageSize);
		
		return "adminquery";
		
	}
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	public String getInputTerm() {
		return inputTerm;
	}
	public void setInputTerm(String inputTerm) {
		this.inputTerm = inputTerm;
	}
	
}