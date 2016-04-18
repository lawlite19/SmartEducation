package com.hhit.spider;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderProfessionType;
import com.hhit.service.ISpiderCourseService;
import com.hhit.service.ISpiderProfessionTypeService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class CourseSpider implements PageProcessor {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	// 获取service
	ISpiderCourseService spiderCourseService = (ISpiderCourseService) ac
			.getBean("spiderCourseServiceImpl");
	ISpiderProfessionTypeService spiderProfessionTypeService = (ISpiderProfessionTypeService) ac
			.getBean("spiderProfessionTypeServiceImpl");

	private Site site = Site.me().setRetryTimes(5).setSleepTime(2000).setTimeOut(10000);

	@Override
	public void process(Page page) {
//		<div class="label">
//		 哲学		</div>
		//筛选专业类型
		String professionType=page.getHtml().xpath("//div[@class='label']/text()").toString();
//		<li class="ans-slow-anim">
//		<div class="picArea ans-slow-anim"><a href="/course/198413.html" target="_blank">
//			<img src="http://p.ananas.chaoxing.com/star/258_153c/1384413396917gvcrs.jpg" width="178" height="109"></a>
//		</div>
//		<div class="introArea"><a href="/course/198413.html" target="_blank" title="中华传统思想-对话先秦哲学">中华传统思想-对话先秦哲学</a></div> 
//		<div class="introArea2" title="万献初 李景林 郭齐勇 夏可君  陈炎   武汉大学">
//								万献初等
//				 武汉大学
//			 
//		</div> 
//		</li>
		// 筛选名称
		List<String> courseNameList = page.getHtml().xpath("//div[@class='introArea']/a/html()").all();
		page.putField("professionName", courseNameList);
		// 筛选url
		List<String> courseUrlList = page.getHtml().xpath("//div[@class='introArea']/a/@href").all();
		page.putField("professionName", courseUrlList);
		// 筛选信息
		List<String> infoList = page.getHtml().xpath("//div[@class='introArea2']/@title").all();
		page.putField("professionName", infoList);

		if (courseNameList.size() > 0) {
			for (int i = 0; i < courseNameList.size(); i++) {
				SpiderCourse model = new SpiderCourse(courseNameList.get(i).toString().trim(),infoList.get(i).toString(), professionType);
				spiderCourseService.save(model);
			}
		}
		List<SpiderProfessionType> list=spiderProfessionTypeService.findAll();
		List<String> urlList=new ArrayList<String>();
		for(int j=2;j<list.size();j++){
			urlList.add(list.get(j).getUrl().trim()+"/0/200");
		}
		//将后续urls作为请求
		page.addTargetRequests(urlList);
	}

	@Override
	public Site getSite() {
		return site;
	}

	@Test
	public void crawer() {
		
		Spider.create(new CourseSpider())//
				// 全部得到，不分页
				.addUrl("http://mooc.chaoxing.com/category/01/0/1000")//
				.thread(5)//
				.run();
	}
}
