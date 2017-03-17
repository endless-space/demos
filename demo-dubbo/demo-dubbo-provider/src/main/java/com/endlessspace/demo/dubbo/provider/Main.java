package com.endlessspace.demo.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述: 服务提供者
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		applicationContext.start();
		
        System.out.println("服务已经启动...");
        System.in.read();
	}
}
