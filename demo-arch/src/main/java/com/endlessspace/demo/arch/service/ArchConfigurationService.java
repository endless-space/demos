package com.endlessspace.demo.arch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ArchConfigurationService {
	
	@Value("#{application.id}")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
