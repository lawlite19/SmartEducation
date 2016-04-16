package com.hhit.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hhit.entity.Test;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


@Component
public class TestSpider {
	@Qualifier("TestPipeline")
    @Autowired
    private PageModelPipeline testPipeline;

    public void crawl() {
        OOSpider.create(Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),//
        		testPipeline, Test.class).addUrl("https://github.com/code4craft").thread(5).run();
    }

    public static void main(String[] args) {
    	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        final TestSpider test = ac.getBean(TestSpider.class);
        test.crawl();
    }
}
