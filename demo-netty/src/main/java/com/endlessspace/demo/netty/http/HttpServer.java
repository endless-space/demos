package com.endlessspace.demo.netty.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;

/**
 * 描述: 基于Netty开发的http服务器 
 * @author wangbo
 */
public class HttpServer {

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
					protected void initChannel(SocketChannel channel) throws Exception {
						channel.pipeline()
							.addLast(new HttpServerCodec())
							.addLast(new HttpObjectAggregator(1024))
							.addLast(new HttpLoggingHandler());
					}
				});
			ChannelFuture future = bootstrap.bind(8080).sync();
			LOG.info("服务器运行在 {} 端口", 8080);
			
			future.channel().closeFuture().sync();
			
		} finally {
			masterGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(HttpServer.class);
}
