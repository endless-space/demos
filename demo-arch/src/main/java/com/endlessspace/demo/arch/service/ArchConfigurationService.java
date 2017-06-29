package com.endlessspace.demo.arch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 描述: 应用配置服务
 * @author wangbo
 */
@Service
public class ArchConfigurationService {
	
	@Value("${app.name}")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
