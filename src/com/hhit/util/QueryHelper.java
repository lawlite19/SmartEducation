package com.hhit.util;

import java.util.ArrayList;
import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.PageBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用于辅助拼接HQL语句
 */
public class QueryHelper {

	private String fromClause; // FROM子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 生成From子句
	 * @param alias 别名
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接Where子句
	 * @param condition  条件
	 * @param params   可变参数，因为添加中？数量不确定，eg: x.id between ? and ?
	 */
	public QueryHelper addCondition(String condition, Object... params) {
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		// 参数
		if (params != null) {
			for (Object p : params) {
				parameters.add(p);
			}
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接Where子句
	 * 
	 * @param append  Action层传递过来是否拼接的条件
	 */
	public QueryHelper addCondition(boolean append, String condition,
			Object... params) {
		if (append) {
			addCondition(condition, params);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName  参与排序的属性名
	 * @param asc  true表示升序，false表示降序
	 */
	public QueryHelper addOrderProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName
					+ (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接OrderBy子句
	 */
	public QueryHelper addOrderProperty(boolean append, String propertyName,
			boolean asc) {
		if (append) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 */
	public String getListQueryHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取HQL中的参数值列表
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * 查询分页信息，并放到值栈栈顶
	 * @param service   需要的service
	 */
	public void preparePageBean(IDaoSupport<?> service, int pageNum,int pageSize) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	/**
	 * 用于app端的分页查询
	 * @param service   需要的service
	 */
	public PageBean prepareAppPageBean(IDaoSupport<?> service, int pageNum,int pageSize) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		return pageBean;
	}

}
