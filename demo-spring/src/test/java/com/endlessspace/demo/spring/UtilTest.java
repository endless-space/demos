package com.endlessspace.demo.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PatternMatchUtils;

public class UtilTest {
	
	/**
	 * 描述: PatternMatchUtils使用例子
	 */
	@Test
	public void testPatternMatch() {
		LOG.info("{}", PatternMatchUtils.simpleMatch("*Service", "EmployeeService"));
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilTest.class);
}
