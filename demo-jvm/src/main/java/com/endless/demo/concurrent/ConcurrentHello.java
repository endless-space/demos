package com.endless.demo.concurrent;

/**
 * 描述: HelloWorld并发版
 * 
 * Thread类
 * Runnable接口
 */
public class ConcurrentHello {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				Thread.yield();
				System.out.println("hello from t thread");
			}
		};
		t.start();
		t.setName("t");
		
		Runnable task = new Runnable() {

			@Override
			public void run() {
				System.out.println("hello from task Thread");
			}
		};
		Thread t1 = new Thread(task);
		t1.start();
		
		Thread.sleep(1);
		System.out.println("hello from main thread");
		t.join();
	}
}
