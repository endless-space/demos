package com.endlessspace.demo.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
	
	public static void main(String[] args) {
		NioServer server = new NioServer(7070);
		server.start();
	}
	
	/**
	 * 启动NioServer
	 */
	public void start() {
		try {
			// 核心组件Selector
			selector = Selector.open();
			
			// 核心组件ServerSocketChannel
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			
			// 建立ServerSocketChannel与Selector的关联
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			while (true) {
				// 从Selctor中阻塞获得事件
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				
				// 迭代处理获得的事件
				for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext();) {
					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						LOG.info("收到连接");
						SocketChannel socketChannel = serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					}
					if (key.isReadable()) {
						LOG.info("收到读数据");
						SocketChannel socketChannel = (SocketChannel)key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						int receiveNum = socketChannel.read(buffer);
						buffer.flip();
						if (buffer.hasRemaining()) {
							System.out.print((char)buffer.get());
						}
						System.out.println();
					}
					
					it.remove();
				}
			}
			
		} catch (Exception e) {
			LOG.info("NIO SERVER EXCEPTION", e);
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(NioServer.class);
}
