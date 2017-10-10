package com.endless.demo.jvm;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadPool {
	
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 50, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build(), new AbortPolicy());
		threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		
		threadPool.wait();
	}
}
