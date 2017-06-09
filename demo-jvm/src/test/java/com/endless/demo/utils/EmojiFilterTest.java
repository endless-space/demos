package com.endless.demo.utils;

import org.junit.Test;

import com.vdurmont.emoji.EmojiParser;

public class EmojiFilterTest {
	
	@Test
	public void testEmojiFilter() {
		String str = "An awesome string with a few emojis! \uD83D\uDC66\uD83C\uDFFF!";
		str = EmojiParser.removeAllEmojis(str);
		System.out.println(str);
	}
}
