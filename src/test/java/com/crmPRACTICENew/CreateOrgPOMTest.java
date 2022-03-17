package com.crmPRACTICENew;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListnerImplimentationClass.class)
public class CreateOrgPOMTest {

@Test (groups = "smokeSuite")
public void createOrgTestPOM() throws Throwable
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
	
	String OrgName = eLib.readDataFromExcelSheet("Org", 1, 2)+"_"+jLib.getRandomNuumber();
	
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
	
	//Step 3: login to application
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	Assert.fail();
	
	//Step 4: Navigate to organization link
	HomePage hp = new HomePage(driver);
	hp.clickOnOrgLnk();
	
	
	//Step 5: Click on create organization button
	OrganizationsPage op = new OrganizationsPage(driver);
	op.clickOnCreateOrgImg();
	
	//Step 6: enter mandatory fields and save
	CreateOrganizationPage cop = new CreateOrganizationPage(driver);
	cop.createNewOrg(OrgName);
	
	//Step 7: verification
	OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	String actOrgName = oip.OrgNameInfo();
	if(actOrgName.contains(OrgName))
	{
		System.out.println(actOrgName+"---->data verified");
	}
	else
	{
		System.out.println("data invalid");
	}
	
	//Step 8: logout of application
	hp.signOutOfApp(driver);
	
	//Step 9: Close the browser
	driver.quit();
}


     @Test (groups = "regressionSuite")
      public void testScript()
      {
        System.out.println("test case 02");
      }
}
