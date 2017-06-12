package com.endless.demo.http.client;

import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;

/**
 * 描述: 全局OkHttpClient
 */
public class HttpClientSingleton {
	private static final String LOGGER_NAME = "BIC-HTTP-CLIENT";
	
	private HttpClientSingleton() {}

	private static class HttpClientHolder {
		private static final OkHttpClient client = buildClient();

		private static OkHttpClient buildClient() {
			Logger log = LoggerFactory.getLogger(LOGGER_NAME);
			X509TrustManager trustManager = buildTrustManager();

			return new OkHttpClient.Builder()
				.sslSocketFactory(buildSSLSocketFactory(trustManager), trustManager)
				.hostnameVerifier(buildHostnameVerifier())
				.addInterceptor(new HttpLoggingInterceptor(log))
				.build();
		}
		
		private static X509TrustManager buildTrustManager() {
			return new HttpsAllTrustManager();
		}
		
		private static SSLSocketFactory buildSSLSocketFactory(TrustManager trustManager) {
			SSLSocketFactory sslSocketFactory = null;
			try {
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());
				sslSocketFactory = sslContext.getSocketFactory();
				
			} catch (Exception ignore) {
			}
			return sslSocketFactory;
		}
		
		private static HostnameVerifier buildHostnameVerifier() {
			return new HttpsNoHostnameVerifier();
		}
	}

	public static OkHttpClient getClient() {
		return HttpClientHolder.client;
	}
}
