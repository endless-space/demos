package com.endlessspace.demo.net;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: 基于JavaNIO的网络服务器
 * @author wangbo
 */
public class NioServer {
	
	private int port = 8080;
	
	private Selector selector;
	
	/**
	 * 通过监听端口创建NioServer
	 */
	public NioServer(int port) {
		this.port = port;
	}
	
	/**
	 * 启动NioServer
	 */
	public void start() {
		try {
			selector = Selector.open();
			
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			while (true) {
				int readyChannel = selector.select();
				if (readyChannel == 0) continue;
				LOG.info("发生 {} 个事件 ", readyChannel);
				
				Set<SelectionKey> keys = selector.selectedKeys();
				for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext();) {
					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						LOG.info("连接进入...");
					}
					it.remove();
				}
				LOG.info("回看keys: {}", keys);
			}
			
		} catch (Exception e) {
			LOG.info("获得Selector失败", e);
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(NioServer.class);
	
	public static void main(String[] args) {
		NioServer server = new NioServer(7070);
		server.start();
	}
}
