package com.endlessspace.demo.net;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: NioClient
 * @author wangbo
 */
public class NioClient {
	
	private String ip;
	
	private int port;
	
	private Selector selector;
	
	public NioClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	} 
	
	public void connect() {
		try {
			selector = Selector.open();
			
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			
			socketChannel.connect(new InetSocketAddress(ip, port));
			
			while(true) {
				int readyChannelNum = selector.select();
				if (readyChannelNum == 0)
					connect();
				
				Set<SelectionKey> keys = selector.selectedKeys();
				for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext();) {
					SelectionKey key = it.next();
					it.remove();
					
					if (key.isConnectable()) {
						LOG.info("连接至服务器");
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(NioClient.class);
	
	public static void main(String[] args) {
		NioClient client = new NioClient("127.0.0.1", 7070);
		client.connect();
	}
}
