package com.endlessspace.demo.spring.beans;

public class UserServiceImpl implements UserService {

	@Override
	public String getUserNickname(String userUuid) {
		return "dva";
	}

	@Override
	public int getUserAge(String userUuid) {
		return 27;
	}
}
