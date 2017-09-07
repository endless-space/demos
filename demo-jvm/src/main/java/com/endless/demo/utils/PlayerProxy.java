package com.endless.demo.utils;

public class PlayerProxy implements Player {
	
	private Player proxyed;
	
	public PlayerProxy(Player proxyed) {
		this.proxyed = proxyed;
	}

	@Override
	public void eat() {
		System.out.println("代理 吃饭");
		proxyed.eat();
	}

	@Override
	public void sleep() {
		System.out.println("代理 睡觉");
		proxyed.sleep();
	}

	@Override
	public void play() {
		System.out.println("代理 玩");
		proxyed.play();
	}
}
