package com.endlessspace.demo.netty.time.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		try {
			long time = (buf.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(new Date(time));
			ctx.close();
			
		} finally {
			buf.release();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.error("exception happen", cause);
		ctx.close();
	}
 
	private static final Logger LOG = LoggerFactory.getLogger(TimeClientHandler.class);
}
