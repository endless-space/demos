package com.endless.demo.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class NioTest {
	
	@Test
	public void testPath() {
		Path path = Paths.get("E:\\temp\\app.properties");
		
		Path workPath = Paths.get("E:\\workspace");
	}
}
