package com.endlessspace.demo.dal.mysql;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.endlessspace.demo.dal.mysql.handler.Handler;
import com.endlessspace.demo.dal.mysql.handler.MysqlProxyHandler;

/**
 * 描述: 一个面向具体连接的代理上下文, 面向独立连接的线程
 * @author wangbo
 */
public class ProxyContext implements Runnable {
	
	// 被代理IP
	private String proxyedIP;
	
	// 被代理端口
	private int proxyedPort;
	
	// 被代理Socket
	private Socket proxyedSocket;
	private InputStream proxyedInputStream;
	private OutputStream proxyedOutputStream;
	
	// 被代理连接Socket
	private Socket clientSocket;
	private InputStream clientInputStream;
	private OutputStream clientOutputStream;
	
	// 代理状态
	private boolean isProxyRunning = true;
	
	// 协议处理器
	private Handler handler;
	
	// 阶段信息
	private int phrase = ProxyConstant.PHRASE_INIT;
	private int nextPhrase = ProxyConstant.PHRASE_INIT;
	
	public ProxyContext(String proxyedIP, int proxyedPort, Socket clientSocket) {
		this.proxyedIP = proxyedIP;
		this.proxyedPort = proxyedPort;
		
		this.clientSocket = clientSocket;
		this.handler = new MysqlProxyHandler();
	}

	@Override
	public void run() {
		try {
			// 客户端网络通信参数
	        this.clientSocket.setPerformancePreferences(0, 2, 1);
	        this.clientSocket.setTcpNoDelay(true);
	        this.clientSocket.setTrafficClass(0x10);
	        this.clientSocket.setKeepAlive(true);
	        
	        clientInputStream = new BufferedInputStream(clientSocket.getInputStream(), 16 * 1024); // 16K
	        clientOutputStream = clientSocket.getOutputStream();
	        
	        // 连接服务器端网络通信参数
			this.proxyedSocket = new Socket(proxyedIP, proxyedPort);
	        this.proxyedSocket.setPerformancePreferences(0, 2, 1);
	        this.proxyedSocket.setTcpNoDelay(true);
	        this.proxyedSocket.setTrafficClass(0x10);
	        this.proxyedSocket.setKeepAlive(true);
	        
	        proxyedInputStream = new BufferedInputStream(proxyedSocket.getInputStream(), 16* 1024); // 16K
	        proxyedOutputStream = proxyedSocket.getOutputStream();
			
		} catch (IOException e) {
			LOG.error("代理连接配置失败", e);
		}
		
		if (this.proxyedSocket == null)
			return;
		
		try {
			// 处理当前阶段, 准备进入下一阶段
			while (isProxyRunning) {
				switch (this.phrase) {
					case ProxyConstant.PHRASE_INIT:
						LOG.info("代理交互开始");
						this.nextPhrase = ProxyConstant.PHRASE_READ_HANDSHAKE;
						break;
						
					case ProxyConstant.PHRASE_READ_HANDSHAKE:
						this.nextPhrase = ProxyConstant.PHRASE_SENG_HANDSHAKE;
						handler.readHandshakeFromServer(this);
						break;
						
					case ProxyConstant.PHRASE_SENG_HANDSHAKE:
						this.nextPhrase = ProxyConstant.PHRASE_READ_AUTHETICATION;
						handler.sendHandshakeToClient(this);
						break;
						
					case ProxyConstant.PHRASE_READ_AUTHETICATION:
						this.nextPhrase = ProxyConstant.PHRASE_SEND_AUTHETICATION;
						handler.readAutheticationFromClient(this);
						break;
						
					case ProxyConstant.PHRASE_SEND_AUTHETICATION:
						this.nextPhrase = ProxyConstant.PHRASE_READ_AUTHETICATION_RESULT;
						handler.sendAutheticationToServer(this);
						break;
						
					case ProxyConstant.PHRASE_READ_AUTHETICATION_RESULT:
						this.nextPhrase = ProxyConstant.PHRASE_SEND_AUTHETICATION_RESULT;
						handler.readAutheticationResultFromServer(this);
						break;
						
					case ProxyConstant.PHRASE_SEND_AUTHETICATION_RESULT:
						this.nextPhrase = ProxyConstant.PHRASE_READ_COMMAND;
						handler.sendAutheticationResultToClient(this);
						break;
						
					case ProxyConstant.PHRASE_READ_COMMAND:
						this.nextPhrase = ProxyConstant.PHRASE_SEND_COMMAND;
						handler.readCommandFromClient(this);
						break;
						
					case ProxyConstant.PHRASE_SEND_COMMAND:
						this.nextPhrase = ProxyConstant.PHRASE_READ_COMMAND_RESULT;
						handler.sendCommandToServer(this);
						break;
						
					case ProxyConstant.PHRASE_READ_COMMAND_RESULT:
						this.nextPhrase = ProxyConstant.PHRASE_SEND_COMMAND_RESULT;
						handler.readCommandResultFromServer(this);
						break;
						
					case ProxyConstant.PHRASE_SEND_COMMAND_RESULT:
						this.nextPhrase = ProxyConstant.PHRASE_READ_COMMAND;
						handler.sendCommandResultToClient(this);
						break;
						
					default:
						isProxyRunning = false; // 退出循环
				}
				this.phrase = this.nextPhrase;
			}
			
		} catch (Exception e) {
			LOG.error("交互失败", e);
		}
	}
	
	public InputStream getProxyedInputStream() {
		return proxyedInputStream;
	}

	public void setProxyedInputStream(InputStream proxyedInputStream) {
		this.proxyedInputStream = proxyedInputStream;
	}

	public OutputStream getProxyedOutputStream() {
		return proxyedOutputStream;
	}

	public void setProxyedOutputStream(OutputStream proxyedOutputStream) {
		this.proxyedOutputStream = proxyedOutputStream;
	}

	public InputStream getClientInputStream() {
		return clientInputStream;
	}

	public void setClientInputStream(InputStream clientInputStream) {
		this.clientInputStream = clientInputStream;
	}

	public OutputStream getClientOutputStream() {
		return clientOutputStream;
	}

	public void setClientOutputStream(OutputStream clientOutputStream) {
		this.clientOutputStream = clientOutputStream;
	}

	private static final Logger LOG = LoggerFactory.getLogger(ProxyContext.class);
}
