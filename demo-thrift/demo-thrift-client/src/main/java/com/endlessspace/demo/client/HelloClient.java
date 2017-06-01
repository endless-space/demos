package com.endlessspace.demo.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.endlessspace.demo.thrift.service.HelloService;

public class HelloClient {
	public static void main(String[] args) {
		try {
			TTransport transport = new TSocket("127.0.0.1", 9090);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);

			HelloService.Client client = new HelloService.Client(protocol);

			String name = "endlessspace";
			System.out.println("请求参数==>name为" + name);
			String result = client.sayHello(name);
			System.out.println("返回结果==>为" + result);
			transport.close();
			
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
