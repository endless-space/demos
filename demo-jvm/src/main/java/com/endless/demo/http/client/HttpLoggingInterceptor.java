package com.endless.demo.http.client;

import java.io.IOException;

import org.slf4j.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述: 应用级拦截器
 */
public class HttpLoggingInterceptor implements Interceptor {
	
	private Logger log;
	
	public HttpLoggingInterceptor(Logger log) {
		this.log = log;
	}
	
	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		Request request = chain.request();

		long t1 = System.nanoTime();
		log.info("REQUEST  {}", request.url());

		Response response = chain.proceed(request);

		long t2 = System.nanoTime();
		log.info("RESPONSE {} in {}ms", response.request().url(), (t2 - t1) / 1e6d);

		return response;
	}
}
