package com.endless.demo.concurrent;


public class ConcurrentCounting {
	
	public static class Counter {
		private int i;
		
		public Counter() {
			i = 0;
		}
		
		public int increment() {
			return ++i;
		}
		
		public int get() {
			return i;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final Counter counter = new Counter();
		
		Runnable task = new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					counter.increment();
				}
			}
		};
		
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(counter.get()); // join有线程同步的作用, 故get不会用为可见性而出问题
	}
}
