package com.endlessspace.demo.dal.mysql.handler;

import java.io.IOException;

import org.apache.commons.io.HexDump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.endlessspace.demo.dal.mysql.ProxyContext;
import com.endlessspace.demo.dal.mysql.packet.Packet;

/**
 * Mysql代理消息处理类
 * @author wangbo
 */
public class MysqlProxyHandler implements Handler {
	
	private byte[] packet;

	@Override
	public void readHandshakeFromServer(ProxyContext ctx) throws IOException {
		packet = Packet.readPacket(ctx.getProxyedInputStream());
		
		LOG.info("握手数据包: ");
		HexDump.dump(packet, 0, System.out, 0);
	}

	@Override
	public void sendHandshakeToClient(ProxyContext ctx) throws IOException {
		Packet.write(ctx.getClientOutputStream(), packet);
	}

	@Override
	public void readAutheticationFromClient(ProxyContext ctx) throws IOException {
		packet = Packet.readPacket(ctx.getClientInputStream());
		
		LOG.info("认证数据包: ");
		HexDump.dump(packet, 0, System.out, 0);
	}

	@Override
	public void sendAutheticationToServer(ProxyContext ctx) throws IOException {
		Packet.write(ctx.getProxyedOutputStream(), packet);
	}

	@Override
	public void readAutheticationResultFromServer(ProxyContext ctx) throws IOException {
		packet = Packet.readPacket(ctx.getProxyedInputStream());
		
		LOG.info("认证结果数据包");
		HexDump.dump(packet, 0, System.out, 0);
	}

	@Override
	public void sendAutheticationResultToClient(ProxyContext ctx) throws IOException {
		Packet.write(ctx.getClientOutputStream(), packet);
	}

	@Override
	public void readCommandFromClient(ProxyContext ctx) throws IOException {
		packet = Packet.readPacket(ctx.getClientInputStream());
		
		LOG.info("命令执行数据包");
		HexDump.dump(packet, 0, System.out, 0);
	}

	@Override
	public void sendCommandToServer(ProxyContext ctx) throws IOException {
		Packet.write(ctx.getProxyedOutputStream(), packet);
	}

	@Override
	public void readCommandResultFromServer(ProxyContext ctx) throws IOException {
		packet = Packet.readPacket(ctx.getProxyedInputStream());
		
		LOG.info("命令执行结果数据包");
		HexDump.dump(packet, 0, System.out, 0);
	}

	@Override
	public void sendCommandResultToClient(ProxyContext ctx) throws IOException {
		Packet.write(ctx.getClientOutputStream(), packet);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(MysqlProxyHandler.class);
}
