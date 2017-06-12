package com.endless.demo.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.endless.demo.http.client.HttpClientSingleton;
import com.google.common.base.Strings;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 描述: http工具类
 */
public class Http {
	private static final String LOGGER_NAME = "BIC-HTTP";
	
	/**
	 * get 无参
	 */
	public static String get(String url) {
		return get(url, new HashMap<String, String>(0));
	}
	
	/**
	 * get 有参
	 */
	public static String get(String url, Map<String, String> params) {
		OkHttpClient client = HttpClientSingleton.getClient();
		
		String queryString = buildQueryString(params);
		if (!Strings.isNullOrEmpty(queryString)) {
			url = url + "?" + queryString;
		}
			
		Request request = new Request.Builder()
			.get()
			.url(url)
			.addHeader("cache-control", "no-cache")
			.build();

		String retStr = "";
		try {
			Response response = client.newCall(request).execute();
			retStr = response.body().string();
			
		} catch (Exception e) {
			LOG.error("请求异常", e);
			retStr = "";
		}
		
		return retStr;
	}
	
	/**
	 * post 方法
	 */
	public static String post(String url) {
		return postForm(url, new HashMap<String, String>(0));
	}
	
	public static String postForm(String url, Map<String, String> params) {
		OkHttpClient client = HttpClientSingleton.getClient();
		
		FormBody.Builder formBodyBuilder = new FormBody.Builder();
		if (params != null && params.size() != 0) {
			for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				String val = params.get(key);
				
				formBodyBuilder.add(key, val);
			}
		}
		
		Request request = new Request.Builder().post(formBodyBuilder.build()).url(url)
				.addHeader("cache-control", "no-cache")
				.build();
		
		String retStr = "";
		try {
			Response response = client.newCall(request).execute();
			retStr = response.body().string();
			
		} catch (Exception e) {
			LOG.error("请求异常");
			retStr = "";
		}
		
		return retStr;
	}
	
	public static String postJSON(String url, Object param) {
		OkHttpClient client = HttpClientSingleton.getClient();
		
		String jsonParam = "";
		if (param != null) {
			jsonParam = JSON.toJSONString(param);
		}

		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParam);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		
		String retStr = "";
		try {
			Response response = client.newCall(request).execute();
			retStr = response.body().string();
			
		} catch (Exception e) {
			LOG.error("请求出错", e);
			retStr = "";
		}
		
		return retStr;
	}
	
	public static String buildQueryString(Map<String, String> params) {
		if (params == null || params.size() == 0) {
			return "";
		}
		
		StringBuilder queryStringBuilder = new StringBuilder();
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			String val = params.get(key);
			
			if (it.hasNext()) {
				queryStringBuilder.append(key);
				queryStringBuilder.append("=");
				queryStringBuilder.append(val);
				queryStringBuilder.append("&");
			} else {
				queryStringBuilder.append(key);
				queryStringBuilder.append("=");
				queryStringBuilder.append(val);
			}
		}
		
		return queryStringBuilder.toString();
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(LOGGER_NAME);
}
