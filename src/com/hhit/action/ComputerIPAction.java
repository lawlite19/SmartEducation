package com.hhit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ComputerIP;
import com.hhit.entity.TestRoom;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ComputerIPAction extends BaseAction<ComputerIP>{
	private int TestRoomID;
	private int testroomid;
	private List<ComputerIP[]> computeriplist;
	private String id;
	private String ip;
	private String computerid;
	private String testroomname;
	public String computerIP(){
		return "computerip";
	}
	
	public String findcomputeriplist(){
		testroomid=TestRoomID;
		TestRoom rt=testRoomService.findRoom(testroomid);
		testroomname=rt.getTestRoomName();
		computeriplist=new ArrayList<ComputerIP[]>();
		List<ComputerIP> computers=new ArrayList<ComputerIP>();
		computers=computerIPService.findbytestroomid(TestRoomID);
		int size=computers.size();
		int i,j;
		for(i=0;i<size;){
			ComputerIP cip[]=new ComputerIP[4];
			for(j=0;j<4&&(i+j)<size;j++){
				cip[j]=computers.get(i+j);
			}
			i+=j;
			computeriplist.add(cip);
		}
		return "computerip";
	}
	
	public String findcomputeriplisttochange(){
		testroomid=TestRoomID;
		TestRoom rt=testRoomService.findRoom(testroomid);
		testroomname=rt.getTestRoomName();
		computeriplist=new ArrayList<ComputerIP[]>();
		List<ComputerIP> computers=new ArrayList<ComputerIP>();
		computers=computerIPService.findbytestroomid(TestRoomID);
		int size=computers.size();
		int i,j;
		for(i=0;i<size;){
			ComputerIP cip[]=new ComputerIP[4];
			for(j=0;j<4&&(i+j)<size;j++){
				cip[j]=computers.get(i+j);
			}
			i+=j;
			computeriplist.add(cip);
		}
		return "changeseat";
	}

	public String showedit(){
		return "showedit";
	}
	
	public void modify() throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		ip=ip.trim();
		ComputerIP computerip=computerIPService.findbyip(ip);
		if(computerip==null){
			computerip=computerIPService.findbyid(id);
			computerip.setIP(ip);
			computerIPService.update(computerip);
			map.put("name", "success");
		}
		else if(computerip.getID().equals(id)) 
			map.put("name", "nochange");
		else 
			map.put("name", "ipexist");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	
	public void delete() throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		computerIPService.deletebyid(id);
		map.put("name", "success");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	
	public void add() throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		ComputerIP cip=computerIPService.findbyip(ip);
		if(cip==null){//如果该IP地址未被使用
			cip=computerIPService.findbycomputerid(Integer.parseInt(computerid),testroomid);
			if(cip==null){//如果该电脑ID不存在
				ComputerIP tempmodel=new ComputerIP();
				tempmodel.setComputerId(Integer.parseInt(computerid));
				tempmodel.setIP(ip);
				tempmodel.setID(UUID.randomUUID().toString());//使用UUID生成唯一编号
				tempmodel.setTestRoomId(testroomid);
				computerIPService.save(tempmodel);
				map.put("name", "success");
			}
			else
			map.put("name", "computeridexist");
		}
		else
			map.put("name", "ipexist");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	
	public int getTestRoomID() {
		return TestRoomID;
	}

	public void setTestRoomID(int testRoomID) {
		TestRoomID = testRoomID;
	}

	public List<ComputerIP[]> getComputeriplist() {
		return computeriplist;
	}

	public void setComputeriplist(List<ComputerIP[]> computeriplist) {
		this.computeriplist = computeriplist;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getTestroomid() {
		return testroomid;
	}

	public void setTestroomid(int testroomid) {
		this.testroomid = testroomid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComputerid() {
		return computerid;
	}

	public void setComputerid(String computerid) {
		this.computerid = computerid;
	}

	public String getTestroomname() {
		return testroomname;
	}

	public void setTestroomname(String testroomname) {
		this.testroomname = testroomname;
	}
	
	
	
}
