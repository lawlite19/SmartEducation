package com.hhit.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.hhit.entity.User;
import com.hhit.service.IClassService;
import com.hhit.service.ICourseService;
import com.hhit.service.IDataDictService;
import com.hhit.service.IDataTypeService;
import com.hhit.service.IDepartmentService;
import com.hhit.service.IFavoriteService;
import com.hhit.service.ILogFileService;
import com.hhit.service.IPrivilegeService;
import com.hhit.service.IQQLoginInfoService;
import com.hhit.service.IRoleService;
import com.hhit.service.ISpiderChapterService;
import com.hhit.service.ISpiderCourseInfoService;
import com.hhit.service.ISpiderCourseService;
import com.hhit.service.ISpiderDocumentService;
import com.hhit.service.ISpiderProfessionService;
import com.hhit.service.ISpiderProfessionTypeService;
import com.hhit.service.IStuAccountService;
import com.hhit.service.IStudentService;
import com.hhit.service.ISubmitTaskService;
import com.hhit.service.ITaskService;
import com.hhit.service.ITeacherService;
import com.hhit.service.ITermService;
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
	
	
	//--------------
	@Resource
	protected IUserDetailsService userDetailsService;// 用户详细信息
	@Resource 
	protected IQQLoginInfoService qqLoginService;//qq第三方登录
	@Resource
	protected IWeiboInfoService weiboInfoService;//微博第三方登录
	

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
	protected ITermService termService;//评价基础信息
	@Resource
	protected IStuAccountService stuAccountService;//学生评价指标信息
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
