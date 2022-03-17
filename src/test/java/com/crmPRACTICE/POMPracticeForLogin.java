package com.crmPRACTICE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class POMPracticeForLogin {
	@Test
	public void pomPractice()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverUtility wLib=new WebDriverUtility();
		wLib.waitForPageLoad(driver);
		driver.get("http://localhost:8885");
		
		LoginPage lP = new LoginPage(driver);
		lP.loginToApp("admin", "admin");
		
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		hp.signOutOfApp(driver);
	}

}
