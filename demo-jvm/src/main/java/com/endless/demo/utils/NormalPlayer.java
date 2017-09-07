package com.endless.demo.utils;

public class NormalPlayer implements Player {
	
	private String name;
	
	public NormalPlayer(String name) {
		this.name = name;
	}

	@Override
	public void eat() {
		System.out.println("玩家 [" + name + "] 正在吃饭");
	}

	@Override
	public void sleep() {
		System.out.println("玩家 [" + name + "] 正在睡觉");
	}

	@Override
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
