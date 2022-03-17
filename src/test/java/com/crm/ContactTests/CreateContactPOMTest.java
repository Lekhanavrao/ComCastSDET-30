package com.crm.ContactTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactPOMTest {
	
	@Test (groups = "smokeSuite")
	public void createContactPOMTest() throws Throwable
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
		
		String lastName = eLib.readDataFromExcelSheet("Contacts", 1, 2)+"_"+jLib.getRandomNuumber();
		
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
		
		//Step 4: Navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 5: Click on create Contact button
        ContactsPage cp = new ContactsPage(driver);
        cp.clickOnCreateContactImg();
        
        //Step 6: enter mandatory fields and save
    	CreateContactsPage ccp = new CreateContactsPage(driver);
    	ccp.createNewContact(lastName);
    	
    	//Step 7: Verification
    	ContactsInfoPage cip = new ContactsInfoPage(driver);
    	String actContact = cip.contactNameInfo();
    	if(actContact.contains(lastName))
    	{
    		System.out.println(actContact+"---->data verified");
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
    public void testScript1()
    {
      System.out.println("test case 03");
    }
}
