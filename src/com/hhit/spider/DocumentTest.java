package com.hhit.spider;

import java.util.List;

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

public class DocumentTest implements PageProcessor {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	// 获取service
	ISpiderDocumentService spiderDocumentService = (ISpiderDocumentService) ac
			.getBean("spiderDocumentServiceImpl");

	private Site site = Site.me().setRetryTimes(5).setSleepTime(3000)
			.setTimeOut(23000);

	@Override
	public void process(Page page) {
		if (page.getUrl()
				.regex("http://mooc\\.chaoxing\\.com/course/\\d+\\.html")
				.toString() != null) {
			System.out.println("第二层");
			crawCourseInfo(page);
		}

	}

	/**
	 * 爬取课程对应的章节、文档
	 */
	public void crawCourseInfo(Page page) {

		/**
		 * 爬取参考教材
		 */
		// <span class="ans-book-info">
		// <span class="ans-ref-bookname">
		// <a href="javascript:void(0);" class="as-ref-link"
		// id="ext-gen1038">先秦哲学</a>
		// </span>
		// <span class="ans-ref-author">曾仕礼编著</span>
		// <span class="ans-ref-publish">昆明市：云南大学出版社&nbsp;2009.09</span>
		// </span>
		// 筛选名称
		List<String> documentNameList = page.getHtml().xpath("//div[@class='ans-attach-ct']/iframe/@bookname").all();
//				.getHtml()
//				.xpath("//span[@class='ans-ref-bookname']/a[@class='as-ref-link']/text()")
//				.all();
//		<iframe src="/ananas/modules/innerbook/simple.html"
//				readurl="http://resapi.chaoxing.com/realRead?dxid=000006873411&amp;ssid=12553309&amp;d=BD6EECD6198FDD693FD0E87F715B5F05" 
//				coverurl="http://cover.duxiu.com/cover/Cover.dll?iid=6768656B6B696569666F3839393335393236" 
//				bookname="先秦哲学" author="曾仕礼编著" publisher="昆明市：云南大学出版社" 
//				publishdate="2009.09" start="" end="" 
//				innerurl="http://resapi.chaoxing.com/innerurl?dxid=000006873411&amp;ssid=12553309&amp;d=BD6EECD6198FDD693FD0E87F715B5F05&amp;unitid=7719" 
//				class="ans-attach-online ans-book-simple" frameborder="0" scrolling="no">
//		</iframe>
		// 筛选url,得到的不是真实的url,需要添加上"&readstyle=4&tp=flip&rotate=true&cpage=1"，后面处理
		List<String> documentUrlList=page.getHtml().xpath("//iframe/@innerurl").all();
				
		// 筛选作者
		List<String> documentAuthorList =page.getHtml().xpath("//iframe/@bookname").all();
				//page.getHtml().xpath("//span[@class='ans-ref-author']/text()").all();
		// 筛选出版社
		List<String> documentPublishList = page.getHtml()
				.xpath("//span[@class='ans-ref-publish']/text()").all();
		// <span class="ans-book-cover">
		// <a href="javascript:void(0);" class="as-ref-link" id="ext-gen1037">
		// <img
		// src="http://cover.duxiu.com/cover/Cover.dll?iid=6768656B6B696569666F3839393335393236">
		// </a>
		// </span>
		// 筛选封面图片url
		List<String> documentImgUrlList = page.getHtml()
				.xpath("//span[@class='ans-book-cover']/a/img/@src").all();

		if (documentNameList.size() > 0) {
			for (int i = 0; i < documentNameList.size(); i++) {
				String realUrl=documentUrlList.get(i).toString()+"&readstyle=4&tp=flip&rotate=true&cpage=1";
				SpiderDocument model = new SpiderDocument(documentNameList.get(i)
						.toString(), realUrl, documentAuthorList.get(i).toString(),
						documentPublishList.get(i).toString(), documentImgUrlList.get(i)
								.toString(), null);
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

		Spider.create(new DocumentTest())//
				// 全部得到，不分页
				.addUrl("http://mooc.chaoxing.com/course/198413.html")//
				.thread(5)//
				.run();
	}
}
