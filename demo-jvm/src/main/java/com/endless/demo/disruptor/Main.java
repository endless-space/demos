package com.endless.demo.disruptor;

import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class Main {
	
	private static final int RING_BUFFER_SIZE = 4096;
	
	public static void main(String[] args) {
		EventFactory<LogEvent> logEventFactory = new LogEventFactory();
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r);
			}
		};
		Disruptor<LogEvent> disruptor = new Disruptor<>(logEventFactory, RING_BUFFER_SIZE, threadFactory);
		disruptor.handleEventsWith(new LogEventHandler());
		disruptor.start();
		
		RingBuffer<LogEvent> eventBuffer = disruptor.getRingBuffer();
		LogEventCenter eventCenter = new LogEventCenter(eventBuffer);
		
		for (int i = 0; i < 1000000000; i++) {
			eventCenter.post(Long.valueOf(i), "event" + i);
		}
	}
}
