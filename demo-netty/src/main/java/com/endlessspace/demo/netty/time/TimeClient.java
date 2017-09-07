package com.endlessspace.demo.netty.time;

import com.endlessspace.demo.netty.time.handler.TimeClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup masterGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(masterGroup)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new TimeClientHandler());
					}
				})
				.option(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture future = bootstrap.connect("127.0.0.1", 8080);
			future.channel().closeFuture().sync();
			
		} finally {
			masterGroup.shutdownGracefully();
		}
	}
}
