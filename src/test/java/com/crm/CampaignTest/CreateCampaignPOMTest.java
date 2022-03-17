package com.crm.CampaignTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CampaignsInfoPage;
import com.crm.ObjectRepository.CampaignsPage;
import com.crm.ObjectRepository.CreateCampaignsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignPOMTest {
	@Test
	public void createCampaignPOMTest() throws Throwable
	{
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step 1: Read all necessary data
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String campname = eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandomNuumber();
		
		//Step 2: launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else 
		{
			System.out.println("invalid browser");	
		}
		
		wLib.maximizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 2: login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 3: Navigate to campaign link
		HomePage hp = new HomePage(driver);
		hp.clickOnCampaignLnk();
		
		//Step 4:click on Create campaign button
		CampaignsPage cp = new CampaignsPage(driver);
		cp.clickOnCreateCampaignsImg();
		
		//Step 5:enter the details and save
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
		ccp.createNewCampaign(campname);
		
		//Step 6: Verification
    	CampaignsInfoPage cip = new CampaignsInfoPage(driver);
    	String actCampaign = cip.campaigntNameInfo();
    	if(actCampaign.contains(campname))
    	{
    		System.out.println(actCampaign+"---->data verified");
    	}
    	else
    	{
    		System.out.println("data invalid");
    	}
        
    	//Step 6: logout of application
    	hp.signOutOfApp(driver);
    	
    	//Step 7: Close the browser
    	driver.quit();
	}

}
