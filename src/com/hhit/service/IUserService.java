package com.hhit.service;


import com.hhit.base.IDaoSupport;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
import com.hhit.entity.UserDetails;

public interface IUserService extends IDaoSupport<User> {

	//根据信息查找用户
	User findUserByNumAndPwd(String userNum, String password,String userType);
	//根据UserDetailsId查询User
	User findByDetailsId(UserDetails userDetails);
	//根据学生查找用户
	User findByStudent(Student stuFind);
	//根据老师查找用户
	User findByTeacher(Teacher teaFind);
}
