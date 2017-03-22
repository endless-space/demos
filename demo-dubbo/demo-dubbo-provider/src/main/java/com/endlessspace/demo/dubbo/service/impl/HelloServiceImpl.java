package com.endlessspace.demo.dubbo.service.impl;

import com.endlessspace.demo.dubbo.service.HelloService;

/**
 * 测试服务实现
 */
public class HelloServiceImpl implements HelloService {
	
	private HelloProperties props;

	/**
	 * 测试服务方法实现
	 */
	@Override
	public String sayHello(String name) {
		return "hello " + name + " from: " + props.getName();
	}

	public HelloProperties getProps() {
		return props;
	}

	public void setProps(HelloProperties props) {
		this.props = props;
	}
}
