package com.hhit.spider;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.SpiderChapter;
import com.hhit.entity.SpiderCourse;
import com.hhit.service.ISpiderChapterService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class ChapterSpider implements PageProcessor {

	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	ISpiderChapterService spiderChapterService = (ISpiderChapterService) ac
			.getBean("spiderChapterServiceImpl");

	private Site site = Site.me().setRetryTimes(3).setTimeOut(5000);

	@Override
	public void process(Page page) {

		// <div class="mt10 f33 l g5">
		// <span>木结构设计</span>
		// </div>
		// 筛选课程名
		String courseName = page.getHtml()
				.xpath("//div[@class='mt10 f33 l g5']/span/text()").toString();
		// <a class="wh"
		// href="/nodedetailcontroller/visitnodedetail?knowledgeId=783956">
		// <div class="f16 chapter_index l">4.1</div>
		// <div class="f16 pct80 pr10 r">木结构设计（四）</div>
		// </a>
		// 筛选url
		List<String> chapterUrlList = page.getHtml()
				.xpath("//a[@class='wh']/@href").all();
		page.putField("chapterUrlList", chapterUrlList);
		// 筛选章节号
		List<String> chapterNumList = page.getHtml()
				.xpath("//div[@class='f16 chapter_index l']/text()").all();
		page.putField("chapterNumList", chapterNumList);
		// 筛选章节名
		List<String> chapterNameList = page.getHtml()
				.xpath("//div[@class='f16 pct80 pr10 r']/text()").all();
		page.putField("chapterNameList", chapterNameList);

		if (chapterUrlList.size() > 0) {
			for (int i = 0; i < chapterUrlList.size(); i++) {
//				SpiderCourse course=;
				SpiderChapter model = new SpiderChapter(chapterNumList.get(i)
						.toString(), chapterNameList.get(i).toString(),
						chapterUrlList.get(i).toString(), courseName, null);
				spiderChapterService.save(model);
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}
	public void crawer(){
		Spider.create(new ChapterSpider())//
		.addUrl("http://mooc.chaoxing.com/course/56823.html")//
		.thread(5)//
		.run();
	}
}
