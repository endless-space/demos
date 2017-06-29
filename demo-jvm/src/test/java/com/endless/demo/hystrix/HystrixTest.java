package com.endless.demo.hystrix;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: Hystrix 测试
 * 
 * 	分布式系统中, 不可避免的会发生一些依赖服务失效, 部分失效
 * 	Hystrix在这些相互依赖的服务交互之间加上了, 延迟容忍和错误容忍逻辑
 */
public class HystrixTest {
	
	@Test
	public void testHystrixCommand() {
		String ret = new HelloHystrixCommand("wangbo").execute();
		LOG.info(ret);
	}
	
	@Test
	public void testHystrixFail() {
		String ret = new FailHystrixCommand("wangbo").execute();
		LOG.info(ret);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(HystrixTest.class);
}
