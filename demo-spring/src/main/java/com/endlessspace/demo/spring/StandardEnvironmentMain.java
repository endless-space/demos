package com.endlessspace.demo.spring;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

/**
 * Environment 调试类
 */
public class StandardEnvironmentMain {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		/**
		 * 获得运行时系统属性
		 */
		Map props = (Map)System.getProperties();
		for (Iterator it = props.keySet().iterator(); it.hasNext();) {
			Object key = it.next();
			Object val = props.get(key);
			LOG.info("{}: {}", key, val);
		}
		
		/**
		 * 获得操作系统环境变量
		 */
		Map<String, String> envs = System.getenv();
		for (Iterator<String> it = envs.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			String val = envs.get(key);
			
			LOG.info("{}: {}", key, val);
		}
		
		/**
		 * Spring对上述两种两种信息的抽象
		 */
		Environment env = new StandardEnvironment();
		LOG.info("{}", Arrays.toString(env.getActiveProfiles()));
		LOG.info("{}", Arrays.toString(env.getDefaultProfiles()));
		LOG.info(env.getProperty("java.version"));
		LOG.info(env.getProperty("JAVA_HOME"));
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(StandardEnvironmentMain.class);
}
