package com.endlessspace.demo.thrift.service.impl;

import org.apache.thrift.TException;

import com.endlessspace.demo.thrift.service.HelloService;

public class HelloServiceImpl implements HelloService.Iface {

	@Override
	public String sayHello(String name) throws TException {
		return "hello, " + name;
	}
}
