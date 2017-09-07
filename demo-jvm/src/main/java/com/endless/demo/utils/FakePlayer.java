package com.endless.demo.utils;

public class FakePlayer {
	
	private String name;
	
	public void eat() {
		System.out.println("玩家 [" + name + "] 正在吃饭");
	}

	public void sleep() {
		System.out.println("玩家 [" + name + "] 正在睡觉");
	}

	public void play() {
		System.out.println("玩家 [" + name + "] 正在吃饭");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
