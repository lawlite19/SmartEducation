package com.hhit.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.SpiderProfession;
import com.hhit.service.ISpiderProfessionService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class ChaoXingTest implements PageProcessor{

	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	//获取service
	ISpiderProfessionService spiderProfessionService=(ISpiderProfessionService) ac.getBean("spiderProfessionServiceImpl");

	private Site site=Site.me().setRetryTimes(3).setSleepTime(1000);
	
	@Override
	public void process(Page page) {
//		<li>
//        <a href="/courselist?professionid=100087&xuekeid=6" target="_self"><img class="fl" src="/zy/2288.jpg" onerror="src='/zy/no_course.jpg'" width="136" height="80"></a>
//        <ul>
//          <li class="name"><a target="_self" href="/courselist?professionid=100087xuekeid=6&amp;schoolid=0">软件工程专业</a></li>
//          <li class="info">
//            <span class="num"><b class="icons"></b>共有42门课程</span><span class="relation" title="所属学科：工学"><b class="icons"></b>工学</span>
//          </li>
//        </ul>
//      </li>
		//筛选专业名称
		List<String> professionNameList=page.getHtml().xpath("//ul/li[@class='name']/a/html()").all();
		page.putField("professionName", professionNameList);
		
		//筛选课程数量
		List<String> courseCountList=page.getHtml().xpath("//ul/li[@class='info']/span[@class='num']/text()").all();
		page.putField("courseCount", courseCountList);
		
		//筛选url 也可以用下面的，不过它使用了两个a标签，会得到重复的
		//page.getHtml().links().regex("(\\/courselist\\?professionid=\\d+&xuekeid=\\d+)").all();
		
		List<String> urlList=page.getHtml().xpath("//li[@class='name']/a/@href").all();
		page.putField("url", urlList);
		
		//筛选专业类型
		List<String> professionTypeList=page.getHtml().xpath("//span[@class='relation']/text()").all();
		page.putField("professionType", professionTypeList);
		
		//System.out.println("哈哈哈哈"+page.getResultItems().get("professionName"));
		//如果url不为空就保存到数据库
		if(professionNameList.size()>0){
			for(int i=0;i<professionNameList.size();i++){
				SpiderProfession spiderProfession=new SpiderProfession(professionNameList.get(i).toString(),1,urlList.get(i).toString(),professionTypeList.get(i).toString());
				spiderProfessionService.save(spiderProfession);
			}
		}
	
//		page.addTargetRequests(page.getHtml().links().regex("(\\/courselist\\?professionid=\\d+&xuekeid=\\d+)").all());
//		List<String> urls = page.getHtml().css("div.pagination").links().regex("").all();
//		page.addTargetRequests(urls);
		//page.addTargetRequest("http://nation.chaoxing.com/nation?prefix=hhit&id=400A4E71B99E66FEDC29078F41E3E3B57C094C6405C01671B4D556D8C6BCB5AB");
	}

	@Override
	public Site getSite() {
		return site;
	}
	public void crawer(){
        Spider.create(new ChaoXingTest())
        //从"http://nation.chaoxing.com/nation?prefix=hhit&id=400A4E71B99E66FEDC29078F41E3E3B57C094C6405C01671B4D556D8C6BCB5AB"开始抓
        .addUrl("http://nation.chaoxing.com/nation?prefix=hhit&id=400A4E71B99E66FEDC29078F41E3E3B57C094C6405C01671B4D556D8C6BCB5AB")
        //开启5个线程抓取
        .thread(5)
        //启动爬虫
        .run();
	}
    public static void main(String[] args) {
    	ChaoXingTest chaoXing=new ChaoXingTest();
    	chaoXing.crawer();
    }
}
