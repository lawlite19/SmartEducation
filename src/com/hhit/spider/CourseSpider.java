package com.hhit.spider;

import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.SpiderChapter;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderDocument;
import com.hhit.entity.SpiderProfessionType;
import com.hhit.service.ISpiderChapterService;
import com.hhit.service.ISpiderCourseService;
import com.hhit.service.ISpiderDocumentService;
import com.hhit.service.ISpiderProfessionTypeService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class CourseSpider implements PageProcessor {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// 获取service
	ISpiderCourseService spiderCourseService = (ISpiderCourseService) ac.getBean("spiderCourseServiceImpl");
	ISpiderProfessionTypeService spiderProfessionTypeService = (ISpiderProfessionTypeService) ac.getBean("spiderProfessionTypeServiceImpl");
	ISpiderChapterService spiderChapterService = (ISpiderChapterService) ac.getBean("spiderChapterServiceImpl");
	ISpiderDocumentService spiderDocumentService = (ISpiderDocumentService) ac.getBean("spiderDocumentServiceImpl");

	private Site site = Site.me().setRetryTimes(5).setSleepTime(3000).setTimeOut(23000);

	@Override
	public void process(Page page) {
		// 格式：http://mooc.chaoxing.com/category/01/0/1000
		if (page.getUrl().regex("http://mooc\\.chaoxing\\.com/category/\\d+/\\d/\\d+")
				.toString() != null) {
			System.out.println("第一层");
			crawerCourse(page);
		}
		// 格式：http://mooc.chaoxing.com/course/55672.html
		else if (page.getUrl().regex("http://mooc\\.chaoxing\\.com/course/\\d+\\.html")
				.toString() != null) {
			System.out.println("第二层");
			crawCourseInfo(page);
		}

	}

	/**
	 * 爬去课程信息
	 */
	public void crawerCourse(Page page) {
		// <div class="label">
		// 哲学 </div>

		// 筛选专业类型
		String professionType = page.getHtml()
				.xpath("//div[@class='label']/text()").toString();
		// <li class="ans-slow-anim">
		// <div class="picArea ans-slow-anim"><a href="/course/198413.html"
		// target="_blank">
		// <img
		// src="http://p.ananas.chaoxing.com/star/258_153c/1384413396917gvcrs.jpg"
		// width="178" height="109"></a>
		// </div>
		// <div class="introArea"><a href="/course/198413.html" target="_blank"
		// title="中华传统思想-对话先秦哲学">中华传统思想-对话先秦哲学</a></div>
		// <div class="introArea2" title="万献初 李景林 郭齐勇 夏可君  陈炎   武汉大学">
		// 万献初等
		// 武汉大学
		//
		// </div>
		// </li>
		// 筛选名称
		List<String> courseNameList = page.getHtml()
				.xpath("//div[@class='introArea']/a/html()").all();
		// page.putField("courseNameList", courseNameList);
		// 筛选url
		List<String> courseUrlList = page.getHtml()
				.xpath("//div[@class='introArea']/a/@href").all();
		// page.putField("courseUrlList", courseUrlList);
		// 筛选信息
		List<String> infoList = page.getHtml()
				.xpath("//div[@class='introArea2']/@title").all();
		// page.putField("infoList", infoList);

		if (courseNameList.size() > 0) {
			for (int i = 0; i < courseNameList.size(); i++) {
				SpiderCourse model = new SpiderCourse(courseNameList.get(i)
						.toString().trim(), courseUrlList.get(i).toString()
						.trim(), infoList.get(i).toString(), professionType);
				spiderCourseService.save(model);

				// Request request2=new
				// Request(courseUrlList.get(i)).setPriority(1).putExtra("courseModel",
				// model);
				// page.putField("model", model);
				// 设置优先级为1
				page.addTargetRequest(new Request(courseUrlList.get(i))
						.setPriority(1).putExtra("courseModel", model));
			}
		}
		 List<SpiderProfessionType> list =
		 spiderProfessionTypeService.findAll();
		 for (int j = 2; j < list.size(); j++) {
		 // 设置优先级为0
		 page.addTargetRequest(new Request(list.get(j).getUrl()+"/0/1000")
		 .setPriority(0));
		 }

		// List<String> urlList=new ArrayList<String>();
		// for(int j=2;j<list.size();j++){
		// urlList.add(list.get(j).getUrl()+"/0/1000");
		// }
		// //将后续urls作为请求
		// page.addTargetRequests(urlList);
	}

	/**
	 * 爬取课程对应的章节、文档
	 */
	public void crawCourseInfo(Page page) {
		/**
		 * 得到上级传来的model,用户保存对应的课程
		 */
		SpiderCourse courseModel = (SpiderCourse) page.getRequest().getExtra(
				"courseModel");
		// <div class="mt10 f33 l g5">
		// <span>木结构设计</span>
		// </div>
		// 筛选课程名
		String courseName = page.getHtml()
				.xpath("//div[@class='mt10 f33 l g5']/span/text()").toString();
		// <li class="mb15 course_section fix">
		// <!--<a class="wh"
		// href="/nodedetailcontroller/visitnodedetail?knowledgeId=789300"
		// target="_blank">-->
		// <a class="wh"
		// href="/nodedetailcontroller/visitnodedetail?knowledgeId=789300">
		// <div class="f16 chapter_index l">1.1</div>
		// <div class="f16 pct80 pr10 r">和的哲学（一）</div>
		// </a>
		// </li>
		/**
		 * 爬取章节
		 */
		// 筛选url
		List<String> chapterUrlList = page
				.getHtml()
				.xpath("//li[@class='mb15 course_section fix']/a[@class='wh']/@href")
				.all();
		// page.putField("chapterUrlList", chapterUrlList);
		// 筛选章节号
		List<String> chapterNumList = page.getHtml()
				.xpath("//div[@class='f16 chapter_index l']/text()").all();
		// page.putField("chapterNumList", chapterNumList);
		// 筛选章节名
		List<String> chapterNameList = page.getHtml()
				.xpath("//div[@class='f16 pct80 pr10 r']/text()").all();
		// page.putField("chapterNameList", chapterNameList);

		if (chapterUrlList.size() > 0) {
			for (int i = 0; i < chapterUrlList.size(); i++) {
				SpiderChapter model = new SpiderChapter(chapterNumList.get(i)
						.toString(), chapterNameList.get(i).toString(),
						chapterUrlList.get(i).toString(), courseName,
						courseModel);
				spiderChapterService.save(model);
			}
		}

		/**
		 * 爬取课程对应的章节文档 ，这个有点特殊，它是一个iframe
		 * 并且通过分析之后得出iframe里有个data属性，是json格式的数据，然后网站再通过js拼接html代码
		 * 汉字采用的是unicode编码
		 */

		// 得到的是json格式的字符串
		// 格式：
		// {"readurl":"http://resapi.chaoxing.com/realRead?dxid=000006873411&ssid=12553309&d=BD6EECD6198FDD693FD0E87F715B5F05",
		// "coverurl":"http://cover.duxiu.com/cover/Cover.dll?iid=6768656B6B696569666F3839393335393236",
		// "bookname":"\u5148\u79e6\u54f2\u5b66",
		// "author":"\u66fe\u4ed5\u793c\u7f16\u8457",
		// "publisher":"\u6606\u660e\u5e02\uff1a\u4e91\u5357\u5927\u5b66\u51fa\u7248\u793e",
		// "publishdate":"2009.09",
		// "id":"ext-gen1223"}

		List<String> allInfoList = page.getHtml().xpath("//iframe/@data").all();

		if (allInfoList.size() > 0) {
			for (int i = 0; i < allInfoList.size(); i++) {
				// String转为json
				JSONObject json = JSONObject.fromObject(allInfoList.get(i)
						.toString());
				String realUrl = json.getString("readurl");
				//http://resapi.chaoxing.com/realRead?dxid=000006873411&ssid=12553309&d=BD6EECD6198FDD693FD0E87F715B5F05
				//连接中realRead替换为innerurl,并加上后缀&unitid=7719&readstyle=4&tp=flip&rotate=true&cpage=1
				realUrl = realUrl.replace("realRead", "innerurl")+ "&unitid=7719&readstyle=4&tp=flip&rotate=true&cpage=1";
						
				SpiderDocument model = new SpiderDocument(
						json.getString("bookname"), realUrl,
						json.getString("author"), json.getString("publisher"),
						json.getString("publishdate"),
						json.getString("coverurl"), courseModel);
				spiderDocumentService.save(model);
			}
		}
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
				.thread(10)//
				.run();
	}
}
