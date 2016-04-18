package com.hhit.action;

import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import com.hhit.base.BaseAction;
import com.hhit.entity.SpiderProfession;
import com.hhit.spider.ChaoXingTest;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpiderProfessionAction extends BaseAction<SpiderProfession>{

	@Test
	public void test() throws Exception{
		ChaoXingTest test=new ChaoXingTest();
		test.crawer();
	}


}
