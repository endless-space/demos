package com.endlessspace.demo.spring.beans;

/**
 * 描述: 用户信息服务
 * @author wangbo
 */
public interface UserService {
	
	/**
	 * 描述: 获得用户别名
	 */
	public String getUserNickname(String userUuid);
	
	/**
	 * 描述: 获得用户年龄信息
	 */
	public int getUserAge(String userUuid);
}
