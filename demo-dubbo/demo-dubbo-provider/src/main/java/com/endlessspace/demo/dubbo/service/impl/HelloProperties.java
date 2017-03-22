package com.endlessspace.demo.dubbo.service.impl;

public class HelloProperties {
	
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "HelloProperties [name=" + name + ", age=" + age + "]";
	}
}
