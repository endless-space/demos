package com.endlessspace.demo.dal.mysql.handler;

import java.io.IOException;

import com.endlessspace.demo.dal.mysql.ProxyContext;

/**
 * 描述: Mysql代理基本接口
 * @author wangbo
 */
public interface Handler {

	// 握手认证阶段
	public void readHandshakeFromServer(ProxyContext ctx) throws IOException;
	
	public void sendHandshakeToClient(ProxyContext ctx) throws IOException;
	
	public void readAutheticationFromClient(ProxyContext ctx) throws IOException;
	
	public void sendAutheticationToServer(ProxyContext ctx) throws IOException;
	
	public void readAutheticationResultFromServer(ProxyContext ctx) throws IOException;
	
	public void sendAutheticationResultToClient(ProxyContext ctx) throws IOException;
	
	// 命令执行阶段
	public void readCommandFromClient(ProxyContext ctx) throws IOException;
	
	public void sendCommandToServer(ProxyContext ctx) throws IOException;
	
	public void readCommandResultFromServer(ProxyContext ctx) throws IOException;
	
	public void sendCommandResultToClient(ProxyContext ctx) throws IOException;
}
