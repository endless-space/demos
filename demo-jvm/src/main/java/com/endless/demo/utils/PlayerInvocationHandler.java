package com.endless.demo.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PlayerInvocationHandler implements InvocationHandler {
	
	private Player proxyed;
	
	public PlayerInvocationHandler(Player proxyed) {
		this.proxyed = proxyed;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理");
		method.invoke(proxyed, args);
		return null;
	}
}
