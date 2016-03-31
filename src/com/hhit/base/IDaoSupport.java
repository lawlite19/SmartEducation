package com.hhit.base;
import java.util.List;

import com.hhit.entity.PageBean;

public interface IDaoSupport<T> {
	/**
	 * 保存一个对象
	 * @param entity
	 */
	void save(T entity);
	/**
	 * 按id删除一个对象
	 * @param id
	 */
	void delete(Integer id);
	/**
	 * 更新一个对象
	 * @param entity
	 */
	void update(T entity);
	/**
	 * 按id查询一个对象
	 * @param id
	 * @return
	 */
	T findById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<T> findAll();
	/**
	 * 按多个id查询
	 * @param ids
	 * @return
	 */
	List<T> findByIds(Integer[] ids);
	/**
	 * 
	 * @param hql   查询列表的语句
	 * @param parameters   参数列表，与hql语句的？对应
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters);
}
