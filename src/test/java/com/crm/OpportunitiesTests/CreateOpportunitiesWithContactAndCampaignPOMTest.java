package com.crm.OpportunitiesTests;

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
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateCampaignsPage;
import com.crm.ObjectRepository.CreateContactsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunitiesWithContactAndCampaignPOMTest {
	@Test
	public void createOpportunitiesWithContactAndCampaignPOMTest() throws Throwable
	{
		/*Step 1: Read all necessary Data */
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
	   
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
        String campName = eLib.readDataFromExcelSheet("Opportunities", 1, 2)+"_"+jLib.getRandomNuumber();
        String contactName = eLib.readDataFromExcelSheet("Opportunities", 1, 2)+"_"+jLib.getRandomNuumber();
        String oppName = eLib.readDataFromExcelSheet("Opportunities", 1, 2)+"_"+jLib.getRandomNuumber();

        
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
    	ccp.createNewContact(contactName);
    	
    	//Step 7: Verification
    	ContactsInfoPage cip = new ContactsInfoPage(driver);
    	String actContact = cip.contactNameInfo();
    	if(actContact.contains(contactName))
    	{
    		System.out.println(actContact+"---->data verified");
    	}
    	else
    	{
    		System.out.println("data invalid");
    	}
    	
    	//Step 3: Navigate to campaign link
    			hp.clickOnCampaignLnk();
    			
    	//Step 4:click on Create campaign button
   		CampaignsPage cp1 = new CampaignsPage(driver);
    	cp1.clickOnCreateCampaignsImg();
    			
    	//Step 5:enter the details and save
    	CreateCampaignsPage ccp1 = new CreateCampaignsPage(driver);
   		ccp1.createNewCampaign(campName);
    			
  		//Step 6: Verification
       	CampaignsInfoPage cip1 = new CampaignsInfoPage(driver);
     	String actCampaign = cip1.campaigntNameInfo();
    	  if(actCampaign.contains(campName))
    	    {
    	    	System.out.println(actCampaign+"---->data verified");
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

		
	}
	
	


