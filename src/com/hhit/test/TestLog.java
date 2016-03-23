package com.hhit.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
	private Log log=LogFactory.getLog(this.getClass());
	
	@Test
	public void test() throws Exception{
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");
	}
}
