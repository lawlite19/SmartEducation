package com.hhit.entity;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * 单例类，用户记录在线的用户
 * @author bob
 */
public class UserList {
	private static final UserList userList = new UserList();
	// //Vector是线程安全的
	// //只存id的情况--->32位机器上是32bit=4Byte,20000个用户占用20000*4/1024=78K,还有Vector
	// //存对象的情况--->假设User占200Byte,10000个用户占10000*200/1024=1953k=1.9M
	private Vector<User> v = new Vector<User>();

	private UserList() {
	}

	public static UserList getInstance() {
		return userList;
	}

	// 将用户登陆身份证保存到Vector中
	public void addUser(User user) throws Exception{
		try{
			if(user != null){
				if(v.indexOf(user)>=0)// 判断是否已经存在
					return;
				v.addElement(user);
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally{
		}
	}

	// 删除用户登录ID
	public void RemoveUser(User user) throws Exception{
		try{
			if (user != null){
				// 移除用户
				v.removeElement(user);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
		}
	}
	//判断Vector中是否存在已经登录的用户，使用Id判断
	public boolean IsExist(Integer userId) throws Exception {
		try{
			for (int i=0;i<v.size();i++) {
				// Integer 比较
				if(v.get(i).getId().equals(userId)){
					return true;
				}
			}
			return false;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	// 返回Vector枚举
	public Enumeration getUserList() {
		return v.elements();
	}

	// 返回迭代器
	public Iterator getUserListItera() {
		return v.iterator();
	}

	// 返回在线人数
	public int getUserCount() {
		return v.size();
	}
}
