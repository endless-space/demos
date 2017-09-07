package com.endless.demo.disruptor;

/**
 * 描述: 基本的Event
 * 
 * @author wangbo
 */
public class LogEvent {

	private long id;
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "LogEvent [id=" + id + ", content=" + content + "]";
	}
}
