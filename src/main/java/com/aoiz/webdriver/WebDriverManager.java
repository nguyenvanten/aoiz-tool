package com.aoiz.webdriver;

public class WebDriverManager {
	public static void main(String[] args) {

		Thread threadStart1 = new ThreadStart(1);
		threadStart1.start();
		Thread threadStart2 = new ThreadStart(2);
		threadStart2.start();
		Thread threadStart3 = new ThreadStart(3);
		threadStart3.start();
		Thread threadStart4 = new ThreadStart(4);
		threadStart4.start();
		Thread threadStart5 = new ThreadStart(5);
		threadStart5.start();
	}
}
