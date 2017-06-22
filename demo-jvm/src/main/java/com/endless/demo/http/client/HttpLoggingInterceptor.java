package com.endless.demo.http.client;

import java.io.IOException;

import org.slf4j.Logger;

import com.google.common.base.Strings;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

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
		
		long startTime = System.nanoTime();
		log.info(formatRequestInfo(request));

		Response response = chain.proceed(request); // http invoke here

		long endTime = System.nanoTime();
		ResponseBody responseBody = response.body();
		MediaType contentType = responseBody.contentType();
		String bodyString = responseBody.string();
		log.info(formatResponseInfo(response, startTime, endTime, bodyString));

		return response.newBuilder().body(ResponseBody.create(contentType, bodyString)).build();
	}
	
	private String formatRequestInfo(Request request) throws IOException {
		StringBuilder requestInfoBuilder = new StringBuilder();
		
		Request copy = request.newBuilder().build();
		Buffer  buf  = new Buffer();
		if (copy.body() != null) {
			copy.body().writeTo(buf);
		}
		String requestBody = buf.readUtf8();
		
		requestInfoBuilder.append("REQUEST  ");
		requestInfoBuilder.append(request.url());
		if (!Strings.isNullOrEmpty(requestBody)) {
			requestInfoBuilder.append(" with:\n");
			requestInfoBuilder.append(requestBody);
		}
		
		return requestInfoBuilder.toString();
	}

	private String formatResponseInfo(Response response, long startTime, long endTime, String bodyString) throws IOException {
		StringBuilder responseInfoBuilder = new StringBuilder();
		
		responseInfoBuilder.append("RESPONSE ");
		responseInfoBuilder.append(response.request().url());
		responseInfoBuilder.append(" in ");
		responseInfoBuilder.append((endTime - startTime) / 1000000.0d);
		responseInfoBuilder.append("ms");
		
		if (!Strings.isNullOrEmpty(bodyString)) {
			responseInfoBuilder.append(" with:\n");
			responseInfoBuilder.append(bodyString);
		}
		
		return responseInfoBuilder.toString();
	}
}
