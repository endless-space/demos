package com.endlessspace.demo.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	public static void main(String[] args) {
		Class<?> clazz = User.class;
		LOG.info("class: {}", clazz);
		LOG.info("" + clazz.getTypeParameters());
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
}
