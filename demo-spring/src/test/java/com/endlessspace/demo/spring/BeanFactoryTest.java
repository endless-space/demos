package com.endlessspace.demo.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import com.endlessspace.demo.spring.beans.User;
import com.endlessspace.demo.spring.beans.UserService;

public class BeanFactoryTest {
	
	@Test
	public void testListableBeanFactory() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(bf);
		beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
		User user = bf.getBean("king", User.class);
		LOG.info("{}", user);
	}
	
	/**
	 * FactoryBean和BeanFactory
	 * FactoryBean是实现了FactoryBean<T>接口的Bean, 根据该bean的id获得的是getObject返回的对象
	 */
	@Test
	public void testFactoryBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(bf);
		beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
		UserService userService = bf.getBean("userServiceWithLog", UserService.class);
		userService.getUserNickname("test");
	}
	
	
	@Test
	public void testUserDefineElem() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
		reader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
		User wangbo = bf.getBean("wangbo", User.class);
		LOG.info("{}", wangbo);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(BeanFactoryTest.class);
}
