package com.endlessspace.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 描述: Spring资源加载机制
 */
public class ResourcePatternResolverMain {
	
	/**
	 * Entry Point
	 */
	public static void main(String[] args) throws Exception {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource txtFile = resourceLoader.getResource("file:///e:/docker.txt");
		
		LOG.info("{}", txtFile.getFile());
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourcePatternResolverMain.class);
}
