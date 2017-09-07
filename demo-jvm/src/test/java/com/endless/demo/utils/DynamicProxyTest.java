package com.endless.demo.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DynamicProxyTest {
	
	@Test
	public void testStaticProxy() {
		Player player = new NormalPlayer("wangbo");
		
		PlayerProxy proxy = new PlayerProxy(player);
		proxy.eat();
		proxy.sleep();
		proxy.play();
	}
	
	@Test
	public void testDynamicProxy() {
		Player player = new NormalPlayer("wangbo");
		InvocationHandler handler = new PlayerInvocationHandler(player);
		
		Player proxy = (Player) Proxy.newProxyInstance(player.getClass().getClassLoader(), new Class[] {Player.class}, handler);
		proxy.eat();
		proxy.sleep();
		proxy.play();
	}
	
	@Test
	public void testProxyWithCglib() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(FakePlayer.class);
		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("-_-");
				return proxy.invokeSuper(obj, args);
			}
		});
		
		FakePlayer player = (FakePlayer)enhancer.create();
		player.setName("wangbo");
		player.eat();
		player.sleep();
		player.play();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(DynamicProxyTest.class);
}
