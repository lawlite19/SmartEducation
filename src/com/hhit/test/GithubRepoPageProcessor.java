package com.hhit.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.Test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;


public class GithubRepoPageProcessor implements PageProcessor {

//	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//	
//	ITestService testService = (ITestService) ac.getBean("testServiceImpl");


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
    	String author=page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString();
        page.putField("author",author );
        String name=page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString();
        page.putField("name", name);

        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        String readme=page.getHtml().xpath("//div[@id='readme']/tidyText()").toString();
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

//        if(name==null){
//            Test test=new Test();
//            test.setAuthor(author);
//            test.setName(name);
//            test.setReadme(readme);
//            testService.save(test);
//        }
        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://github.com/code4craft")
                .scheduler(new FileCacheQueueScheduler("K:\\data\\webmagic"))
                .addPipeline(new FilePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}