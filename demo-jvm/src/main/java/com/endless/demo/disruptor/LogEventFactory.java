package com.endless.demo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 描述: 事件工厂, 用于创建空的事件对象
 * @author wangbo
 */
public class LogEventFactory implements EventFactory<LogEvent> {

	@Override
	public LogEvent newInstance() {
		return new LogEvent();
	}
}
