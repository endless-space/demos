package com.endlessspace.demo.netty.json;

import com.endlessspace.demo.netty.json.handler.JsonHandler;
import com.endlessspace.demo.netty.json.handler.JsonLoggingHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 描述: 使用json作为序列化方式的服务器
 * @author wangbo
 */
public class JsonServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup masterGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(masterGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler())
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new JsonHandler())
							.addLast(new JsonLoggingHandler());
					}
				})
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.bind(8080).sync();
			future.channel().closeFuture().sync();
			
		} finally {
			masterGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
