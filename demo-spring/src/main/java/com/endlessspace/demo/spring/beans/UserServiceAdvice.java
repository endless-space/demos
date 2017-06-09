package com.endlessspace.demo.spring.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * 描述: AOP 增强
 * @author wangbo
 */
public class UserServiceAdvice implements MethodBeforeAdvice {

	/**
	 * 描述: 调用方法之前拦截
	 */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		LOG.info("调用方法 {}, 参数为 {}", method.getName(), Arrays.toString(args));
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceAdvice.class);
}
