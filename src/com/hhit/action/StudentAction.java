package com.hhit.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ChartDirector.Chart;
import ChartDirector.XYChart;

import com.hhit.base.BaseAction;
import com.hhit.entity.Chapter;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.Department;
import com.hhit.entity.Favorite;
import com.hhit.entity.PageBean;
import com.hhit.entity.QuestionErrorRecord;
import com.hhit.entity.Role;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.Student;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
import com.hhit.entity.VisitCourseRecord;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.DepartmentUtils;
import com.hhit.util.JsonUtil;
import com.hhit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private Integer departmentId;
	private Integer classId;
	
	private int viewType;// 0姓名；1账号
	private String inputTerm = "";// 输入的词条
	
	// ajax json返回
	private String result;
	//存储图片文件
	private File picture;
	private String pictureFileName;
	//
	private String oldPwd;
	private String newPwd;
	
	//我的课程
	private Integer courseId;
	private String teacherNum;
	
	//查询添加
	private String txtCourseName="";
	private String txtKnowledgeName="";
	private String txtQuestion="";
	
	/** 列表 */
	public String list() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		new QueryHelper(Student.class, "s")//
				.addCondition((departmentId != null), "s.department.id=?",departmentId)//
				.addCondition((viewType == 0) && (inputTerm.trim().length() > 0),"s.stuName LIKE ?", "%" + inputTerm + "%")//
				.addCondition((viewType == 1) && (inputTerm.trim().length() > 0),"s.stuNum LIKE ?", "%" + inputTerm + "%")//
				.preparePageBean(studentService, pageNum, pageSize);

		return "list";
	}
	/** 删除 */
	public String delete() throws Exception{
		studentService.delete(model.getId());
		 return "toList";
	}
	/** 批量删除 */
	public String bulkDelete() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		// 直接根据id删除
		userDetailsService.delete(model.getId());
		result = "ok";
		map.put("name", result);
		JsonUtil.toJson(ServletActionContext.getResponse(), map);

		return null;
	}
	
	/** 添加界面 */
	public String addUI() throws Exception{
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}
	/** 添加 */
	public String add() throws Exception {
		/** 保存用户相应信息 */
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// >> 设置所属部门
		model.setDepartment(departmentService.findById(departmentId));
		// 保存数据库
		studentService.save(model);
		// ** 保存用户登陆信息 */
		// >> 设置默认密码为账号（要使用MD5摘要）
		User userModel = new User();
		String md5Digest = DigestUtils.md5Hex(model.getStuNum());
		userModel.setPassword(md5Digest);
		userModel.setStudent(model);
		userModel.setUserNum(model.getStuNum());
		userModel.setUserType("学生");
		// 保存到数据库
		userService.save(userModel);

		return "toList";
	}
	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 准备回显的数据
		Student stuFind=studentService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(stuFind);
		
		// 准备数据, classList==>部门
		List<Class_> classList=classService.findByDept(stuFind.getDepartment());
		ActionContext.getContext().put("classList", classList);
		
		if (stuFind.getDepartment() != null) {
			departmentId = stuFind.getDepartment().getId();
		}
		if(stuFind.getClass_()!=null){
			classId=stuFind.getClass_().getId();
		}

		return "saveUI";
	}
	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		Student stuFind=studentService.findById(model.getId());

		// 2，设置要修改的属性
		stuFind.setBirthday(model.getBirthday());
		stuFind.setClass_(model.getClass_());
		stuFind.setGrade(model.getGrade());
		stuFind.setSex(model.getSex());
		stuFind.setStuName(model.getStuName());
		stuFind.setStuNum(model.getStuNum());
		// >> 设置所属部门和班级
		stuFind.setDepartment(departmentService.findById(departmentId));
		stuFind.setClass_(classService.findById(classId));
		
		// 3，更新到数据库
		studentService.update(stuFind);

		return "toList";
	}
	/** 初始化密码 */
	public String initPassword() throws Exception{
		Student stuFind=studentService.findById(model.getId());
		User userFind=userService.findByStudent(stuFind);
		userFind.setPassword(DigestUtils.md5Hex("123456"));
		
		//更新用户
		userService.update(userFind);
		return "toList";
	}
	/** 个人信息维护界面  */
	public String personalMaintainUI() throws Exception{
		//得到学生放到栈顶
		ActionContext.getContext().getValueStack().push(getCurrentUser().getStudent());
		
		return "personalMaintainUI";
	}
	/** 提交个人信息 */
	public String personalMaintain() throws Exception{
		//取出源对象
		Student stuFind=getCurrentUser().getStudent();
		if (picture != null) {
			// 获取当前应用程序物理路径
			String rootPath = ServletActionContext.getServletContext()
					.getRealPath("/");
			File tarDir = new File(rootPath + "/studentImgs");
			if (!tarDir.exists()) {
				tarDir.mkdirs();
			}
			//设置图片名为自己的学号
			//-->以.切分
			String [] strArray = pictureFileName.split("\\."); 
			SimpleDateFormat sdf =   new SimpleDateFormat( "-yyyy-MM-dd-HH-mm-ss" );
			String timeString=sdf.format(new Date());
			pictureFileName=getCurrentUser().getUserNum()+timeString+"."+strArray[strArray.length-1];
			File tarFile = new File(tarDir, pictureFileName);
			try {
				FileUtils.copyFile(picture, tarFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//设置图片
			stuFind.setPhoto(pictureFileName);
		}
		//设置其他属性
		stuFind.setBirthday(model.getBirthday());
		stuFind.setSex(model.getSex());
		stuFind.setStuName(model.getStuName());
		
		//更新
		studentService.update(stuFind);
		return "personalMaintain";
	}
	/** 我的收藏 */
	public String myFavorite() throws Exception{
		//准备信息--所有课程类型
//		ActionContext.getContext().put("professionTypeList", spiderProfessionTypeService.findAll());
		//准备信息--点击的课程类型名
//		if(professionId!=null){
//			ActionContext.getContext().getValueStack().push(spiderProfessionTypeService.findById(professionId));
//			ActionContext.getContext().put("professionId", professionId);
//		}
		//分页信息
		new QueryHelper(Favorite.class, "f")//
		.addCondition("f.student=?", getCurrentUser().getStudent())
		.preparePageBean(favoriteService, pageNum, 12);
		
		return "myFavorite";
	}
	/** 浏览记录 */
	public String visitRecord() throws Exception{
		int i,recordCount=0;
		//取出学生
		Student stuFind=getCurrentUser().getStudent();
		//取出访问的课程
		String hql="FROM VisitCourseRecord WHERE student=?";
		List<Object> parameters=new ArrayList<Object>();
		parameters.add(stuFind);
		PageBean pageBean=visitCourseRecordService.getPageBean(pageNum, 15, hql, parameters);
		
		List<VisitCourseRecord> courseList=pageBean.getRecordList();
		ActionContext.getContext().getValueStack().push(pageBean);
		
		//记录条数
		recordCount=courseList.size();
		//数据--显示访问记录数
		double[] count=new double[courseList.size()];
		for(i=0;i<recordCount;i++){
			count[i]=courseList.get(i).getCount();
		}
		//数据--要显示的标题
		String[] labels = new String[courseList.size()];
		for(i=0;i<recordCount;i++){
			labels[i]=courseList.get(i).getSpiderCourse().getName();
		}
		XYChart c = new XYChart(1000, 800);
		c.setDefaultFonts("simsun.ttc");
		c.addTitle("访问课程记录", "宋体", 18);
		c.setPlotArea(100, 40, 800, 300, c.linearGradientColor(100, 40, 60, 280, 0xeeeeff, 0x0000cc), -1,
		    0xffffff, 0xffffff);
		c.addBarLayer3(count).setBorderColor(Chart.Transparent, Chart.softLighting(Chart.Left));
		c.xAxis().setLabels(labels);
		c.xAxis().setLabelStyle("宋体", 10).setFontAngle(65);
		c.xAxis().setTickOffset(0.5);
		c.yAxis().setTitle("访问次数","宋体",12);
		c.xAxis().setWidth(2);
		c.yAxis().setWidth(2);
		String chart1URL = c.makeSession(ServletActionContext.getRequest(), "chart1");
		String imageMap1 = c.getHTMLImageMap("", "", "title='{xLabel}: 浏览{value}次'");

		ActionContext.getContext().put("chart1URL", chart1URL);
		ActionContext.getContext().put("imageMap1", imageMap1);
		return "visitRecord";
	}
	//我的课程
	public String myReceivePush() throws Exception{
		//数据--学生
		Student stuFind=getCurrentUser().getStudent();
		//数据--班级
		Class_ classFind=stuFind.getClass_();
		//数据--选课
		List<ClassSelectCourse> classSelectCourseList=classSelectCourseService.findByClass(classFind);
//		//班级对应课程
//		List<Course> courseList=new ArrayList<>();
//		for(int i=0;i<classSelectCourseList.size();i++){
//			courseList.add(classSelectCourseList.get(i).getCourse());
//		}
		ActionContext.getContext().put("classSelectCourseList", classSelectCourseList);
		if(courseId!=null){
			//当前时间
			Timestamp nowTime=new Timestamp(new Date().getTime());
			//根据账号找到老师
			Teacher teaFind=teacherService.findByTeacherNum(teacherNum);
			//课程
			Course courseFind=courseService.findById(courseId);
			//查找规定时间的教学进程
			List<TeachProcess> teachProcessList=teachProcessService.findByTeacherAndCourseAndTime(teaFind, courseFind,nowTime);
			//得到章节
			List<Chapter> chapterList=new ArrayList<>();
			for(int i=0;i<teachProcessList.size();i++){
				chapterList.add(teachProcessList.get(i).getChapter());
			}
			ActionContext.getContext().put("chapterList", chapterList);
		}
		return "myReceivePush";
	}
	/** 学生判断题错题记录 */
	public String myJudgementErrorPush() throws Exception{
		Student stuFind=getCurrentUser().getStudent();
		new QueryHelper(QuestionErrorRecord.class, "q")//
			.addCondition("q.stuNum=? AND q.singleChoice IS NULL",stuFind.getStuNum())//
			.addCondition(txtCourseName.trim()!="", "q.judgement.course.courseName LIKE ?", "%"+txtCourseName+"%")
			.addCondition(txtKnowledgeName.trim()!="", "q.judgement.knowledgeName LIKE ?", "%"+txtKnowledgeName+"%")
			.addCondition(txtQuestion.trim()!="", "q.judgement.question LIKE ?", "%"+txtQuestion+"%")
			.preparePageBean(questionErrorRecordService, pageNum, pageSize);
		return "myJudgementErrorPush";
	}
	/** 学生单选题错题记录 */
	public String mySingleErrorPush() throws Exception{
		Student stuFind=getCurrentUser().getStudent();
		new QueryHelper(QuestionErrorRecord.class, "q")//
			.addCondition("q.stuNum=? AND q.judgement IS NULL",stuFind.getStuNum())//
			.addCondition(txtCourseName.trim()!="", "q.singlceChoice.course.courseName LIKE ?", "%"+txtCourseName+"%")
			.addCondition(txtKnowledgeName.trim()!="", "q.singlceChoice.knowledgeName LIKE ?", "%"+txtKnowledgeName+"%")
			.addCondition(txtQuestion.trim()!="", "q.singlceChoice.question LIKE ?", "%"+txtQuestion+"%")
			.preparePageBean(questionErrorRecordService, pageNum, pageSize);
		return "mySingleErrorPush";
	}
	
//app接口	
//====================================
	
	//学生课程
	public String appStudentCourse() throws Exception{
		Map<String, Object> map=new HashMap<String,Object>();
		//根据学号找到学生
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			Class_ classFind=stuFind.getClass_();
			//根据班级查找选课
			List<ClassSelectCourse> classSelectCourseList = classSelectCourseService.findByClass(classFind);
			if(classSelectCourseList.size()<1){
				map.put("name", "noCourse");
			}
			else{
				//课程
				List<Course> courseList=new ArrayList<>();
				for(int i=0;i<classSelectCourseList.size();i++){
					courseList.add(classSelectCourseList.get(i).getCourse());
				}
				ClassPropertyFilter.ListCourseFilter(map, courseList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//学生的收藏
	public String appMyFavorite() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		//找到学生
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			//找到收藏
			List<Favorite> favoriteList=favoriteService.findByStudent(stuFind);
			if(favoriteList.size()<1){
				map.put("name", "noFavorite");
			}
			else{
				//填充课程
				List<SpiderCourse> courseList=new ArrayList<>();
				for(int i=0;i<favoriteList.size();i++){
					courseList.add(favoriteList.get(i).getSpiderCourse());
				}
				ClassPropertyFilter.ListSpiderCourseFilter(map, courseList);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//修改密码
	public String appModifyPwd() throws Exception{
		Map<String, Object> map=new HashMap<>();
		
		User userFind=userService.findByUserNum(model.getStuNum(),"学生");
		if(userFind==null){
			map.put("name", "noStudent");
		}
		else{
			String digest=DigestUtils.md5Hex(oldPwd);
			if(userFind.getPassword().equals(digest)){
				userFind.setPassword(DigestUtils.md5Hex(newPwd));
				userService.update(userFind);
				map.put("name", "success");
			}
			else{
				map.put("name", "oldPwdError");
			}
			
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//根据学号返回学生信息
	public String appStuInfo() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			ClassPropertyFilter.StudentFilter(map, stuFind);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//修改个人信息
	public String appStuModifyInfo() throws Exception{
		Map<String, String> map=new HashMap<>();
		//取出源对象
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			stuFind.setQqNum(model.getQqNum());
			stuFind.setSex(model.getSex());
			stuFind.setTelphone(model.getTelphone());
			//更新
			studentService.update(stuFind);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}
	//学生班级
	public String appStuClass() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			Class_ classFind=stuFind.getClass_();
			if(classFind==null){
				map.put("name", "noClass");
			}
			else{
				ClassPropertyFilter.ClassFilter(map, classFind);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//根据id返回学生信息
	public String appStuInfoById() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Student stuFind=studentService.findById(model.getId());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			ClassPropertyFilter.StudentFilter(map, stuFind);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	//获取学生的信息和班级
	public String appStuInfoAndClass() throws Exception{
		Map<String, Object> map=new HashMap<>();
		Student stuFind=studentService.findByStuNum(model.getStuNum());
		if(stuFind==null){
			map.put("name", "noStudent");
		}
		else{
			ClassPropertyFilter.StudentFilter(map, stuFind);
			Class_ classFind=stuFind.getClass_();
			if(classFind==null){
				map.put("name", "noClass");
			}
			else{
				ClassPropertyFilter.ClassFilter(map, classFind);
				map.put("name", "success");
			}
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		
		return null;
	}
	
//=============================	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public String getInputTerm() {
		return inputTerm;
	}
	public void setInputTerm(String inputTerm) {
		this.inputTerm = inputTerm;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public File getPicture() {
		return picture;
	}
	public void setPicture(File picture) {
		this.picture = picture;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public String getTxtCourseName() {
		return txtCourseName;
	}
	public void setTxtCourseName(String txtCourseName) {
		this.txtCourseName = txtCourseName;
	}
	public String getTxtKnowledgeName() {
		return txtKnowledgeName;
	}
	public void setTxtKnowledgeName(String txtKnowledgeName) {
		this.txtKnowledgeName = txtKnowledgeName;
	}
	public String getTxtQuestion() {
		return txtQuestion;
	}
	public void setTxtQuestion(String txtQuestion) {
		this.txtQuestion = txtQuestion;
	}
}
