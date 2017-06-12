package com.endless.demo.http.api;

import java.util.List;

public class PostDateList extends BaseResponse {
	
	private List<String> results;

	public List<String> getResults() {
		return results;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "PostDateList [results=" + results + "]";
	}
}
