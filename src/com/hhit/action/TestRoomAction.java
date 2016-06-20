package com.hhit.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.TestRoom;
import com.hhit.util.ClassPropertyFilter;
import com.hhit.util.JsonUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TestRoomAction extends BaseAction<TestRoom>{
	private int location;
	private String testroomname;
	//private String TeacherPwd;
	
	public String first(){
		return "setRoomPassword";
	}

	public String setpassword(){
		try{
			TestRoom rt=testRoomService.findRoom(model.getTestRoomID());
			rt.setTeacherPwd(model.getTeacherPwd());
			testRoomService.update(rt);
			//将考场编号放入session，方便以后调用
			ServletActionContext.getRequest().getSession().setAttribute("testroomid", model.getTestRoomID());
		}catch(Exception e){
			addFieldError("setPasswordInfo", "监考密码设置出错!");
			return "setRoomPassword";
		}
		addFieldError("setPasswordInfo", "监考密码设置成功!");
		return "setRoomPasswordsuccess";
	}
	
	public void list() throws IOException{
		String where="";
		Map<String, Object> map=new HashMap<String, Object>();
		switch(location){//拼接条件
		case 1:
			where="NOT LIKE '东港%' AND RoomPlace NOT LIKE '通灌%'";break;
		case 2:
			where="LIKE '通灌%'";break;
		case 3:
			where="LIKE '东港%'";break;
		}
		if(!where.isEmpty()){
		List<TestRoom> trs=testRoomService.findRooms(where);//查找机房
		//设置需要过滤的属性
		String[] Property={"firstIP","endtIP","ftpIP","endtIP","ftpName","ftpPwd","teacherPwd","setDir"};
		ClassPropertyFilter.ListTestRoomFilter(map, trs, Property);//将剩下的属性放入map中
		map.put("name", "success");//成功则添加名称为success
		}
		else{
			map.put("name", "null");//失败则添加名称为null
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);//将机房信息封装成Json，并发送到前台
	}
	

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getTestroomname() {
		return testroomname;
	}

	public void setTestroomname(String testroomname) {
		this.testroomname = testroomname;
	}

	/*public String getTeacherPwd() {
		return TeacherPwd;
	}

	public void setTeacherPwd(String teacherPwd) {
		TeacherPwd = teacherPwd;
	}*/

	
	
}
