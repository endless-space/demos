package com.endless.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 描述: 
 */
public class HelloHystrixCommand extends HystrixCommand<String> {
	
	private String name;

	public HelloHystrixCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("BIC"));
		this.name = name;
	}

	/**
	 * 
	 */
	@Override
	protected String run() throws Exception {
		return "hello " + name;
	}
}
