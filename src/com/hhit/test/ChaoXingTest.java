package com.hhit.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class ChaoXingTest implements PageProcessor{

	private Site site=Site.me().setRetryTimes(3).setSleepTime(1000);
	
	@Override
	public void process(Page page) {
		page.putField("professionName", page.getHtml().xpath("//ul[@id='profession']/a/text()"));
		page.putField("courseCount", page.getHtml().xpath("//ul/li[@class='name']/a/html()"));
		page.putField("url", page.getUrl().regex("//courseinfo?courseid=2357").toString());
		page.putField("professionType", page.getHtml().xpath("//span[@class='relation']/html()"));
		
		System.out.println("哈哈哈哈"+page.getResultItems().get("professionName"));
		
		page.addTargetRequests(page.getHtml().links().all());
	}

	@Override
	public Site getSite() {
		return site;
	}
    public static void main(String[] args) {

//    	System.out.println("哈哈哈哈");
//        Spider.create(new GithubRepoPageProcessor())
//                //从"http://nation.chaoxing.com/nation?prefix=hhit&id=400A4E71B99E66FEDC29078F41E3E3B57C094C6405C01671B4D556D8C6BCB5AB"开始抓
//                .addUrl("http://nation.chaoxing.com/nation?prefix=hhit&id=400A4E71B99E66FEDC29078F41E3E3B57C094C6405C01671B4D556D8C6BCB5AB")
//                .scheduler(new FileCacheQueueScheduler("K:\\data\\webmagic"))
//                .addPipeline(new FilePipeline())
//                //开启5个线程抓取
//                .thread(5)
//                //启动爬虫
//                .run();
    	
    }
}
