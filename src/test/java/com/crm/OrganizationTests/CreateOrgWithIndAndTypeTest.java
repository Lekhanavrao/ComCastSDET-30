package com.crm.OrganizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class CreateOrgWithIndAndTypeTest {
	@Test
	public void createOrgWithIndTypeNewTest() throws Throwable
	{
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		/*Step 1: Read all necessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String OrgName = eLib.readDataFromExcelSheet("Org", 4, 2)+"_"+jLib.getRandomNuumber();
	    String indType = eLib.readDataFromExcelSheet("Org", 4, 3);
	    String type = eLib.readDataFromExcelSheet("Org", 4, 4);
	    
	    //Step 2: launch the browser
		WebDriver driver=null;
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
		
		//Step 4: Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 5: Click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		//Step 6: enter mandatory fields and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName, indType, type);
		
		//Step 7: verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actHeader = oip.OrgNameInfo();
		if(actHeader.contains(OrgName))
		{
		System.out.println(actHeader +"---->Organization created");	
		}
		else
		{
		System.out.println("organization is not created");
		}
		
		//Step 8: logout of application
		hp.signOutOfApp(driver);
				
		//Step 9: Close the browser
		driver.quit();
				
	}
	
}
