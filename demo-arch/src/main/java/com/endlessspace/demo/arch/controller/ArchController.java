package com.endlessspace.demo.arch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.endlessspace.demo.arch.service.ArchConfigurationService;

@RestController
@RequestMapping("/arch")
public class ArchController {
	
	@Autowired
	private ArchConfigurationService  config;
	
	@RequestMapping("/desc")
	public Map<String, Object> onGetDesc() {
		Map<String, Object> ret = new HashMap<>();
		ret.put("code", 0);
		ret.put("info", "success");
		ret.put("appId", config.getAppId());
		
		return ret;
	}
}
