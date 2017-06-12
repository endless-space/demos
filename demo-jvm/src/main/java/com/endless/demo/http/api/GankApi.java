package com.endless.demo.http.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.endless.demo.http.Http;

public class GankApi {
	
	public PostDateList getGankDateList() {
		String res = Http.get("http://gank.io/api/day/history");
		return JSON.parseObject(res, new TypeReference<PostDateList>() {});
	}
	
	public static void main(String[] args) {
		GankApi api = new GankApi();
		PostDateList list = api.getGankDateList();
		LOG.info("{}", list);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(GankApi.class);
}
