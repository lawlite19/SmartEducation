package com.hhit.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.hhit.entity.Department;

/**
 * 说明：不能使用多层循环的方式，因为需要能支持任意层。
 */
public class TreeViewPractice {
	/**
	 * 练习二：打印所有顶层部门及其子孙部门的信息（名称），用不同的缩进表示层次（使用全角空格）。<br>
	 * 子部门的名称前比上级部门多一个空格，最顶层部门的名字前没有空格。 提示：假设有一个打印部门集合中所有部门信息的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * ┣aaa
	 *    ┣bbb
	 *    ┣bbb
	 *       ┣ccc
	 * ┣aaa
	 *    ┣bbb
	 */
//	@Test
//	public void printAllDepts() {
//		List<Department> topList = findTopLevelDepartmentList();
//		showTreeList(topList, "┣");
//	}
//
//	// 显示树
//	private void showTreeList(Collection<Department> topList, String prefix) {
//		for (Department top : topList) {
//			// 顶点
//			System.out.println(prefix + top.getDeptName());
//			// 子树
//			showTreeList(top.getChildren(), "    " + prefix);
//		}
//	}

	public List<Department> printAllDepts(List<Department> topList) {
		List<Department> list=new ArrayList<Department>();
		showTreeList(topList, "┣",list);
		return list;
	}
	//显示树
	private void showTreeList(Collection<Department> topList, String prefix,List<Department> list) {
		for (Department top : topList) {
			// 顶点
			top.setDeptName(prefix+top.getDeptName());
			list.add(top);
			// 子树
			showTreeList(top.getChildren(), "    " + prefix,list);
		}
	}
	
	
	/**
	 * 结构如下：
	 * ┣学校
	 *    ┣计算机学院
	 *       ┣软件工程
	 *       ┣网络工程
	 * 	  ┣电子学院
	 *       ┣电气工程
	 * @return 所有最顶层的部门的列表
	 */
	public static List<Department> findTopLevelDepartmentList() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Integer(11));
		dept_1_1.setDeptName("学校");

		Department dept_1_2 = new Department();
		dept_1_2.setId(new Integer(12));
		dept_1_2.setDeptName("计算机学院");

		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Integer(121));
		dept_1_2_1.setDeptName("业务一部");

		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Integer(122));
		dept_1_2_2.setDeptName("业务二部");

		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setChildren(children_0);

		// ================================

		Department dept_1 = new Department();
		dept_1.setId(new Integer(1));
		dept_1.setDeptName("市场部");

		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setChildren(children_1);

		// ---

		Department dept_2_1 = new Department();
		dept_2_1.setId(new Integer(21));
		dept_2_1.setDeptName("开发一部");

		Department dept_2_2 = new Department();
		dept_2_2.setId((new Integer(22)));
		dept_2_2.setDeptName("开发二部");

		Department dept_2 = new Department();
		dept_2.setId(new Integer(2));
		dept_2.setDeptName("开发部");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setChildren(children_2);

		// ---

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
	}

}
