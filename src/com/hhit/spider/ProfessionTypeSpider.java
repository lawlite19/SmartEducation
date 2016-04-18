package com.hhit.spider;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.SpiderProfessionType;
import com.hhit.service.ISpiderProfessionTypeService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class ProfessionTypeSpider implements PageProcessor {

	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	// 获取service
	ISpiderProfessionTypeService spiderProfessionTypeService = (ISpiderProfessionTypeService) ac
			.getBean("spiderProfessionTypeServiceImpl");

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public void process(Page page) {
		// <li><a href="/category/01">哲学</a></li>
		// 筛选名称
		List<String> professionTypeNameList = page.getHtml()
				.xpath("//ul[@class='category']/li/a/html()").all();
		page.putField("professionName", professionTypeNameList);
		// 筛选url
		List<String> professionTypeUrlList = page.getHtml().xpath("//ul[@class='category']/li/a/@href").all();
		page.putField("professionName", professionTypeUrlList);
		
		if(professionTypeNameList.size()>0){
			for(int i=0;i<professionTypeNameList.size();i++){
				SpiderProfessionType model=new SpiderProfessionType(professionTypeNameList.get(i).toString(), professionTypeUrlList.get(i));
				spiderProfessionTypeService.save(model);
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}
	@Test
	public void crawer(){
		Spider.create(new ProfessionTypeSpider())//
		.addUrl("http://mooc.chaoxing.com/category/00")//
		.thread(5)//
		.run();
	}

}
