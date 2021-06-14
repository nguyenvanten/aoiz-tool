package com.aoiz.sml;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aoiz.webdriver.VideoRunner;

public class SMLTool {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get("https://bc.game/limbo");
		Thread.sleep(10000);

		WebElement userName = VideoRunner.getWebElement(driver,
				By.xpath("//*[@id='login']/div[1]/div[1]/div[2]/input"));
		userName.sendKeys("tenitbinance@gmail.com");
		Thread.sleep(3000);
		WebElement passWord = VideoRunner.getWebElement(driver,
				By.xpath("//*[@id='login']/div[1]/div[2]/div[2]/input"));
		passWord.sendKeys("Texx#321");
		Thread.sleep(3000);
		WebElement login = VideoRunner.getWebElement(driver, By.xpath("//*[@id='login']/div[2]/button[1]/div"));
		login.click();
		Thread.sleep(10000);

		WebElement defaultMoney = VideoRunner.getWebElement(driver,
				By.xpath("//*[@id='Limbo-control-0']/div[2]/div/div[2]/div[2]/input"));
		selectAll(defaultMoney);
		Thread.sleep(1000);
		double defaultValue = 2.80;
		defaultMoney.sendKeys(defaultValue + "");
		int index = 0;
		while (true) {
			WebElement realResultValue = VideoRunner.getWebElement(driver,
					By.xpath("//*[@id='game-Limbo']/div[1]/div/div[2]/div[2]/div[3]/div/div[1]/span[1]"));
			double realValueDb = Double.valueOf(realResultValue.getText());
			System.out.println("Value of realValueDb ================ " + realValueDb);
			Thread.sleep(200);
			if (realValueDb <= defaultValue) {
				index++;
				System.out.println("realValueDb <= defaultValue ================ index = " + index);
			} else {
				System.out.println("Reset index");
				index = 0;
			}
			if (index >= 3) {
				getCoin(driver);
				Thread.sleep(1000);
				index = 0;
			}
			WebElement betButton = VideoRunner.getWebElement(driver,
					By.xpath("//*[@id='Limbo-control-0']/div[2]/div/button"));
			betButton.click();
			Thread.sleep(500);
		}
	}

	private static void resetMinimum(WebElement value) {
		System.out.println("Reset value");
		value.sendKeys("0.0001");
	}

	private static void selectAll(WebElement element) {
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		element.sendKeys(selectAll);
	}

	private static void getCoin(WebDriver driver) throws InterruptedException {
		double maxValue = 27.00;
		double innitValue = 0.0128;
		double defaultValue = 2.80;
		while (innitValue < maxValue) {
			WebElement realResultValue = VideoRunner.getWebElement(driver,
					By.xpath("//*[@id='game-Limbo']/div[1]/div/div[2]/div[2]/div[3]/div/div[1]/span[1]"));
			Thread.sleep(500);
			double realValueDb = Double.valueOf(realResultValue.getText());
			System.out.println("Value of result ================" + realValueDb);
			WebElement moneyValue = VideoRunner.getWebElement(driver,
					By.xpath("//*[@id='Limbo-control-0']/div[2]/div/div[1]/div[2]/input"));
			Thread.sleep(500);
			if (innitValue > 26.0) {
				selectAll(moneyValue);
				Thread.sleep(500);
				resetMinimum(moneyValue);
				return;
			}
			if (realValueDb > defaultValue) {
				selectAll(moneyValue);
				Thread.sleep(500);
				resetMinimum(moneyValue);
				return;
			}
			System.out.println("Value of moneyValue ================" + moneyValue.getText());
			selectAll(moneyValue);
			Thread.sleep(500);
			moneyValue.sendKeys(innitValue + "");
			Thread.sleep(500);
			System.out.println("Send innitValue is ================" + innitValue);
			WebElement betButton = VideoRunner.getWebElement(driver,
					By.xpath("//*[@id='Limbo-control-0']/div[2]/div/button"));
			Thread.sleep(500);
			betButton.click();
			Thread.sleep(1000);
			innitValue = innitValue * 2;
		}
	}

}
