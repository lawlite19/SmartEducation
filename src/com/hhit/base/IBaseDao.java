package com.hhit.base;

import java.util.List;

public interface IBaseDao<T> {
	/**
	 * 保存一个对象
	 * @param entity
	 */
	void save(T entity);
	/**
	 * 按id删除一个对象
	 * @param id
	 */
	void delete(Long id);
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
	T getById(Long id);
	
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
	List<T> findByIds(Long[] ids);
}
