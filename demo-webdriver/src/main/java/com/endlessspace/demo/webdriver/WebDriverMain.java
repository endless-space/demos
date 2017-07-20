package com.endlessspace.demo.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverMain {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\utils\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.baidu.com");
	}
}
