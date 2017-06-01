package com.endlessspace.demo.dal.mysql.packet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 描述: mysql 协议数据包
 * @author wangbo
 */
public class Packet {

	/**
	 * 读取一个数据包
	 */
	public static byte[] readPacket(InputStream in) throws IOException {
		byte[] header = new byte[4];
		fillByteArray(in, header);
		
		ByteBuffer lenBuffer = ByteBuffer.allocate(4);
		lenBuffer.order(ByteOrder.LITTLE_ENDIAN);
		lenBuffer.put(header[0]);
		lenBuffer.put(header[1]);
		lenBuffer.put(header[2]);
		lenBuffer.put((byte) 0x0);
		
		lenBuffer.flip();
		int bodyLen = lenBuffer.getInt();
		
		byte[] body = new byte[bodyLen];
		fillByteArray(in, body);
		
		byte[] packet = new byte[4 + bodyLen];
		System.arraycopy(header, 0, packet, 0, 4);
		System.arraycopy(body, 0, packet, 4, bodyLen);
		
		return packet;
	}
	
	/**
	 * 从字节流中读满数组
	 */
	private static void fillByteArray(InputStream in, byte[] arr) throws IOException {
		if (arr == null)
			return;
		
        int offset = 0;
        int target = arr.length;
        do {
            int len = in.read(arr, offset, (target - offset));
            if (len == -1) {
                throw new IOException();
            }
            offset += len;
        } while (offset != target);
	}
	
	/**
	 * 写入数据包
	 */
	public static void write(OutputStream out, byte[] data) throws IOException {
		if (data != null)
			out.write(data);
	}
}
