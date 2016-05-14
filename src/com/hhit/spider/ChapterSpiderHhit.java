package com.hhit.spider;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.SpiderChapter;
import com.hhit.service.IChapterService;
import com.hhit.service.ICourseService;


/**
 * 爬取本校的课程的章节，保存到Chapter中
 * @author bob
 *
 */
public class ChapterSpiderHhit implements PageProcessor{
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// 获取service
	ICourseService courseService=(ICourseService) ac.getBean("courseServiceImpl");
	IChapterService chapterService = (IChapterService) ac.getBean("chapterServiceImpl");

	
	private Site site = Site.me().setRetryTimes(5).setSleepTime(3000).setTimeOut(23000);


	@Override
	public void process(Page page) {
			crawCourseInfo(page);
	}
	/**
	 * 爬取课程对应的介绍、章节、文档
	 */
	public void crawCourseInfo(Page page) {

		// <div class="mt10 f33 l g5">
		// <span>木结构设计</span>
		// </div>
		// 筛选课程名
		String courseName = page.getHtml()
				.xpath("//div[@class='mt10 f33 l g5']/span/text()").toString();
		
		//找到课程
		Course courseModel = courseService.findById(9);
		
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
//		<div class="cell">
//        <a class="wh wh1" style="cursor:default">
//	<div class="f16">第一章 Java概述</div>


		List<String> chapterNameOneList=page.getHtml().xpath("//div[@class='cell']/a[@class='wh wh1']/div[@class='f16']/text()").all();
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

		if (chapterNameOneList.size() > 0) {
			for (int i = 0; i < chapterNameOneList.size(); i++) {
				//保存一级标题
				Chapter modelOne=new Chapter("", chapterNameOneList.get(i).toString(),"", courseName, courseModel,null);
				chapterService.save(modelOne);
				for(int j=0;j<chapterNameList.size();j++){
					if(chapterNumList.get(j).startsWith(""+(i+1)+".")){
						//保存二级标题
						Chapter model = new Chapter(chapterNumList.get(j)
								.toString(), chapterNameList.get(j).toString(),chapterUrlList.get(i).toString(),
								 courseName,null,modelOne);
						chapterService.save(model);
					}
				}
			}
		}

	}


	@Override
	public Site getSite() {
		return site;
	}
	
	@Test
	public void crawer() {

		Spider.create(new ChapterSpiderHhit())//
				.addUrl("http://mooc1.chaoxing.com/course/86454598.html")//
				//.addRequest(new Request(professionTypeModel.getUrl()+"/0/1000").setPriority(0).putExtra("professionTypeModel", professionTypeModel))
				.thread(10)//
				.run();
	}

}
