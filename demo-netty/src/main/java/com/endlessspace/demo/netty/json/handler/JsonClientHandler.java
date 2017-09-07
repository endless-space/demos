
package com.endlessspace.demo.netty.json.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class JsonClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 30; i++) {
			String json = "{\"test\":" + i + "}";
			ByteBuf buff = ctx.alloc().buffer(4 + json.length());
			buff.writeInt(json.length());
			buff.writeBytes(json.getBytes("utf-8"));
			ctx.write(buff);
			ctx.flush();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.error("发生异常情况", cause);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonClientHandler.class);
}
