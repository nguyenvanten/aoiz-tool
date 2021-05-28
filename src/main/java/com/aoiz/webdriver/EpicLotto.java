package com.aoiz.webdriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aoiz.model.EpicModel;

public class EpicLotto extends Thread {
	private List<EpicModel> epicModels;
	private int index;

	public EpicLotto(List<EpicModel> epicModels, int index) {
		this.epicModels = epicModels;
		this.index = index;
	}

	@Override
	public void run() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		String url = "https://epiclotto.io/usercenter/user/sign-in/login";
		FileWriter myWriter = null;
		FileWriter error = null;
		try {
			error = new FileWriter("notcreate" + index + ".txt");
			myWriter = new FileWriter("epicloto" + index + ".txt");
			for (EpicModel model : epicModels) {
				System.out.println("Run data for ========================== " + model.getEmail());
				String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
				myWriter.write(model.getIndex());
				myWriter.write("\t");
				myWriter.write(time);
				myWriter.write("\t");
				myWriter.write(model.getEmail());
				myWriter.write("\n");
				Thread.sleep(2000);
				driver.get(url);
				Thread.sleep(2000);
				WebElement email = VideoRunner.getWebElement(driver, new ById("loginform-identity"));
				email.sendKeys(model.getEmail());
				Thread.sleep(500);
				WebElement pass = VideoRunner.getWebElement(driver, new ById("loginform-password"));
				pass.sendKeys(model.getPass());
				Thread.sleep(500);

				WebElement btnLogin = VideoRunner.getWebElement(driver,
						new ByXPath("//*[@id='login-form']/div[4]/button"));
				btnLogin.click();
				System.out.println("Login ============================");
				Thread.sleep(10000);
				if (driver.getPageSource().contains("Incorrect username or password")) {
					error.write(model.getEmail());
					error.write("\n");
					continue;
				}
				
				driver.get("https://epiclotto.io/usercenter/game/lotto-free");
				Thread.sleep(5000);
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
//				driver.quit();
				driver.get("https://epiclotto.io/usercenter/user/sign-in/logout");
			}
			driver.quit();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				myWriter.close();
				error.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
