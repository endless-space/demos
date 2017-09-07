package com.endlessspace.demo.netty.discard;

import com.endlessspace.demo.netty.discard.handler.DiscardServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 描述: 接收数据, 丢弃数据
 * @author wangbo
 */
public class DiscardServer {
	
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 基本构件 - EventLoopGroup - 线程池
		 */
		EventLoopGroup masterGroup = new NioEventLoopGroup(); // 用于接收进来的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup(); // 用于处理已经进来的连接
		try {
			/**
			 * 基本构件 - 启动引导辅助类
			 */
			ServerBootstrap bootstrap = new ServerBootstrap(); 
			bootstrap.group(masterGroup, workerGroup)
				.channel(NioServerSocketChannel.class)  // 基本构件: 服务器SocketChannel
				.childHandler(new ChannelInitializer<SocketChannel>() {  // 基本构件: 客户端SocketChannel
					
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new DiscardServerHandler());
					}
				})
				.option(ChannelOption.SO_BACKLOG, 128)  // 
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.bind(8080).sync(); // 监听端口
			future.channel().closeFuture().sync(); // 等待ServerSocket结束
 			
		} finally {
			masterGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
