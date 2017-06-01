package com.endlessspace.demo.dal.mysql;

/**
 * 描述: 代理服务常量
 * @author wangbo
 */
public class ProxyConstant {
	
	// 交互阶段
	public static final int PHRASE_INIT = 0;
	public static final int PHRASE_READ_HANDSHAKE = 1;
	public static final int PHRASE_SENG_HANDSHAKE = 2;
	public static final int PHRASE_READ_AUTHETICATION = 3;
	public static final int PHRASE_SEND_AUTHETICATION = 4;
	public static final int PHRASE_READ_AUTHETICATION_RESULT = 5;
	public static final int PHRASE_SEND_AUTHETICATION_RESULT = 6;
	public static final int PHRASE_READ_COMMAND = 7;
	public static final int PHRASE_SEND_COMMAND = 8;
	public static final int PHRASE_READ_COMMAND_RESULT = 9;
	public static final int PHRASE_SEND_COMMAND_RESULT = 10;
	
	// 命令类型 
}
