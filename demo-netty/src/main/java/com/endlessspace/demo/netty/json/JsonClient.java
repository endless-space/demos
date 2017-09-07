package com.endlessspace.demo.netty.json;

import com.endlessspace.demo.netty.json.handler.JsonClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class JsonClient {
	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new JsonClientHandler());	
					}
				})
				.option(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture future = bootstrap.connect("127.0.0.1", 8080);
			future.channel().closeFuture().sync();
			
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
