package com.endlessspace.demo.netty.echo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 描述: 简单返回信息的Handler
 * @author wangbo
 */
public class EchoChannelHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.write(msg); // ctx提供了多种方法触发事件
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.error("处理数据异常", cause);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(EchoChannelHandler.class);
}
