package com.endlessspace.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.endlessspace.demo.spring.beans.User;

/**
 * BeanFactory验证
 */
public class BeanFactoryMain {
	
	public static void main(String[] args) throws Exception {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		User king = beanFactory.getBean("king", User.class);
		
		LOG.info("{}", king);
		System.in.read();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(BeanFactoryMain.class);
}
