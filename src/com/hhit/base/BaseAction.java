package com.hhit.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.hhit.entity.User;
import com.hhit.service.IChapterService;
import com.hhit.service.IClassSelectCourseService;
import com.hhit.service.IClassService;
import com.hhit.service.IComputerIPService;
import com.hhit.service.ICourseDiscussService;
import com.hhit.service.ICourseScoreService;
import com.hhit.service.ICourseService;
import com.hhit.service.IDataDictService;
import com.hhit.service.IDataTypeService;
import com.hhit.service.IDepartmentService;
import com.hhit.service.IFavoriteService;
import com.hhit.service.IJudgementService;
import com.hhit.service.ILogFileService;
import com.hhit.service.IPrivilegeService;
import com.hhit.service.IQQLoginInfoService;
import com.hhit.service.IRoleService;
import com.hhit.service.ISeatService;
import com.hhit.service.ISingleChoiceService;
import com.hhit.service.ISpiderChapterService;
import com.hhit.service.ISpiderCourseInfoService;
import com.hhit.service.ISpiderCourseService;
import com.hhit.service.ISpiderDocumentService;
import com.hhit.service.ISpiderProfessionService;
import com.hhit.service.ISpiderProfessionTypeService;
import com.hhit.service.IStuQuestionService;
import com.hhit.service.IStudentService;
import com.hhit.service.ISubmitTaskService;
import com.hhit.service.ITaskService;
import com.hhit.service.ITeaAnswerService;
import com.hhit.service.ITeachProcessService;
import com.hhit.service.ITeacherService;
import com.hhit.service.ITestPaperIDListService;
import com.hhit.service.ITestPaperService;
import com.hhit.service.ITestQuestionService;
import com.hhit.service.ITestRoomService;
import com.hhit.service.ITestingControlService;
import com.hhit.service.IUserDetailsService;
import com.hhit.service.IUserService;
import com.hhit.service.IVisitCourseRecordService;
import com.hhit.service.IWeiboInfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	// =============== ModelDriven的支持 ==================
	protected T model;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 写入响应数据 jsonString
	 * @param object
	 */
	public void writeJson(Object object) {
		try {
			String jsonString = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.write(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================
	@Resource
	protected IUserService userService;// 登录用户
	@Resource
	protected IStudentService studentService;//学生
	@Resource
	protected ITeacherService teacherService;//老师
	@Resource
	protected IRoleService roleService;// 角色
	@Resource 
	protected IQQLoginInfoService qqLoginService;//qq第三方登录
	@Resource
	protected IWeiboInfoService weiboInfoService;//微博第三方登录
	@Resource
	protected IDepartmentService departmentService;// 部门
	@Resource
	protected IPrivilegeService privilegeService;// 权限
	@Resource
	protected IDataDictService dataDictService;//数据字典
	@Resource
	protected IDataTypeService dataTypeService;//数据项类型
	@Resource
	protected ILogFileService logFileService; //日志
	@Resource
	protected ICourseService courseService;//课程
	@Resource
	protected IClassService classService;//班级
	@Resource
	protected ISpiderProfessionTypeService spiderProfessionTypeService;//爬虫学科类型
	@Resource
	protected ISpiderCourseInfoService spiderCourseInfoService;//爬虫课程信息
	@Resource
	protected IFavoriteService favoriteService;//学生收藏夹
	@Resource
	protected IVisitCourseRecordService visitCourseRecordService;//学生访问次数
	@Resource
	protected ITeachProcessService teachProcessService;//教学进程
	@Resource
	protected IJudgementService judgementService;//判断题
	@Resource
	protected ISingleChoiceService singleChoiceService;//单选题
	@Resource
	protected IChapterService chapterService;//章节
	@Resource
	protected IClassSelectCourseService classSelectCourseService;//班级选课
	@Resource
	protected ITestPaperService testPaperService;//测试卷
	@Resource
	protected ITestQuestionService testQuestionService;//判断题
	@Resource
	protected ICourseScoreService courseScoreService;//爬取课程评分
	@Resource
	protected ICourseDiscussService courseDiscussService;//爬取课程讨论
	@Resource
	protected IStuQuestionService stuQuestionService;//学生问题
	@Resource
	protected ITeaAnswerService teaAnswerService;//老师回答
	
	//--------------
	@Resource
	protected IUserDetailsService userDetailsService;// 用户详细信息
	@Resource
	protected ITaskService taskService;//老师布置作业
	@Resource
	protected ISubmitTaskService submitTaskService;//学生提交作业
	@Resource
	protected ISpiderProfessionService spiderProfessionService;//爬虫1专业
	@Resource
	protected ISpiderCourseService spiderCourseService;//爬虫2课程
	@Resource
	protected ISpiderChapterService spiderChapterService;//爬虫课程章节
	@Resource
	protected ISpiderDocumentService spiderDocumentService;//爬虫课程文档
	@Resource
	protected ITestingControlService testingControlService;//考试控制
	@Resource
	protected ITestRoomService testRoomService;//考试控制
	@Resource
	protected IComputerIPService computerIPService;//考场IP管理
	@Resource
	protected ISeatService seatService;//考试座位管理
	@Resource
	protected ITestPaperIDListService testPaperIDListService;//试卷管理

	/**
	 * 获取当前登录的用户
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}
	
	// ============== 分页用的参数 =============
	
	protected int pageNum=1;
	protected int pageSize=10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
