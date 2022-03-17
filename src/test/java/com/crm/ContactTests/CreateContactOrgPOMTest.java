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
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateContactOrgPOMTest {
	
	@Test
	public void createContactOrgPOMTest() throws Throwable
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
		
		String lastName = eLib.readDataFromExcelSheet("Contacts", 3, 2)+"_"+jLib.getRandomNuumber();
	    String OrgName = eLib.readDataFromExcelSheet("Contacts", 3, 3)+"_"+jLib.getRandomNuumber();
	    
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
		cop.createNewOrg(OrgName);
	
		//Step 7: verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.OrgNameInfo();
		if(header.contains(OrgName))
			{
				System.out.println(header+"---->Org created");
			}
			else
			{
				System.out.println("org not created");
			}
	
		//Step 8: navigate to contacts link
		hp.clickOnContactLnk();
		
		//Step 9: Click on create contact link
        ContactsPage cp = new ContactsPage(driver);
        cp.clickOnCreateContactImg();
        
        //Step 10: enter mandatory fields and save
    	CreateContactsPage ccp = new CreateContactsPage(driver);
    	ccp.createNewContact(driver, lastName, OrgName);
    	
    	//Step 11:verify for contact
    	ContactsInfoPage cip = new ContactsInfoPage(driver);
    	String contactHeader = cip.contactNameInfo();
    	if(contactHeader.contains(lastName))
    	{
    		System.out.println(contactHeader+"---->contact created");
    	}
    	else
    	{
    		System.out.println("contact not created");
    	}
        
    	//Step 8: logout of application
    	hp.signOutOfApp(driver);
    	
    	//Step 9: Close the browser
    	driver.quit();
		
	}
}
  












