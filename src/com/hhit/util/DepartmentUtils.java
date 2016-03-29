package com.hhit.util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hhit.entity.Department;
public class DepartmentUtils {

	/**
	 * 遍历部门树，把所有的部门遍历出来放到同一个集合中返回，并且其中所有部门的名称都修改了，以表示层次。
	 * 
	 * @param topList
	 * @return
	 */
	
	
	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList, "┣", list);
		return list;
	}

	/**
	 * 遍历部门树，把遍历出的部门信息放到指定的集合中
	 * 
	 * @param topList
	 */
	private static void walkDepartmentTreeList(Collection<Department> topList, String prefix, List<Department> list) {
		for (Department top : topList) {
			// 顶点
			Department copy = new Department(); // 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setDeptName(prefix + top.getDeptName());
			list.add(copy); // 把副本添加到同一个集合中
			// 子树
			walkDepartmentTreeList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}
}
