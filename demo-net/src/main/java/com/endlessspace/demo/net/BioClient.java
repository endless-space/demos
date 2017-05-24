package com.endlessspace.demo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: 阻塞连接
 * @author wangbo
 */
public class BioClient {
	
	private String ip;
	
	private int port;
	
	public BioClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void connect() {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(BioClient.class);
	
	public static void main(String[] args) {
		BioClient client = new BioClient("127.0.0.1", 7070);
		client.connect();
	}
}
