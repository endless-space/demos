package com.endless.demo.utils;

import com.google.common.base.Strings;

/**
 * 描述: Xss Filter和此类似
 */
public class EmojiFilter {
	
	/**
     * emoji表情替换
     *
     * @param source 原字符串
     * @param slipStr emoji表情替换成的字符串                
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source,String slipStr) {        
        return Strings.isNullOrEmpty(source) ? "" : source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
    }
}
