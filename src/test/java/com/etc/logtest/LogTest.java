package com.etc.logtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
	
	private Logger log = LoggerFactory.getLogger(LogTest.class);
	
	@Test
	public void test1(){
		//由于默认的logback的日志级别是info，所以debug没有输出
		log.debug("debug...");
		log.info("info...");
		log.warn("warn...");
		log.error("error...");
		
	}
}
