package com.hhit.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;
@SuppressWarnings("unchecked")
@Transactional
public class DaoSupportImpl<T> implements IDaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	public DaoSupportImpl(){
		//使用反射得到T的真实类型
		//获取new的对象的泛型的父类类型
		ParameterizedType pt= (ParameterizedType) this.getClass().getGenericSuperclass();
		//获取第一个类型的真实类型
		this.clazz=(Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("--->clazz"+clazz);
	}
	/**
	 * 获取当前Session对象
	 * protected子类中可以得到
	 * @return
	 */
	protected Session getSession(){
//		if(sessionFactory.getCurrentSession()==null)
//			return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Integer id) {
		Object obj=findById(id);
		if(null!=obj){
			getSession().delete(obj);
		}
		
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T findById(Integer id) {
		if(id==null){
			return null;
		}
		else
			return (T)getSession().get(clazz, id);
	}

	
	@Override
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM "+clazz.getSimpleName())//from+类名
				.list();
	}
	@Override
	public List<T> findByIds(Integer[] ids) {
		if(ids==null||ids.length==0){
			//返回一个空集合
			return Collections.EMPTY_LIST;
		}
		return getSession().createQuery(//
				"FROM "+clazz.getSimpleName()+"  WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

}
