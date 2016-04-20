package com.hhit.spider;

import java.util.List;





import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.SpiderDocument;
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
	 * 这个有点特殊，它是一个iframe
	 * 并且通过分析之后得出iframe里有个data属性，是json格式的数据，然后网站再通过js拼接html代码
	 * 汉字采用的是unicode编码
	 */
	public void crawCourseInfo(Page page) {

		/**
		 * 爬取参考教材
		 */

		//得到的是json格式的字符串
		//格式：
//		{"readurl":"http://resapi.chaoxing.com/realRead?dxid=000006873411&ssid=12553309&d=BD6EECD6198FDD693FD0E87F715B5F05",
//		"coverurl":"http://cover.duxiu.com/cover/Cover.dll?iid=6768656B6B696569666F3839393335393236",
//		"bookname":"\u5148\u79e6\u54f2\u5b66",
//		"author":"\u66fe\u4ed5\u793c\u7f16\u8457",
//		"publisher":"\u6606\u660e\u5e02\uff1a\u4e91\u5357\u5927\u5b66\u51fa\u7248\u793e",
//		"publishdate":"2009.09",
//		"id":"ext-gen1223"}
		
		List<String> allInfoList = page.getHtml().xpath("//iframe/@data").all();
		
		
		if(allInfoList.size()>0){
			for(int i=0;i<allInfoList.size();i++){
				//String转为json
				JSONObject json = JSONObject.fromObject(allInfoList.get(i).toString()); 
				String realUrl=json.getString("readurl");

				realUrl=realUrl.replace("realRead", "innerurl");
				realUrl=realUrl+"&unitid=7719&readstyle=4&tp=flip&rotate=true&cpage=1";
				SpiderDocument model=new SpiderDocument(json.getString("bookname"), realUrl, json.getString("author"), json.getString("publisher"),json.getString("publishdate"), json.getString("coverurl"), null);
				spiderDocumentService.save(model);
			}
		}

	}
	@Test
	public void test(){
		
		String ascii="\u5148\u79e6\u54f2\u5b66";
		System.out.println(ascii);
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
