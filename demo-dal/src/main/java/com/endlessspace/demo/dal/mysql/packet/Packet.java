package com.endlessspace.demo.dal.mysql.packet;

import java.util.Arrays;

/**
 * 描述: mysql 协议数据包
 * @author wangbo
 */
public abstract class Packet {
	
	private byte[] packetBytes;
	
	private int type;

	public int getType() {
		return type;
	}

	public Packet setType(int type) {
		this.type = type;
		return this;
	}

	/**
	 * 从字节数据中获取
	 */
	protected abstract Packet loadFromContent(byte[] content);
	
	public byte[] getPacketBytes() {
		return packetBytes;
	}
	
	public byte[] getContentBytes() {
		return Arrays.copyOfRange(packetBytes, 4, packetBytes.length);
	}

	public Packet setPacket(byte[] packetBytes) {
		if (packetBytes == null) {
			this.packetBytes = new byte[0];
			return this;
		}
		
		this.packetBytes = packetBytes;
		loadFromContent(getContentBytes());
		return this;
	}
	
	public String toHex() {
		return "";
	}
}
