package com.hhit.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hhit.entity.SpiderProfession;
import com.hhit.service.ISpiderPorfessionService;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component
public class SpiderProfessionPipeline implements PageModelPipeline<SpiderProfession>{

	@Resource
	private ISpiderPorfessionService spiderProfessionService;
	
	@Override
	public void process(SpiderProfession t, Task task) {
		spiderProfessionService.save(t);
	}

}
