package com.endlessspace.demo.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.endlessspace.demo.thrift.service.HelloService;
import com.endlessspace.demo.thrift.service.impl.HelloServiceImpl;

public class HelloServer {
	public static void main(String[] args) {
		try {
			TServerSocket serverTransport = new TServerSocket(9090);
			TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
			
			TProcessor processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
			serverArgs.processor(processor);
			
			TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
			serverArgs.protocolFactory(proFactory);
			
			TServer server = new TThreadPoolServer(serverArgs);
			server.serve();
			
		} catch (TTransportException e) {
			LOG.error("Thrift server 启动失败", e);
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(HelloServer.class);
}
