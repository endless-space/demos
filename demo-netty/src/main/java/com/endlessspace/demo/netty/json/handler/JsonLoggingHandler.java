package com.endlessspace.demo.netty.json.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class JsonLoggingHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String json = (String)msg;
		LOG.info("接收到数据: {}", json);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.error("异常行为", cause);
	}

	private static final Logger LOG = LoggerFactory.getLogger(JsonLoggingHandler.class);
}
