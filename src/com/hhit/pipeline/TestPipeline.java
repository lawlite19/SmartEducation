package com.hhit.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.hhit.entity.Test;
import com.hhit.service.ITestService;

@Component("TestPipeline")
public class TestPipeline implements PageModelPipeline<Test>{

	@Resource
	private ITestService testService;

	public void process(Test t, Task task) {
		System.out.println("-------------------------进入了----------------------------------");
		System.out.println("-------------------------进入了----------------------------------");
		System.out.println("-------------------------进入了----------------------------------");
		System.out.println("-------------------------进入了----------------------------------");
		testService.save(t);
	}
	
	
}
