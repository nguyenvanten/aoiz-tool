package com.aoiz.webdriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoRunner {
	public static void runVideo(WebDriver driver, int index) throws InterruptedException {
		Thread.sleep(1000);
		WebElement acceptButton = getWebElement(driver, new ByXPath("/html/body/div[3]/div/div/div/button"));
		acceptButton.click();
		Thread.sleep(1000);

		WebElement loginButton = getWebElement(driver, new ByXPath("//*/div[3]/div/header/div[2]/div[1]/button[1]"));
		loginButton.click();
		Thread.sleep(1000);

		WebElement emailText = getWebElement(driver, new ById("email"));
		emailText.sendKeys("tenitbinance@gmail.com");

		WebElement passwordText = getWebElement(driver, new ById("password"));
		passwordText.sendKeys("Texx#321");

		WebElement continueButton = getWebElement(driver,
				new ByXPath("/html/body/div[4]/div/div/div/div/form/div[4]/button"));
		continueButton.click();
		List<WebElement> elements = getListElementMenu(driver, new ByClassName("aioz-sidebar-item"));
		Thread.sleep(10000);
		elements.get(index).click();
		Thread.sleep(3000);
		List<WebElement> elementAlinks = getListElementMenu(driver, new ByClassName("video-box-img-wrapper"));
		List<String> attributes = new ArrayList<String>();
		for (WebElement element : elementAlinks) {
			String hrefValue = element.getAttribute("href");
			attributes.add(hrefValue);
		}
		startVideo(driver, attributes.get(0), index);

	}

//		driver.close();

	private static List<WebElement> getListElementMenu(WebDriver driver, By by) {
		return getWebElements(driver, by);
	}

	private static void startVideo(WebDriver driver, String url, int threadIndex) throws InterruptedException {
		System.out.println("Start video " + url);
		clickToVideo(driver, url, threadIndex);
	}

	private static void clickToVideo(WebDriver driver, String url, int threadIndex) throws InterruptedException {
		String currentUrl = url;
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript(String.format("window.open('%s','_blank');", url));
		System.out.println("Open new tab");
		Thread.sleep(2000);
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String tabName = tabs.get(1);
		driver.switchTo().window(tabName);
		System.out.println("Switch to next tab");
		Thread.sleep(5000);
		WebElement videoSelector = getWebElement(driver, new ById("player_html5_api"));
		System.out.println("Find video element");
		Thread.sleep(3000);
		WebElement headTag = getWebElement(driver, new ByTagName("video"));
		new Actions(driver).click(headTag).perform();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Double timeVideo = (Double) js.executeScript("return arguments[0].duration;", videoSelector);

		long endTime = Math.round(timeVideo);
		System.out.println("Get time video ");
		js.executeScript(String.format("return arguments[0].currentTime = %d;", endTime), videoSelector);
		System.out.println("Scroll to end time video");
		int maxTimeRetry = 600;
		int index = 0;
		System.out.println("Start waiting for next video");
		while (index < maxTimeRetry) {
			System.out.println("Loop to wating next video");
			if (currentUrl.equals(driver.getCurrentUrl())) {
				Thread.sleep(1000);
				index++;
			} else {
				String urlNew = driver.getCurrentUrl();
				driver.close();
				Thread.sleep(1000);
				driver.switchTo().window(tabs.get(0));
				startVideo(driver, urlNew, threadIndex);
				Thread.sleep(1000);
				break;
			}
		}
	}

	private static WebElement getWebElement(WebDriver driver, By by) {
		int maxRetry = 100;
		int index = 0;
		while (index < maxRetry) {
			try {
				WebElement element = new WebDriverWait(driver, 50000, 500)
						.until(ExpectedConditions.visibilityOfElementLocated(by));
				return element;
			} catch (Exception e) {
				index++;
			}

		}
		return null;
	}

	private static List<WebElement> getWebElements(WebDriver driver, By by) {
		int maxRetry = 100;
		int index = 0;
		while (index < maxRetry) {
			try {
				new WebDriverWait(driver, 1000000, 2000).until(ExpectedConditions.elementToBeClickable(by));
				List<WebElement> elements = driver.findElements(by);
				return elements;
			} catch (Exception e) {
				index++;
			}

		}
		return null;
	}
}
