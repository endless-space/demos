package com.endlessspace.demo.dubbo.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.endlessspace.demo.dubbo.service.HelloService;

/**
 * 服务消费者例子
 */
public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		applicationContext.start();
		
		HelloService helloService = (HelloService)applicationContext.getBean("helloService");
		for(;;) {
			String msg = helloService.sayHello("wangbo");
			LOG.info(msg);
			
			Thread.sleep(1000);
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
}
