package com.endlessspace.demo.net;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: Nio 类, 接口测试
 */
public class NioTest {
	
	/**
	 * 描述: Channel接口, 表示一个(硬件, 文件, 网络, etc)的开放连接
	 * 	数据可以从channel读入buffer
	 * 	数据可以从buffer写入channel
	 * 常见实现有:
	 * 	FileChannel
	 * 	DatagramChannel
	 * 	SocketChannel
	 *  ServerSocketChannel, 监听TCP连接, 针对每个进来的连接创建一个SocketChannel
	 */
	@Test
	public void testChannel() throws Exception {
		RandomAccessFile dockerManual = new RandomAccessFile("e:\\docker.txt", "rw");
		FileChannel fileChannel = dockerManual.getChannel();
		
		/**
		 * 以下代码阐述了Channel和Buffer的使用模式
		 */
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		int writeBufferLen = fileChannel.read(byteBuffer);
		while (writeBufferLen != -1) {
			byteBuffer.flip(); // 翻转使用模式
			while (byteBuffer.hasRemaining()) {
				LOG.info("{}", (char)byteBuffer.get());
			}
			byteBuffer.clear();
			writeBufferLen = fileChannel.read(byteBuffer);
		}
		
		dockerManual.close();
	}
	
	/**
	 * 描述: Buffer接口
	 *	基本数据类型中除了boolean都有对应的Buffer
	 *  MappedByteBuffer 表示内存映射文件
	 *  
	 * buffer本质是一块内存区域, 提供了一组便利方法去访问, 有3个核心属性
	 * 	capacity (Buffer的大小), position, limit
	 * 读写模型决定了position和limit的具体含义
	 * 	写模式下, position从0开始, limit和capacity等价
	 * 	读模式下, position从0开始, limit表示上次写入的可读内容
	 */
	@Test 
	public void testBuffer() throws Exception {
		RandomAccessFile file = new RandomAccessFile("e:\\docker.txt", "r");
		FileChannel channel = file.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int bufferWriteLen = channel.read(buffer);
		while (bufferWriteLen != -1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				LOG.info("{}", (char) buffer.get());
			}
			buffer.clear();
			bufferWriteLen = channel.read(buffer);
		}
		
		file.close();
	}
	
	/**
	 * 描述: Selector接口
	 * 	Selector可以在单线程下管理多个Channel, 其select方法将阻塞至Channel有事件发生
	 *  channel在向selector注册的时候提供关注事件集合(SelectionKey 提供了4种)
	 */
	@Test 
	public void testSelector() {
		
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(NioTest.class);
}
