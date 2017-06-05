package com.endlessspace.demo.spring.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

public class PropertyPlaceholderHelperCase {
	
	public static void main(String[] args) {
		String tmpl = "this is ${name}";
		
		final Map<String, String> values = new HashMap<>();
		values.put("name", "wangbo");
		
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
		String content = helper.replacePlaceholders(tmpl, new PlaceholderResolver() {

			@Override
			public String resolvePlaceholder(String placeholderName) {
				return values.get(placeholderName);
			}
		});
		
		System.out.println(content);
	}
}
