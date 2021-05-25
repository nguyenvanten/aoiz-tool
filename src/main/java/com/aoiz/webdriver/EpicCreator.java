package com.aoiz.webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aoiz.model.EpicModel;

public class EpicCreator extends Thread {

	private List<EpicModel> epicModels;

	public EpicCreator(List<EpicModel> epicModels) {
		this.epicModels = epicModels;
	}

	@Override
	public void run() {
		try {
			for (EpicModel model : epicModels) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				driver.get("https://epiclotto.io/usercenter/user/sign-in/signup");
				Thread.sleep(2000);
				WebElement userNameElement = VideoRunner.getWebElement(driver, new ById("signupform-username"));
				userNameElement.sendKeys(model.getEmail().split("@")[0]);
				Thread.sleep(1000);
				WebElement email = VideoRunner.getWebElement(driver, new ById("signupform-email"));
				email.sendKeys(model.getEmail());
				System.out.println(model.getEmail().split("@")[0]);
				System.out.println(model.getEmail());
				Thread.sleep(1000);
				WebElement pass = VideoRunner.getWebElement(driver, new ById("signupform-password"));
				pass.sendKeys(model.getPass());
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
				wallet.sendKeys(model.getKeyNode());
				Thread.sleep(2000);
				WebElement confirm = VideoRunner.getWebElement(driver,
						new ByXPath("//*[@id='relictum-form']/div/button"));
				confirm.click();
				Thread.sleep(2000);
				driver.quit();
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
