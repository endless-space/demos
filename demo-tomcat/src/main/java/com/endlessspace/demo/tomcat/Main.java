package com.endlessspace.demo.tomcat;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * 描述: Tomcat嵌入版本样例
 * @author wangbo
 */
public class Main {
	
	private static final String WEBAPP_PATH = "src/main/webapp";
	
	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		
		configureWebappContext((StandardContext) tomcat.addContext("/", new File(WEBAPP_PATH).getAbsolutePath()));
		
		tomcat.setPort(8081);
		tomcat.start();
		tomcat.getServer().await();
	}
	
	private static void configureWebappContext(StandardContext ctx) {
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", new File("classes").getAbsolutePath(), "/"));
        ctx.setResources(resources);
	}
}
