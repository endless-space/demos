package com.endless.demo.jvm;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 描述: 指令重排序
 * @author wangbo
 */
public class Reordering {
	
	Random rand = new Random();
	
	private static int x, y;
	private static int a, b;
	
	public static void main(String[] args) throws InterruptedException {
		
		Set<String> resultSet = new HashSet<String>();
		for (long i = 0L; i < Long.MAX_VALUE; i++) {
			Runnable r1 = new Runnable() {
				@Override
				public void run() {
					x = 1;
					a = y;
				}
			};
			
			Runnable r2 = new Runnable() {
				@Override
				public void run() {
					y = 1;		
					b = x;
				}
			};
			
			Thread t1 = new Thread(r1);
			Thread t2 = new Thread(r2);
			
			t1.start(); t2.start();
			t1.join();  t2.join();
			
			String result = "(" + a + ", " + b + ")";
			if (!resultSet.contains(result)) {
				resultSet.add(result);
				System.out.println("found a new result type: " + result + " in " + i);
			}
			
			if (i != 0 && i % 1000 == 0) {
				System.err.println("exection in " + i);
			}
		}
	}
}
