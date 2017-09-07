package com.endless.demo.disruptor;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * 描述: 事件的发布中心
 * @author wangbo
 */
public class LogEventCenter {
	
	private static EventTranslatorVararg<LogEvent> translator = new EventTranslatorVararg<LogEvent>() {
		
		@Override
		public void translateTo(LogEvent event, long sequence, Object... args) {
			event.setId((Long)args[0]);
			event.setContent((String)args[1]);
		}
	};
	
	private RingBuffer<LogEvent> eventBuffer;
	
	public LogEventCenter(RingBuffer<LogEvent> eventBuffer) {
		this.eventBuffer = eventBuffer;
	}
	
	public void post(Long id, String content) {
		eventBuffer.publishEvent(translator, id, content);
	}
}
