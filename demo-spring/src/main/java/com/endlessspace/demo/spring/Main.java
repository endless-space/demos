package com.endlessspace.demo.spring;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.endlessspace.demo.spring.beans.User;

public class Main {
	
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		User core = context.getBean("coreUser", User.class);
		
		LOG.info("id: {}, name: {}, displayName: {}", context.getId(), context.getApplicationName(), context.getDisplayName());
		LOG.info("{}", core);
		LOG.info("{}, {}", Main.class.getName(), Main.class.getSimpleName());
		System.in.read();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
}
