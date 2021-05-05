package com.aoiz.webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\TenNguyen\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get("https://aioz.tube");
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
		elements.get(4).click();
		Thread.sleep(3000);
		List<WebElement> elementAlinks = getListElementMenu(driver, new ByClassName("video-box-img-wrapper"));
		List<String> attributes = new ArrayList<String>();
		for (WebElement element : elementAlinks) {
			String hrefValue = element.getAttribute("href");
			attributes.add(hrefValue);
		}
		clickToVideo(driver, attributes);

//		driver.close();
	}

	private static List<WebElement> getListElementMenu(WebDriver drive, By by) {
		return getWebElements(drive, by);
	}

	private static void clickToVideo(WebDriver driver, List<String> urls) throws InterruptedException {
		for (String url : urls) {
			String currentUrl = url;
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript(String.format("window.open('%s','_blank');", url));
			Thread.sleep(2000);
			List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			String tabName = tabs.get(1);
			System.out.println(tabs.size());
			driver.switchTo().window(tabName);
//			WebElement videoSelect = getWebElement(driver, new ByXPath("//*/div[1]/div/div/div[1]/div/div[2]/div/h3"));
//			videoSelect.click();
			Thread.sleep(5000);
			WebElement videoSelector = getWebElement(driver, new ById("player_html5_api"));
			Thread.sleep(5000);
			System.out.println("Find video tag name");
			WebElement headTag = getWebElement(driver, new ByTagName("video"));
			new Actions(driver).click(headTag).perform();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			System.out.println("arguments[0].play();");
			js.executeScript("return arguments[0].play()", videoSelector);
			Thread.sleep(10000);

			System.out.println("arguments[0].duration;");
			Double timeVideo = (Double) js.executeScript("return arguments[0].duration;", videoSelector);

			long endTime = Math.round(timeVideo);
			js.executeScript(String.format("return arguments[0].currentTime = %d;", endTime), videoSelector);
			System.out.println("timeVideo =========================" + timeVideo);
			int maxTimeRetry = 300;
			int index = 0;
			while (index < maxTimeRetry) {
				System.out.println("Check to close tab");
				if (currentUrl.equals(driver.getCurrentUrl())) {
					Thread.sleep(1000);
					index++;
				} else {
					System.out.println("Before window " + driver.getWindowHandle());
					driver.close();
					Thread.sleep(2000);
					driver.switchTo().window(tabs.get(0));
					System.out.println("After window " + driver.getWindowHandle());
					System.out.println(driver.getWindowHandle());
					break;
				}
			}
		}
	}

	private static WebElement getWebElement(WebDriver driver, By by) {
		int maxRetry = 50;
		int index = 0;
		while (index < maxRetry) {
			try {
				WebElement element = new WebDriverWait(driver, 50000, 500)
						.until(ExpectedConditions.visibilityOfElementLocated(by));
				String elementTagName = element.getTagName();
				System.out.println("elementTagName ==================== " + elementTagName);
				return element;
			} catch (Exception e) {
				index++;
				System.out.println("number of retry ============================= " + index);
			}

		}
		return null;
	}

	private static List<WebElement> getWebElements(WebDriver driver, By by) {
		int maxRetry = 50;
		int index = 0;
		while (index < maxRetry) {
			try {
				new WebDriverWait(driver, 1000000, 2000).until(ExpectedConditions.elementToBeClickable(by));
				List<WebElement> elements = driver.findElements(by);
				return elements;
			} catch (Exception e) {
				index++;
				System.out.println("number of retry ============================= " + index);
			}

		}
		return null;
	}
}
