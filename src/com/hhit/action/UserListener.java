package com.hhit.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;








import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hhit.entity.User;
import com.hhit.entity.UserList;
import com.hhit.entity.UserOnlineTime;
import com.hhit.service.IUserOnlineTimeService;
import com.hhit.service.IUserService;
import com.hhit.util.SizeOfObjectUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 访问登录时也经过这里，但是没有登录操作就不计算为在线
 * @author bob
 *
 */
@Controller
@Scope("prototype")
public class UserListener implements HttpSessionAttributeListener,ServletContextListener{

	private IUserService userService;
	private IUserOnlineTimeService userOnlineTimeService;
	
	private UserList userList = UserList.getInstance();
	
	/**
	 * ActionContext.getContext().getSession().put()时调用
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if((event!=null)&&event.getName().equals("user")){
			User userFind= (User) event.getSession().getAttribute("user");
			if(userFind!=null){
				try {
					//判断是否已经存在，若存在不计入
					if(!userList.IsExist(userFind.getId())){
						try {
							userList.addUser(userFind);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * ActionContext.getContext().getSession().remove()时调用
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if((event!=null)&&event.getName().equals("user")){
			User userFind= (User) event.getValue();
			
			try {
				//如果Vector中有用户==》移除==》记录==>这样如果切换到别的浏览器同一账号登录且之前账号没有退出就无法计时了
				//如果Vector中没用户==》不记录
				if(userList.IsExist(userFind.getId())){
					//userList中移除User
					try {
						userList.RemoveUser(userFind);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("用户数量"+userList.getUserCount());
					//得到当前时间
					Timestamp nowTime=new Timestamp(new Date().getTime());
					//计算时间差--在线时长
					int durationMinute=(int)(nowTime.getTime()-userFind.getLoginTime().getTime())/(1000*60);
					//得到最后一条记录
					UserOnlineTime onlineTimeFind=userOnlineTimeService.findByUser(userFind);
					if(onlineTimeFind==null){
						onlineTimeFind=new UserOnlineTime(nowTime, durationMinute, userFind);
						userOnlineTimeService.save(onlineTimeFind);
						//更新总时长
						userFind.setTotalMinute(durationMinute+userFind.getTotalMinute());
						userService.update(userFind);
					}
					else{
						//判断是否是当天
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
						String stringNowDate= format.format(nowTime);
						String stringLastDate=format.format(onlineTimeFind.getDate());
						if(stringLastDate.equals(stringNowDate)){//当天的
							//这次在线的时长加上已有的
							durationMinute=(int)(nowTime.getTime()-userFind.getLoginTime().getTime())/(1000*60);
							int realDuration=durationMinute+onlineTimeFind.getDurationMinute();
							onlineTimeFind.setDate(nowTime);
							onlineTimeFind.setDurationMinute(realDuration);
							//更新数据库
							userOnlineTimeService.update(onlineTimeFind);
							
							//更新总时长
							userFind.setTotalMinute(durationMinute+userFind.getTotalMinute());
							userService.update(userFind);
						}
						else{//不是当天--新建
							durationMinute=(int)(nowTime.getTime()-userFind.getLoginTime().getTime())/(1000*60);
							onlineTimeFind=new UserOnlineTime(nowTime, durationMinute, userFind);
							userOnlineTimeService.save(onlineTimeFind);
							//更新总时长
							userFind.setTotalMinute(durationMinute+userFind.getTotalMinute());
							userService.update(userFind);
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}
	//显示平台信息
	public String list() throws Exception{
		
		//在线人数
		ActionContext.getContext().put("onlineCount", userList.getUserCount());
		
//		List<User> pageUserList=new ArrayList<User>();
//		for(int i=(pageNum-1)*pageSize;i<pageNum*pageSize;i++){
//			if(userList.getUserList().hasMoreElements()){
//				pageUserList.add(e)
//			}
//		}
//		
		ActionContext.getContext().put("userList", userList.getUserList());
		return "list";
	}
	

	/**
	 * 容器初试话加载一些需要的service
	 * @param sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获取容器与相关的Service对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		userService = (IUserService) ac.getBean("userServiceImpl");
		userOnlineTimeService = (IUserOnlineTimeService) ac.getBean("userOnlineTimeServiceImpl");
	}
	
	// ===================
//	public Vector<User> getUserVector() {
//		return userVector;
//	}
//
//	public void setUserVector(Vector<User> userVector) {
//		UserListener.userVector = userVector;
//	}
//
//	public Integer getCount() {
//		return userVector.size();
//	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
