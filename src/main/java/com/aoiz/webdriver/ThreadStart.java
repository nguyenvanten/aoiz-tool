package com.aoiz.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ThreadStart extends Thread {
	private int index;

	public ThreadStart(int index) {
		this.index = index;
	}

	@Override
	public void run() {
		System.setProperty("webdriver.chrome.driver", String.format("E:\\TenNguyen\\chromedriver%d.exe", index));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get("https://aioz.tube");
		try {
			Thread.sleep(3000);
			VideoRunner.runVideo(driver, index + 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
