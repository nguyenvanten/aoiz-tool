package com.aoiz.webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aoiz.model.EpicModel;

public class EpicCreator {
	private static Map<Integer, EpicModel> getMapModel() {
		Map<Integer, EpicModel> result = new HashMap<Integer, EpicModel>();
		result.put(1, new EpicModel("nam_relictum2021_1@outlook.com.vn", "Lmwe93noWihyudB5MnxfA4opXji04lDA"));
		result.put(1, new EpicModel("nam_6relictum2021_6@outlook.com.vn", "s6LlrapUjVlTJgxEt7MmsbqVkWmUUo0M"));
		return result;
	}
	public static void main(String[] args) throws InterruptedException {
		Map<Integer, EpicModel> mapModel = getMapModel();
		for (int i = 1; i <= mapModel.size(); i++) {
			EpicModel model = mapModel.get(i);
			System.setProperty("webdriver.chrome.driver", "E:\\tool\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.get("https://epiclotto.io/usercenter/user/sign-in/signup");
			Thread.sleep(2000);
			WebElement userNameElement = VideoRunner.getWebElement(driver, new ById("signupform-username"));
			String username = RandomStringUtils.randomAlphabetic(10);
			userNameElement.sendKeys(username);
			Thread.sleep(1000);
			WebElement email = VideoRunner.getWebElement(driver, new ById("signupform-email"));
			email.sendKeys(model.getEmail());
			System.out.println(username);
			System.out.println(model.getEmail());
			Thread.sleep(1000);
			WebElement pass = VideoRunner.getWebElement(driver, new ById("signupform-password"));
			pass.sendKeys("Relictrum2021");
			Thread.sleep(1000);
			WebElement rePass = VideoRunner.getWebElement(driver, new ById("signupform-password_confirm"));
			rePass.sendKeys("Relictrum2021");
			Thread.sleep(3000);
			
			WebElement btnDangKy = VideoRunner.getWebElement(driver,
					new ByXPath("//*[@id='login-form']/div[6]/button"));
			btnDangKy.click();
			Thread.sleep(10000);
			WebElement btnHoso = VideoRunner.getWebElement(driver,
					new ByXPath("/html/body/div[3]/div[2]/div/div/div[1]/div[2]/ul/li[2]/a"));
			btnHoso.click();
			Thread.sleep(10000);
			WebElement wallet = VideoRunner.getWebElement(driver, new ById("setrelictumform-relictum"));
			wallet.sendKeys(model.getKey());
			Thread.sleep(2000);
			WebElement confirm = VideoRunner.getWebElement(driver, new ByXPath("//*[@id='relictum-form']/div/button"));
			confirm.click();
			Thread.sleep(2000);
			driver.quit();
			Thread.sleep(5000);
		}
	}
}
