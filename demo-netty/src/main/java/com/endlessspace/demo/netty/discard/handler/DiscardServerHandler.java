package com.endlessspace.demo.netty.discard.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 描述: Handler - netty的基本组件之一
 * @author wangbo
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ((ByteBuf)msg).release(); // 丢弃数据
		
		ByteBuf in = (ByteBuf)msg;
		try {
			if (in.isReadable()) {
				System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
			}
			
		} finally {
			ReferenceCountUtil.release(in);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.error("Handler异常", cause);
		ctx.close();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(DiscardServerHandler.class);
}
