package com.hhit.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.entity.PageBean;
import com.hhit.util.QueryHelper;
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
//		//增加
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
	@Deprecated
	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql,
			List<Object> parameters) {
		//查询数据列表
		Query listQuery=getSession().createQuery(hql);//创建查询对象
		if(parameters!=null)//设置参数
		{
			for(int i=0;i<parameters.size();i++)
			{
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		List list=listQuery.list();//执行查询
		//查询记录总数
		Query countQuery=getSession().createQuery("SELECT COUNT(*) "+hql);
		if(parameters!=null)//设置参数
		{
			for(int i=0;i<parameters.size();i++)
			{
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count=(Long) countQuery.uniqueResult();//执行查询
		
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public PageBean getPageBean(int pageNum, int pageSize,QueryHelper queryHelper) {
		System.out.println("-------> DaoSupportImpl.getPageBean( int pageNum, int pageSize, QueryHelper queryHelper )");
		// 参数列表
		List<Object> parameters = queryHelper.getParameters();
		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询
		// 查询总记录数量
		Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult(); // 执行查询
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
