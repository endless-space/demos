package com.endlessspace.demo.spring.udb;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 命名空间处理器
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
