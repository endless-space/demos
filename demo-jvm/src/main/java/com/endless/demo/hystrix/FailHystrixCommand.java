package com.endless.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FailHystrixCommand extends HystrixCommand<String> {
	
	private String name;
	
	public FailHystrixCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("BIC"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		throw new RuntimeException("执行异常");
	}

	@Override
	protected String getFallback() {
		return "hello fail";
	}
}
