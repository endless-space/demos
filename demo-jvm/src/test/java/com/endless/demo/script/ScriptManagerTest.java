package com.endless.demo.script;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptManagerTest {

	/**
	 * JDK1.7 只有 Mozilla Rhino
	 * JDK1.8 有 Nashorn
	 */
	@Test
	public void testScriptManager() {
		ScriptEngineManager manager = new ScriptEngineManager();
		for (ScriptEngineFactory factory : manager.getEngineFactories()) {
			LOG.info(factory.getEngineName());
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(ScriptManagerTest.class);
}
