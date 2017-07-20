package com.endless.demo.jvm;

public class Test {
	static Test2 t1 = new Test2();
	Test2 t2 = new Test2();
	
	public void test() {
		Test2 t3 = new Test2();
	}
}

class Test2 {}
