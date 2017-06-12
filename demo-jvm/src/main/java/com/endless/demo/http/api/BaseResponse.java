package com.endless.demo.http.api;

public class BaseResponse {
	
	private boolean error;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "BaseResponse [error=" + error + "]";
	}
}
