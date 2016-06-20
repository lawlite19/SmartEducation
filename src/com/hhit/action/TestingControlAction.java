package com.hhit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.ComputerIP;
import com.hhit.entity.Seat;
import com.hhit.entity.Student;
import com.hhit.entity.TestPaperIDList;
import com.hhit.entity.TestRoom;
import com.hhit.entity.TestingControl;
import com.hhit.tempmodel.TesterTempModel;
import com.hhit.util.JsonUtil;
import com.hhit.util.xlsHandler;


@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TestingControlAction extends BaseAction<TestingControl>{
	private File file;
	private String fileFileName;
	private List<TesterTempModel[]> testers;
	private int arrangemodel;
	private int delaytime;
	private String testpaperid;
	private List<Seat> seats;
	private String ip;
	private String stunum;
	private String testroomname;
	private String computerid;
	/**导入数据*/
	public String exceltosql(){
		File tarDir=null;
		if (file != null) {
			// 获取当前应用程序物理路径
			String rootPath = ServletActionContext.getServletContext()
					.getRealPath("/");
			tarDir = new File(rootPath + "/exceltosqlUpload");
			if (!tarDir.exists()) {
				tarDir.mkdirs();
			}
			//修改文件名字为当天日期
			//以.切分
			String [] strArray=fileFileName.split("\\.");
			fileFileName="latestexceltosql"+"."+strArray[strArray.length-1];
			File tarFile = new File(tarDir, fileFileName);
			try {
				FileUtils.copyFile(file, tarFile);
			} catch (IOException e) {
				e.printStackTrace();
				addFieldError("exceltosqlInfo", "文件上传出错！");
				return "exceltosql";
			}
			try {
				//读取TestingControl表的追后一个Rnumber
				int firstno=testingControlService.findlastRNumber();
	        	InputStream in = new FileInputStream(tarFile);
	        	Workbook workbook = xlsHandler.create(in);
				Sheet sheet = workbook.getSheetAt(0);//读取默认第一个工作表sheet
				int firstRowNum = 1;
				int lastRowNum = sheet.getLastRowNum();//获取sheet中最后一行行号
				List<TestingControl> tcs=new ArrayList<TestingControl>();
				for (int i = firstRowNum; i <=lastRowNum; i++) {
					firstno++;
					Row r=sheet.getRow(i);
					int j=1;
					if(r.getCell(j).getStringCellValue().trim().equals("")){
						break;
					}
					TestingControl tc=new TestingControl();
					tc.setRnumber(firstno);
					tc.setTesterID(r.getCell(j++).getStringCellValue().trim());//默认最左边编号也算一列 所以这里得j++
	                tc.setTesterName(r.getCell(j++).getStringCellValue().trim());
	                tc.setClassName(r.getCell(j++).getStringCellValue().trim());
	                tc.setTestName(r.getCell(j++).getStringCellValue().trim());
	                j++;
	                tc.setTestRoomName(r.getCell(j++).getStringCellValue().trim());
	                tc.setLoginFlag(r.getCell(j++).getStringCellValue().trim());
	                tc.setTestRoomID(r.getCell(j++).getStringCellValue().trim());
	                tc.setTestList(r.getCell(j++).getStringCellValue().trim());
	                j++;
	                tc.setTeaname(r.getCell(j++).getStringCellValue().trim());
	                tc.setTeaid(tc.getTeaname());
	                tc.setTestPaperID(testpaperid.trim());
	                tcs.add(tc);
				}
				testingControlService.save(tcs);
				workbook.close();
				tarFile.deleteOnExit();//使用完之后删除
			}
		  catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         addFieldError("exceltosqlInfo", "文件读取出错！");
			return "exceltosql";
	     }
		addFieldError("exceltosqlInfo", "文件导入成功！");
		return "exceltosql";
		}
		else{
			addFieldError("exceltosqlInfo", "文件上传失败！");
			return "exceltosql";
		}
	}
	
	/**跳转到导入数据页面*/
	public String first(){
		return "exceltosql";
	}
	
	/**跳转到座位安排页面*/
	public String arrangeseats(){
		String arrange="";
		try{
			arrange=ServletActionContext.getRequest().getSession().getAttribute("arrange").toString();
			testers=(List<TesterTempModel[]>)ServletActionContext.getRequest().getSession().getAttribute("testers");
		}catch(Exception e){}
		if(!arrange.equals("")&&testers!=null)
			updatestate();
		return "arrangeseats";
	}
	
	/**安排座位*/
	public String arrange(){
		String testroomid;
		try{
			testroomid=ServletActionContext.getRequest().getSession().
					getAttribute("testroomid").toString();
		}catch(Exception e){
			return "setRoomPassword";
		}
		if(testroomid.equals("")){//判断是否按照流程，先设置了考试密码，如果没有，则返回设置考试密码页面
			return "setRoomPassword";
		}
		String arrange="";
		try{
			testers=(List<TesterTempModel[]>)ServletActionContext.getRequest()
					.getSession().getAttribute("testers");
			arrange=ServletActionContext.getRequest().getSession()
					.getAttribute("arrange").toString();
		}catch(Exception e){}
		if(arrange.equals("")||testers==null){//如果没有安排座位,则安排座位
			seats=new ArrayList<Seat>();
			switch(arrangemodel){
			case 1:
				order_arrange(testroomid);
				break;
			case 2:
				random_arrange(testroomid);
				break;
			}
			//座位安排完之后在session中添加arrange属性，座位下次进入页面时判断是否已经安排座位的依据
			ServletActionContext.getRequest().getSession()
			.setAttribute("arrange", "order");
			//将testers放入session，方便下次更新，不需要重新遍历list来对应IP，提高更新速度
			ServletActionContext.getRequest().getSession()
			.setAttribute("testers", testers);
			ServletActionContext.getRequest().getSession()
			.setAttribute("seats", seats);
			updatestate();
		}
		else {//如果安排了座位，就从seat表中取出数据，并更新值
			updatestate();
		}
		return "arrangeseats";
	}
	
	/**更新考生的考试状态*/
	@SuppressWarnings("unchecked")
	private void updatestate(){
		String state;
		//从session中获取考生信息
		testers=(List<TesterTempModel[]>)ServletActionContext.getRequest().getSession().getAttribute("testers");
		for (TesterTempModel[] testerTempModels : testers) {
			for(int i=0;i<testerTempModels.length&&testerTempModels[i]!=null;i++){
				//根据考生学号访问数据库来获取考生的最新考试状态
				String isupload=testingControlService.getuploadstate(testerTempModels[i].getStunum());
				if(!"交卷成功!!!".equals(isupload))
					state=testingControlService.findstatebytesternum(testerTempModels[i].getStunum());
				else
					state="交卷";
				testerTempModels[i].setState(state);
			}
		}
	}
	
	/**顺序排座*/
	private void order_arrange(String testroomid){
		TestRoom testroom=testRoomService.findRoom(Integer.parseInt(testroomid));
		int i,j;
		testers=new ArrayList<TesterTempModel[]>();
		List<TesterTempModel> testerlist=new ArrayList<TesterTempModel>();
		List<TestingControl> testingcontrols=testingControlService.findTestingControl(Integer.parseInt(testroomid));
		List<ComputerIP> computerips=computerIPService.findbytestroomid(Integer.parseInt(testroomid));
		ServletActionContext.getRequest().getSession().setAttribute("testpaperid", testingcontrols.get(0).getTestPaperID());
		int comsize=computerips.size();
		int testsize=testingcontrols.size();
		if(comsize/2>=testsize)//如果电脑的数量大于2倍考生的数量，则一个隔一个坐
			for(i=0;i<testsize;i++)
			{
				TesterTempModel tester=new TesterTempModel();
				tester.setComputernum(testroom.getTestRoomName()+"-"+String.valueOf(computerips.get(i*2).getComputerId()));
				tester.setName(testingcontrols.get(i).getTesterName());
				Student s = studentService.findByStuNum(testingcontrols.get(i).getTesterID());
				if(s==null)
					tester.setPhoto("");
				else
					tester.setPhoto(s.getPhoto());
				tester.setState(testingcontrols.get(i).getLoginFlag());
				tester.setStunum(testingcontrols.get(i).getTesterID());
				testerlist.add(tester);
				Seat seat=new Seat();
				seat.setFlag(0);
				seat.setIp(computerips.get(i*2).getIP());
				seat.setStuNum(testingcontrols.get(i).getTesterID());
				seat.setId(UUID.randomUUID().toString());//使用UUID生成一个唯一的ID
				seats.add(seat);
				seatService.save(seat);
			}
		else{//如果电脑的数量小于2倍考生的数量，则连续坐
			for(i=0;i<testsize;i++)
			{
				TesterTempModel tester=new TesterTempModel();
				tester.setComputernum(testroom.getTestRoomName()+"-"+String.valueOf(computerips.get(i).getComputerId()));
				tester.setName(testingcontrols.get(i).getTesterName());
				Student s = studentService.findByStuNum(testingcontrols.get(i).getTesterID());
				if(s==null)
					tester.setPhoto("");
				else
					tester.setPhoto(s.getPhoto());
				tester.setState(testingcontrols.get(i).getLoginFlag());
				tester.setStunum(testingcontrols.get(i).getTesterID());
				testerlist.add(tester);
				Seat seat=new Seat();
				seat.setFlag(0);
				seat.setIp(computerips.get(i).getIP());
				seat.setStuNum(testingcontrols.get(i).getTesterID());
				seat.setId(UUID.randomUUID().toString());
				seats.add(seat);
				seatService.save(seat);
			}
		}
		int testersize=testerlist.size();
		for(i=0;i<testersize;){
			TesterTempModel tt[]=new TesterTempModel[3];
			for(j=0;j<3&&i+j<testersize;j++){
				tt[j]=testerlist.get(i+j);
			}
			i+=j;
			testers.add(tt);
		}
	}
	/**随机排座*/
	private void random_arrange(String testroomid){
		TestRoom testroom=testRoomService.findRoom(Integer.parseInt(testroomid));
		int i,j;
		testers=new ArrayList<TesterTempModel[]>();
		List<TesterTempModel> testerlist=new ArrayList<TesterTempModel>();
		List<TestingControl> testingcontrols=testingControlService.findTestingControl(Integer.parseInt(testroomid));
		List<ComputerIP> computerips=computerIPService.findbytestroomid(Integer.parseInt(testroomid));
		ServletActionContext.getRequest().getSession().setAttribute("testpaperid", testingcontrols.get(0).getTestPaperID());
		int comsize=computerips.size();
		int testsize=testingcontrols.size();
		if(comsize/2>testsize)
			for(i=0;i<testsize;i++)//控制循环次数，循环次数等于testingcontrols长度
			{
				j=(int)(Math.random()*testingcontrols.size());//每次得到一个0~剩余长度的随机数
				TesterTempModel tester=new TesterTempModel();
				tester.setComputernum(testroom.getTestRoomName()+"-"+String.valueOf(computerips.get(i*2).getComputerId()));
				tester.setName(testingcontrols.get(j).getTesterName());
				Student s = studentService.findByStuNum(testingcontrols.get(j).getTesterID());
				if(s==null)
					tester.setPhoto("");
				else
					tester.setPhoto(s.getPhoto());
				tester.setState(testingcontrols.get(j).getLoginFlag());
				tester.setStunum(testingcontrols.get(j).getTesterID());
				testerlist.add(tester);
				Seat seat=new Seat();
				seat.setFlag(0);
				seat.setIp(computerips.get(i*2).getIP());
				seat.setStuNum(testingcontrols.get(j).getTesterID());
				seat.setId(UUID.randomUUID().toString());
				seats.add(seat);
				seatService.save(seat);
				testingcontrols.remove(j);//将该考生安排完座位后从testingcontrols中删除，防止重复分配
			}
		else{
			for(i=0;i<testsize;i++)
			{
				j=(int)(Math.random()*testingcontrols.size());
				TesterTempModel tester=new TesterTempModel();
				tester.setComputernum(testroom.getTestRoomName()+"-"+String.valueOf(computerips.get(i).getComputerId()));
				tester.setName(testingcontrols.get(j).getTesterName());
				Student s = studentService.findByStuNum(testingcontrols.get(j).getTesterID());
				if(s==null)
					tester.setPhoto("");
				else
					tester.setPhoto(s.getPhoto());
				tester.setState(testingcontrols.get(j).getLoginFlag());
				tester.setStunum(testingcontrols.get(j).getTesterID());
				testerlist.add(tester);
				Seat seat=new Seat();
				seat.setFlag(0);
				seat.setIp(computerips.get(i).getIP());
				seat.setStuNum(testingcontrols.get(j).getTesterID());
				seat.setId(UUID.randomUUID().toString());
				seats.add(seat);
				seatService.save(seat);
				testingcontrols.remove(j);
			}
		}
		int testersize=testerlist.size();
		for(i=0;i<testersize;){//按照原来的顺序，将学生重新放入list<TesterTempModel[]>中
			TesterTempModel tt[]=new TesterTempModel[3];
			for(j=0;j<3&&i+j<testersize;j++){
				tt[j]=testerlist.get(i+j);
			}
			i+=j;
			testers.add(tt);
		}
	}
	
	/**考试延时
	 * @throws IOException 
	 * @throws ParseException */
	public void delay() throws IOException, ParseException{
		Map<String, Object> map=new HashMap<String, Object>();
		String testpaperid="";
		try{
			testpaperid=ServletActionContext.getRequest().getSession().getAttribute("testpaperid").toString();
		}catch(Exception e){
			map.put("name", "testpaperidisnotexist");
			JsonUtil.toJson(ServletActionContext.getResponse(), map);
			return;
		}
		TestPaperIDList tpidl=testPaperIDListService.findbytestpaperid(testpaperid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time=tpidl.getEndTime().getTime()+delaytime*60000;
		String newtime=sdf.format(new Date(time));
		tpidl.setEndTime(Timestamp.valueOf(newtime));
		testPaperIDListService.update(tpidl);
		map.put("name", "success");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	
	public void endtest() throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		String testpaperid="";
		try{
			testpaperid=ServletActionContext.getRequest().getSession().getAttribute("testpaperid").toString();
		}catch(Exception e){
			map.put("name", "testpaperidisnotexist");
			JsonUtil.toJson(ServletActionContext.getResponse(), map);
			return;
		}
		TestPaperIDList tpidl=testPaperIDListService.findbytestpaperid(testpaperid);
		if(tpidl.getEndTime().compareTo(new Date())>0){//如果没有到考试结束时间，则将name值赋值为early
			map.put("name", "early");
			JsonUtil.toJson(ServletActionContext.getResponse(), map);
			return;
		}
		map.put("name", "success");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	
	/**检查交卷失败的考生（其中认为state为'在考'的考生是交卷失败）*/
	@SuppressWarnings("unchecked")
	public String checktest(){
		testers=(List<TesterTempModel[]>)ServletActionContext.getRequest().getSession().getAttribute("testers");
		List<TesterTempModel> errorlist= new ArrayList<TesterTempModel>();
		for (TesterTempModel[] testerTempModels : testers) {
			for(int i=0;i<testerTempModels.length&&testerTempModels[i]!=null;i++){
				String isupload=testingControlService.getuploadstate(testerTempModels[i].getStunum());
				if(!"交卷成功!!!".equals(isupload)&&testerTempModels[i].getState().equals("在考"))
					errorlist.add(testerTempModels[i]);
			}
		}
		testers.clear();
		int testersize=errorlist.size();
		int i,j;
		for(i=0;i<testersize;){
			TesterTempModel tt[]=new TesterTempModel[3];
			for(j=0;j<3&&i+j<testersize;j++){
				tt[j]=errorlist.get(i+j);
			}
			i+=j;
			testers.add(tt);
		}
		return "checktest";
	}
	
	@SuppressWarnings("unchecked")
	public void testover() throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			seats=(List<Seat>)ServletActionContext.getRequest().getSession().getAttribute("seats");
			seatService.deleteseats(seats);
			ServletActionContext.getRequest().getSession().removeAttribute("testpaperid");
			ServletActionContext.getRequest().getSession().removeAttribute("seats");
			ServletActionContext.getRequest().getSession().removeAttribute("testers");
			ServletActionContext.getRequest().getSession().removeAttribute("arrange");
			ServletActionContext.getRequest().getSession().removeAttribute("testroomid");
		}catch(Exception e){
			map.put("name", "error");
			JsonUtil.toJson(ServletActionContext.getResponse(), map);
			return;
		}
		map.put("name", "success");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	public void change() throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		Seat seat=seatService.findbyip(ip);
		if(seat!=null){
			map.put("name", "taken");
			JsonUtil.toJson(ServletActionContext.getResponse(), map);
			return;
		}
		seat=seatService.findbystunum(stunum);
		seat.setIp(ip);
		seat.setFlag(0);
		seatService.update(seat);
		testers=(List<TesterTempModel[]>) ServletActionContext.getRequest().getSession().getAttribute("testers");
		int flag=0;
		for (TesterTempModel[] tester : testers) {
			for(int i=0;i<tester.length;i++)
				if(tester[i].getStunum().equals(stunum))
				{
					tester[i].setComputernum(testroomname+"-"+computerid);
					flag=1;
					break;
				}
			if(flag==1) break;
		}
		ServletActionContext.getRequest().getSession().setAttribute("testers", testers);
		map.put("name", "success");
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
	}
	
	public String changeseat(){
		return "changeseat";
	}
	
	
	
	
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public List<TesterTempModel[]> getTesters() {
		return testers;
	}

	public void setTesters(List<TesterTempModel[]> testers) {
		this.testers = testers;
	}

	public int getArrangemodel() {
		return arrangemodel;
	}

	public void setArrangemodel(int arrangemodel) {
		this.arrangemodel = arrangemodel;
	}

	public int getDelaytime() {
		return delaytime;
	}

	public void setDelaytime(int delaytime) {
		this.delaytime = delaytime;
	}

	public String getTestpaperid() {
		return testpaperid;
	}

	public void setTestpaperid(String testpaperid) {
		this.testpaperid = testpaperid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStunum() {
		return stunum;
	}

	public void setStunum(String stunum) {
		this.stunum = stunum;
	}

	public String getTestroomname() {
		return testroomname;
	}

	public void setTestroomname(String testroomname) {
		this.testroomname = testroomname;
	}

	public String getComputerid() {
		return computerid;
	}

	public void setComputerid(String computerid) {
		this.computerid = computerid;
	}
	
	
}
