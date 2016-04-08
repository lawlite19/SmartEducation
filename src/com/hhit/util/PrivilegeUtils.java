package com.hhit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hhit.entity.Privilege;

public class PrivilegeUtils {
	/**
	 * 遍历系统功能树
	 * @param topList
	 */
	public static List<Privilege> getAllPrivileges(List<Privilege> topList) {
		List<Privilege> list = new ArrayList<Privilege>();
		walkPrivilegeTreeList(topList, "┣", list);
		return list;
	}
	/**
	 * 遍历系统功能树，把遍历出的部门信息放到指定的集合中
	 */
	private static void walkPrivilegeTreeList(Collection<Privilege> topList, String prefix, List<Privilege> list) {
		for (Privilege top : topList) {
			// 顶点
			Privilege copy = new Privilege(); // 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setPrivilegeName(prefix+top.getPrivilegeName());
			list.add(copy); // 把副本添加到同一个集合中
			// 子树
			walkPrivilegeTreeList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}
}
