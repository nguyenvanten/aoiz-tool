package com.aoiz.webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aoiz.model.EpicModel;

public class EpicLotto {
	private static List<EpicModel> getMapModel() {
		List<EpicModel> result = new ArrayList<>(Arrays.asList(
				
//				new EpicModel("ten_relictum2021_1@outlook.com.vn"), 
//				new EpicModel("nam_relictum2021_1@outlook.com.vn"), 
//				new EpicModel("nam_relictum2021_2@outlook.com.vn"), 
//				new EpicModel("nam_3relictum2021_3@outlook.com.vn"),
//				new EpicModel("nam_4relictum2021_4@outlook.com.vn"),
//				new EpicModel("nam_5relictum2021_5@outlook.com.vn"), 
//				new EpicModel("nam_6relictum2021_6@outlook.com.vn"), 
//				new EpicModel("ads1sutlook.com@outlook.com"),
//				new EpicModel("minadnamtrannguyen@outlook.com"), 
//				new EpicModel("help1s@outlook.com"),
//				new EpicModel("tkwebgiare.com1s@outlook.com"), 
//				new EpicModel("banthansv921s@outlook.com"),
//				new EpicModel("tuanmbland1s@outlook.com"), 
//				new EpicModel("nguyentienkhoi3811s@outlook.com"),
//				new EpicModel("batdongsangiatot.com.vn1s@outlook.com"),
//				new EpicModel("thienduong.massage.spa1s@outlook.com"), 
//				new EpicModel("qualitybacklink4u1s@outlook.com"),
//				new EpicModel("tuanlaptopqn1s@outlook.com"), 
//				new EpicModel("newsky02061s@outlook.com"),
//				new EpicModel("hotcongnghe1s@outlook.com"), 
//				new EpicModel("qcgame2004pc1s@outlook.com")));
				new EpicModel("isqz2kuqbs4h@opayq.com"),
				new EpicModel("3lz0d9vn84w7@opayq.com"),
				new EpicModel("a4dofsv1dlop@opayq.com"),
				new EpicModel("4ix7upu9w2xy@opayq.com"),
				new EpicModel("y7tik0f3paq2@opayq.com"),
				new EpicModel("saolasao@opayq.com"),
				new EpicModel("iqli5xjsjxa4@opayq.com"),
				new EpicModel("7ct5hmqzy7ul@opayq.com"),
				new EpicModel("brjscja7zvnz@opayq.com"),
				new EpicModel("8zgifwcxfy2d@opayq.com"),
				new EpicModel("v3rd0cviml96@opayq.com"),
				new EpicModel("xmo48w4yv9m@opayq.com"),
				new EpicModel("oatq7ae39vog@opayq.com"),
				new EpicModel("hq8qp544atha@opayq.com"),
				new EpicModel("mscqk53725jk@opayq.com"),
				new EpicModel("8rul7b6lpwyq@opayq.com"),
				new EpicModel("l5p52ylopi4q@opayq.com"),
				new EpicModel("shj414bakrgp@opayq.com"),
				new EpicModel("96d4xc28u81s@opayq.com"),
				new EpicModel("q5ye0pigl9k9@opayq.com")));
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
