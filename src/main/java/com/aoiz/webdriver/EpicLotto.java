package com.aoiz.webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aoiz.model.EpicModel;
import com.poiji.bind.Poiji;

public class EpicLotto {
	private static List<EpicModel> getMapModel() {
		List<EpicModel> result = Poiji.fromExcel(new File("E:\\TenNguyen\\learn\\Relictum account.xlsx"), EpicModel.class);
		return result;
	}

	public static void main(String[] args) throws InterruptedException {
		List<EpicModel> mapModels = getMapModel();
		for (EpicModel model : mapModels) {
			System.setProperty("webdriver.chrome.driver", "E:\\tool\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.get("https://epiclotto.io/usercenter/user/sign-in/login");
			Thread.sleep(2000);
			WebElement email = VideoRunner.getWebElement(driver, new ById("loginform-identity"));
			email.sendKeys(model.getEmail());
			Thread.sleep(500);
			WebElement pass = VideoRunner.getWebElement(driver, new ById("loginform-password"));
			if (model.getEmail().contains("ten_relictum2021_1")) {
				pass.sendKeys("relictum2021_1");
			} else {
				pass.sendKeys("Relictrum2021");
			}
			Thread.sleep(1000);

			WebElement btnLogin = VideoRunner.getWebElement(driver, new ByXPath("//*[@id='login-form']/div[4]/button"));
			btnLogin.click();
			Thread.sleep(10000);
//			WebElement freeBtn = VideoRunner.getWebElement(driver,
//					new ByXPath("/html/body/div[3]/div[2]/div/div/div[1]/div[2]/ul/li[9]/a"));
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", freeBtn);
//			freeBtn.click();
			driver.get("https://epiclotto.io/usercenter/game/lotto-free");
			Thread.sleep(10000);
			JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement random = VideoRunner.getWebElement(driver,
					new ByXPath("//*[@id='choose']/div[1]/div/div[3]/div[2]/button[1]"));
			WebElement selectBtn = VideoRunner.getWebElement(driver,
					new ByXPath("//*[@id='choose']/div[1]/div/div[3]/div[2]/button[2]"));
			for (int j = 0; j < 5; j++) {
				je.executeScript("arguments[0].scrollIntoView(true);", random);
				Thread.sleep(200);
				random.click();
				Thread.sleep(200);
				selectBtn.click();
				Thread.sleep(200);
			}
			WebElement createTransaction = VideoRunner.getWebElement(driver,
					new ByXPath("//*[@id='buy-tickets']/div[1]/button"));
			createTransaction.click();
			Thread.sleep(3000);
			driver.quit();
		}
	}
}
