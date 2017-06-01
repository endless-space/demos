package com.endlessspace.demo.dal.mysql;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: Mysql代理
 * @author wangbo
 */
public class Proxy {
	
	// 服务器端口
	private int port;
	
	// 被代理IP
	private String proxyedIP;
	
	// 被代理端口
	private int proxyedPort;
	
	// 服务器端线程池
	private ExecutorService threadPool;
	
	// 代理服务是否正在运行
	private boolean isRunning = true;
	
	public Proxy(int port, String proxyedIP, int proxyedPort) {
		this.port = port;
		this.proxyedIP = proxyedIP;
		this.proxyedPort = proxyedPort;
		
		this.threadPool = Executors.newCachedThreadPool();
	}
	
	public void start() {
		ServerSocket serverSocket = bindServerSocket();
		if (serverSocket == null)
			return;
		
		LOG.info("代理服务启动在 {} 端口", port);
		while (isRunning) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				LOG.error("客户端连接建立失败", e);
			}
			
			if (socket == null)
				continue;
			
			threadPool.execute(new ProxyContext(proxyedIP, proxyedPort, socket));
		}
	}
	
	public void stop() {
		this.isRunning = false;
	}
	
	/**
	 * 绑定ServerSocket至端口
	 */
	private ServerSocket bindServerSocket() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			LOG.error("服务器监听端口失败", e);
			server = null;
		}
		
		return server;
	}
	
	public static void main(String[] args) {
		Proxy mysqlProxy = new Proxy(8080, "127.0.0.1", 3306);
		mysqlProxy.start();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(Proxy.class);
}
