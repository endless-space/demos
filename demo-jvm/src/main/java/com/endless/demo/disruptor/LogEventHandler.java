package com.endless.demo.disruptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventHandler;

/**
 * 描述: 事件处理器
 * @author wangbo
 */
public class LogEventHandler implements EventHandler<LogEvent> {

	@Override
	public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
		LOG.info("{} - {} - {}", sequence, event, endOfBatch);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(LogEventHandler.class);
}
