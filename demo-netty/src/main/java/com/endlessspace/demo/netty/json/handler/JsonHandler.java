package com.endlessspace.demo.netty.json.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class JsonHandler extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4)
			return;
		
		in.markReaderIndex();
		int len = in.readInt();
		if (in.readableBytes() < len) {
			in.resetReaderIndex();
			return;
		}

		byte[] bytes = new byte[len];
		in.readBytes(bytes);
		String message = new String(bytes, "utf-8");
		out.add(message);
	}
}
