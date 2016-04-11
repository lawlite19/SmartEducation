package com.hhit.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.hhit.entity.User;
import com.hhit.service.IDataDictService;
import com.hhit.service.IDataTypeService;
import com.hhit.service.IDepartmentService;
import com.hhit.service.ILogFileService;
import com.hhit.service.IPrivilegeService;
import com.hhit.service.IQQLoginInfoService;
import com.hhit.service.IRoleService;
import com.hhit.service.ITaskService;
import com.hhit.service.IUserDetailsService;
import com.hhit.service.IUserService;
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
	protected IUserDetailsService userDetailsService;// 用户详细信息
	@Resource
	protected IRoleService roleService;// 角色
	@Resource
	protected IDepartmentService departmentService;// 部门
	@Resource
	protected IPrivilegeService privilegeService;// 权限
	@Resource 
	protected IQQLoginInfoService qqLoginService;//qq第三方登录
	@Resource
	protected IWeiboInfoService weiboInfoService;//微博第三方登录
	@Resource
	protected IDataDictService dataDictService;//数据字典
	@Resource
	protected IDataTypeService dataTypeService;//数据项类型
	@Resource
	protected ILogFileService logFileService; //日志
	@Resource
	protected ITaskService taskService;//老师布置作业

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
