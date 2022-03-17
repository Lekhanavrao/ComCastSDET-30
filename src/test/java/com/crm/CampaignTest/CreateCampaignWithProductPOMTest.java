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
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductsInfoPage;
import com.crm.ObjectRepository.ProductsPage;

public class CreateCampaignWithProductPOMTest {
	@Test
	public void createCampaignWithProduct() throws Throwable
	{
	/*read data*/
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtility eLib = new ExcelFileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	/*Step 1: read all necessary data*/
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");
	
	String campName = eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandomNuumber();
	String prodName = eLib.readDataFromExcelSheet("Campaign", 1, 3);
	
	/*Step 1: launch the browser*/
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
		System.out.println("invalid browser name");
	}
	
	wLib.maximizewindow(driver);
	wLib.waitForPageLoad(driver);
	driver.get(URL);
	
	//Step 2: login to application
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	
	//Step 3: Navigate to product link
	HomePage hp = new HomePage(driver);
    hp.clickOnProductLnk();
	    
	//Step 4: Click on create product button
	 ProductsPage pp = new ProductsPage(driver);
     pp.clickOnProductLookUpImg();
	    
   //Step 5: enter mandatory fields and save
    CreateProductsPage cpp = new CreateProductsPage(driver);
	cpp.createNewProduct(prodName);
		
    //Step 6: Verification
    ProductsInfoPage pip = new ProductsInfoPage(driver);
    String actProduct = pip.productNameInfo();
	if(actProduct.contains(prodName))
	{
		System.out.println(actProduct+"---->data verified");
	}
	else
	{
		System.out.println("data invalid");
	}
	
	
	//Step 7: Navigate to campaign link
	hp.clickOnCampaignLnk();
	
	//Step 8:click on Create campaign button
	CampaignsPage cp = new CampaignsPage(driver);
	cp.clickOnCreateCampaignsImg();      
	
	//Step 9:enter the details and save
	CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
	ccp.createNewCampaign(driver, campName, prodName);
	
	//Step 10: Verification
	CampaignsInfoPage cip = new CampaignsInfoPage(driver);
	String actCampaign = cip.campaigntNameInfo();
	if(actCampaign.contains(campName))
	{
		System.out.println(actCampaign+"---->data verified");
	}
	else
	{
		System.out.println("data invalid");
	}
    
	
	    	
	//Step 11: logout of application
	hp.signOutOfApp(driver);
	
	//Step 12: Close the browser
	driver.quit();

	}
}
